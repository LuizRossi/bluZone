package br.com.bluzone

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.bluzone.databinding.ActivityMainBinding
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var functions: FirebaseFunctions

    // instanciando um objeto gson
    private val gson = GsonBuilder().enableComplexMapKeySerialization().create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        functions = Firebase.functions("southamerica-east1")
        //Inflar a activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTelaConsultar.setOnClickListener{
            abrirTelaConsulta()
        }
    }

    private val networkChecker by lazy {
        NetworkChecker(ContextCompat.getSystemService(this, ConnectivityManager::class.java))
    }



    private fun abrirTelaConsulta(){
        //navegar pra activity de tela de consulta
        networkChecker.performActionIfConnectec {
            val intentTelaConsulta = Intent(this, StatusVeiculo::class.java)
            startActivity(intentTelaConsulta)
        }

    }
}
