package com.GabrielBastardo30.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.GabrielBastardo30.imc.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class IMC_Activity : AppCompatActivity() {

    private var isMaleSelected:Boolean=true
    private var isFemaleSelected:Boolean=false
    private var currentWeight:Int=50
    private var currentAge:Int=20
    private var currentHeight:Int=120

    private lateinit var cardMale: CardView
    private lateinit var cardFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider

    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button

    companion object{
        const val IMC_KEY="IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)
        initComponents()
        initListeners()
        initUI()

    }

    private fun initComponents(){
        cardMale= findViewById<CardView>(R.id.cardMale)
        cardFemale = findViewById<CardView>(R.id.cardFemale)
        tvHeight = findViewById<TextView>(R.id.tvHeight)
        rsHeight = findViewById<RangeSlider>(R.id.rsHeight)

        btnPlusWeight= findViewById<FloatingActionButton>(R.id.btnPlusWeight)
        btnSubtractWeight= findViewById<FloatingActionButton>(R.id.btnSubtractWeight)
        tvWeight= findViewById<TextView>(R.id.tvWeight)
        btnPlusAge = findViewById<FloatingActionButton>(R.id.btnPlusAge)
        btnSubtractAge = findViewById<FloatingActionButton>(R.id.btnSubtractAge)
        tvAge=findViewById<TextView>(R.id.tvAge)
        btnCalculate= findViewById<Button>(R.id.btnCalculate)
    }

    private fun initListeners(){
        cardMale.setOnClickListener{
            changeGender()
            setGenderColor()}
        cardFemale.setOnClickListener {
            changeGender()
            setGenderColor()}

        rsHeight.addOnChangeListener { _, value, _ ->
            val df= DecimalFormat("#.##")
            currentHeight= df.format(value).toInt()
            tvHeight.text= "$currentHeight cm"
        }
        btnPlusWeight.setOnClickListener{
            currentWeight= currentWeight+1
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight= currentWeight-1
            setWeight()
        }
        btnPlusAge.setOnClickListener {
            currentAge= currentAge+1
            setAge()
        }
        btnSubtractAge.setOnClickListener {
            currentAge= currentAge-1
            setAge()
        }
        btnCalculate.setOnClickListener{
            val result= calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result:Double) {
        val intent= Intent(this,ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY,result)
        startActivity(intent)
    }

    private fun calculateIMC():Double {
        val df= DecimalFormat("#.##")
        val imc = currentWeight/((currentHeight.toDouble()/100)*(currentHeight.toDouble()/100))
        return df.format(imc).toDouble()
    }


    private fun setAge() {
        tvAge.text= currentAge.toString()
    }

    private fun setWeight() {
        tvWeight.text= currentWeight.toString()
    }

    private fun changeGender(){
        isMaleSelected=!isMaleSelected
        isFemaleSelected=!isFemaleSelected
    }

    private fun setGenderColor(){

        cardMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        cardFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent:Boolean):Int{

        val colorReference= if(isSelectedComponent){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }

        return ContextCompat.getColor(this,colorReference)

    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}