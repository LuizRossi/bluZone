package br.com.bluzone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder

class StatusVeiculo : AppCompatActivity() {

    private lateinit var etPlaca: AppCompatEditText
    private lateinit var consultarBtn: AppCompatButton
    private lateinit var statusTV: AppCompatTextView
    private lateinit var registraBtn: AppCompatButton
    private lateinit var backBtn: AppCompatButton
    private lateinit var homeBtn: AppCompatButton
    private lateinit var functions: FirebaseFunctions

    // instanciando um objeto gson
    private val gson = GsonBuilder().enableComplexMapKeySerialization().create()

    override fun onCreate(savedInstanceState: Bundle?) {
        functions = Firebase.functions("southamerica-east1")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statusveiculo)
        //setTitle(R.string.activity_status_veiculo_title)

        //botão para voltar para a home
        homeBtn = findViewById(R.id.homeBtn)
        homeBtn.setOnClickListener {

            IrParaHome()
        }


        //Botão para voltar a tela
        backBtn = findViewById(R.id.backBtn)
        backBtn.setOnClickListener{
            VoltarParaHome()
        }

        //Edit Text para consultar a placa

        etPlaca = findViewById(R.id.etPlaca)
        etPlaca.setOnFocusChangeListener { placa, focus ->
            if (focus) {
                statusTV.visibility = View.GONE

            }
        }

        //Botão para consultar a placa
        consultarBtn = findViewById(R.id.consultarBtn)
        consultarBtn.setOnClickListener {
            verificarStatus()
        }
        //Texto para mostar o status
        statusTV = findViewById(R.id.statusTV)

        //Botão para mudar para tela de Irregularidade
        registraBtn = findViewById(R.id.registraBtn)
        registraBtn.setOnClickListener {
            IrTelaIrregularidade()
        }



    }
    private fun consultaVeiculo(){

    }
    //função da tela de consulta do veículo
    private fun verificarStatus() {
        if (etPlaca.text.isNullOrEmpty()) {
            Snackbar.make(statusTV, "Informe a placa para consultar o status", Snackbar.LENGTH_LONG)
                .show()
        } else {
            val message = "O veículo ${etPlaca.text} está irregular"
            statusTV.text = message
            statusTV.visibility = View.VISIBLE
            etPlaca.text!!.clear()
            etPlaca.clearFocus()
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    //Função para ir para a Irregularidade
    private fun IrTelaIrregularidade(){
        val telaIrregularidade = Intent(this, Irregularidade::class.java)
        startActivity(telaIrregularidade)
    }

    private fun IrParaHome() {
        val telaHome = Intent(this, MainActivity::class.java)
        startActivity(telaHome)
    }
    private fun VoltarParaHome() {
        val telaHome = Intent(this, MainActivity::class.java)
        startActivity(telaHome)
    }
    fun validaPlaca(){

    }

}

