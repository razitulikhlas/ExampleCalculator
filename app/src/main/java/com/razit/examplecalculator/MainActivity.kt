package com.razit.examplecalculator

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var status = 5
    private var error = false
    private var result : Double? = null

    companion object{
        const val NUMBER_INPUT_1 = "Bilangan pertama wajib di isi"
        const val NUMBER_INPUT_2 = "Bilangan kedua wajib di isi"
        const val NUMBER_INPUT_3 = "Bilangan ketiga wajib di isi"
    }


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
            hideKeyboard()
            if(!error)
                sumNumber()
            else
                tv_result.text = ""
        }

        btnMin.setOnClickListener {
            resetStatus()
            checkInput()
            hideKeyboard()
            if(!error)
                minNumber()
            else
                tv_result.text = ""
        }

        btnDiv.setOnClickListener {
            resetStatus()
            checkInput()
            hideKeyboard()
            if(!error)
                divNumber()
            else
                tv_result.text = ""

        }

        btnMultiple.setOnClickListener {
            resetStatus()
            checkInput()
            hideKeyboard()
            if(!error)
                multipleNumber()
            else
                tv_result.text = ""
        }
    }

    fun hideKeyboard() {
        // Check if no view has focus:
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    private fun sumNumber(){
        when (status) {
            1 -> {
                result = sumNumbers(edBil1.text.toString().toDouble(), edBil2.text.toString().toDouble(), edBil3.text.toString().toDouble())
            }
            2 -> {
                result = sumNumbers(edBil1.text.toString().toDouble(), edBil2.text.toString().toDouble())
            }
            3 -> {
                result = sumNumbers(edBil2.text.toString().toDouble(), edBil3.text.toString().toDouble())
            }
            4 -> {
                result = sumNumbers(edBil1.text.toString().toDouble(), edBil3.text.toString().toDouble())
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
                result = edBil1.text.toString().toDouble() - edBil2.text.toString().toDouble() - edBil3.text.toString().toDouble()
            }
            2 -> {
                result = edBil1.text.toString().toDouble() - edBil2.text.toString().toDouble()
            }
            3 -> {
                result = edBil2.text.toString().toDouble() - edBil3.text.toString().toDouble()
            }
            4 -> {
                result = edBil1.text.toString().toDouble() - edBil3.text.toString().toDouble()
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
                result = edBil1.text.toString().toDouble() / edBil2.text.toString().toDouble() / edBil3.text.toString().toDouble()
            }
            2 -> {
                result = edBil1.text.toString().toDouble() / edBil2.text.toString().toDouble()
            }
            3 -> {
                result = edBil2.text.toString().toDouble() / edBil3.text.toString().toDouble()
            }
            4 -> {
                result = edBil1.text.toString().toDouble() / edBil3.text.toString().toDouble()
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
                result = edBil1.text.toString().toDouble() * edBil3.text.toString().toDouble()
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
        if(checkbox1.isChecked && checkbox2.isChecked && checkbox3.isChecked){
            when {
                edBil1.text.toString() == "" -> {
                    showMessageError(NUMBER_INPUT_1)
                    error = !error
                }
                edBil2.text.toString() == "" -> {
                    showMessageError(NUMBER_INPUT_2)
                    error = !error
                }
                edBil3.text.toString() == "" -> {
                    showMessageError(NUMBER_INPUT_3)
                    error = !error
                }
                else -> {
                    status = 1
                }
            }
        }else if(checkbox1.isChecked && checkbox2.isChecked){
            when {
                edBil1.text.toString() == "" -> {
                    showMessageError(NUMBER_INPUT_1)
                    error = !error
                }
                edBil2.text.toString() == "" -> {
                    showMessageError(NUMBER_INPUT_2)
                    error = !error
                }
                else -> {
                    status = 2
                }
            }

        }else if(checkbox2.isChecked && checkbox3.isChecked){
            when {
                edBil2.text.toString() == "" -> {
                    showMessageError(NUMBER_INPUT_2)
                    error = !error
                }
                edBil3.text.toString() == "" -> {
                    showMessageError(NUMBER_INPUT_3)
                    error = !error
                }
                else -> {
                    status = 3
                }
            }
        }else if(checkbox1.isChecked && checkbox3.isChecked){
            when {
                edBil1.text.toString() == "" -> {
                    showMessageError(NUMBER_INPUT_1)
                    error = !error
                }
                edBil3.text.toString() == "" -> {
                    showMessageError(NUMBER_INPUT_3)
                    error = !error
                }
                else -> {
                    status = 4
                }
            }
        } else{
            status = 5
        }
    }

    private fun resetStatus(){
        status = 5
        error = false
    }

    private fun result(){
        if(error){
            showMessageError("kemungkinan anda belum memasukan bilangan atau hanya memasukan 1 buah bilangan")
            tv_result.text = ""
        }else{
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            tv_result.text = df.format(result)
        }
    }


    private fun showMessageError(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }











}