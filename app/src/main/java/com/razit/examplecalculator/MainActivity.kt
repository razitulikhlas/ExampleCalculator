package com.razit.examplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var status = 5
    private var error = false
    private var result : Double? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkbox1.setOnClickListener {
            ed_layout1.isEnabled = checkbox1.isChecked
        }

        checkbox2.setOnClickListener {
            ed_layout2.isEnabled = checkbox2.isChecked
        }

        checkbox3.setOnClickListener {
            ed_layout3.isEnabled = checkbox3.isChecked
        }


        btnAdd.setOnClickListener {
            resetStatus()
            checkInput()
            sumNumber()
        }

        btnMin.setOnClickListener {
            resetStatus()
            checkInput()
            minNumber()
        }

        btnDiv.setOnClickListener {
            resetStatus()
            checkInput()
            divNumber()
        }

        btnMultiple.setOnClickListener {
            resetStatus()
            checkInput()
            multipleNumber()
        }


    }


    private fun sumNumber(){
        when (status) {
            1 -> {
                result = sumNumbers(edBil1.text.toString().toDouble(),edBil2.text.toString().toDouble(),edBil3.text.toString().toDouble())
            }
            2 -> {
                result =sumNumbers(edBil1.text.toString().toDouble(),edBil2.text.toString().toDouble())
            }
            3 -> {
                result = sumNumbers(edBil2.text.toString().toDouble(),edBil3.text.toString().toDouble())
            }
            4 -> {
                result =sumNumbers(edBil1.text.toString().toDouble(),edBil3.text.toString().toDouble())
            }
            5 -> {
                error = !error
            }
        }
        result()
    }

    private fun minNumber(){
        when (status) {
            1 -> {
                result = edBil1.text.toString().toDouble()-edBil2.text.toString().toDouble()-edBil3.text.toString().toDouble()
            }
            2 -> {
                result = edBil1.text.toString().toDouble()-edBil2.text.toString().toDouble()
            }
            3 -> {
                result = edBil2.text.toString().toDouble() - edBil3.text.toString().toDouble()
            }
            4 -> {
                result =edBil1.text.toString().toDouble() - edBil3.text.toString().toDouble()
            }
            5 -> {
                error = !error
            }
        }
        result()
    }

    private fun divNumber(){
        when (status) {
            1 -> {
                result = edBil1.text.toString().toDouble() / edBil2.text.toString().toDouble()/edBil3.text.toString().toDouble()
            }
            2 -> {
                result = edBil1.text.toString().toDouble() / edBil2.text.toString().toDouble()
            }
            3 -> {
                result = edBil2.text.toString().toDouble() / edBil3.text.toString().toDouble()
            }
            4 -> {
                result =edBil1.text.toString().toDouble() / edBil3.text.toString().toDouble()
            }
            5 -> {
                error = !error
            }
        }
        result()
    }

    private fun multipleNumber(){
        when (status) {
            1 -> {
                result = edBil1.text.toString().toDouble() * edBil2.text.toString().toDouble() * edBil3.text.toString().toDouble()
            }
            2 -> {
                result = edBil1.text.toString().toDouble() * edBil2.text.toString().toDouble()
            }
            3 -> {
                result = edBil2.text.toString().toDouble() * edBil3.text.toString().toDouble()
            }
            4 -> {
                result =edBil1.text.toString().toDouble() * edBil3.text.toString().toDouble()
            }
            5 -> {
                error = !error
            }
        }
        result()
    }
    private fun sumNumbers(vararg number: Double): Double {
        return number.sum()
    }


    private fun checkInput(){
        status = if(checkbox1.isChecked && checkbox2.isChecked && checkbox3.isChecked){
            1
        }else if(checkbox1.isChecked && checkbox2.isChecked){
            2
        }else if(checkbox2.isChecked && checkbox3.isChecked){
            3
        }else if(checkbox1.isChecked && checkbox3.isChecked){
            4
        } else{
            5
        }
    }

    private fun resetStatus(){
        status = 5
        error = false
    }

    private fun result(){
        if(error){
            messageError()
            tv_result.text = ""
        }else{
            tv_result.text = result.toString()
        }
    }

    private fun messageError(){
        Toast.makeText(this,"Terjadi kesalahan ketika proses perhitungan,kemungkinan angka yang anda masukan cuman satu buah atau tidak sama sekali",Toast.LENGTH_SHORT).show()
    }










}