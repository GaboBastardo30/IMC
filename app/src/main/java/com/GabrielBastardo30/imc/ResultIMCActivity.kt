package com.GabrielBastardo30.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.GabrielBastardo30.imc.IMC_Activity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        val result:Double= intent.extras?.getDouble(IMC_KEY)?:-1.0

        initComponents()
        initListeners()
        initUI(result)
    }

    private fun initComponents() {

        tvIMC=findViewById(R.id.tvIMC)
        tvResult=findViewById(R.id.tvResult)
        tvDescription=findViewById(R.id.tvDescription)
        btnRecalculate=findViewById(R.id.btnRecalculate)
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener{
            onBackPressed()
        }
    }


    private fun initUI(result: Double) {

        tvIMC.text=result.toString()
        when(result){
            in 0.00..18.50->{ //Bajo peso
                tvResult.text=getString(R.string.titleBajoPeso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
                tvDescription.text=getString(R.string.descriptionBajoPeso)
            }
            in 18.50..24.99->{ //Peso normal
                tvResult.text=getString(R.string.titlePesoNormal)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_normal))
                tvDescription.text=getString(R.string.descriptionPesoNormal)
            }
            in 25.00..29.99->{ //Sobrepeso
                tvResult.text=getString(R.string.titleSobrepeso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.sobrepeso))
                tvDescription.text=getString(R.string.descriptionSobrepeso)
            }
            in 30.00..99.00->{ //Obesidad
                tvResult.text=getString(R.string.titleObesidad)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                tvDescription.text=getString(R.string.descriptionObesidad)
            }
            else->{ //Error
                tvIMC.text=getString(R.string.error)
                tvResult.text=getString(R.string.error)
                tvDescription.text=getString(R.string.error)
            }
        }
    }



}