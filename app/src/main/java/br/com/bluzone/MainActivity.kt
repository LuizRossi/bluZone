package br.com.bluzone

import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import br.com.bluzone.databinding.ActivityMainBinding
import br.com.bluzone.databinding.ActivityStatusveiculoBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
}