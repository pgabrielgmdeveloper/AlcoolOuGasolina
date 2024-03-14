package br.com.pgabrelgmdeveloper.alcoolougasolina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var gasolinaText: TextInputEditText
    private lateinit var gasolinaLayout: TextInputLayout
    private lateinit var alcoolText: TextInputEditText
    private lateinit var alcoolLayout: TextInputLayout
    private lateinit var btnCalcular: Button
    private lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()

    }

    private fun validateInputs(): Boolean {
        alcoolLayout.error = null
        gasolinaLayout.error = null
        if(alcoolText.text.toString().isEmpty()){
            alcoolLayout.error = "é obrigatório preencher este campo"
            return false
        }else if (gasolinaText.text.toString().isEmpty()){
            gasolinaLayout.error = "é obrigatório preencher este campo"
            return false
        }
        return true
    }

    private fun initViews() {
        gasolinaText = findViewById(R.id.edit_gasolina)
        gasolinaLayout = findViewById(R.id.text_input_gasolina)
        alcoolText = findViewById(R.id.edit_alcool)
        alcoolLayout = findViewById(R.id.text_input_alcool)
        btnCalcular = findViewById(R.id.btn_calcular)
        textResult = findViewById(R.id.text_result)
        btnCalcular.setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {
        val result: Boolean = validateInputs()
        if (result){
            val alcoolValue = alcoolText.text.toString().toDouble()
            val gasolinaValue = gasolinaText.text.toString().toDouble()

            val dv = alcoolValue/gasolinaValue
            if (dv <= 0.7){
                textResult.text = "É mais vantajoso usar alcool"
            }else{
                textResult.text = "É mais vantajoso usar gasolina"

            }
        }
    }
}