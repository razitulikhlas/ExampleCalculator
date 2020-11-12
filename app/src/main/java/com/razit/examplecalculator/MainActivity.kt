package com.razit.examplecalculator

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var error = false
    private var result : Double? = null
    private var bil1 = 0.0
    private var bil2 = 0.0
    private var bil3 = 0.0

    companion object{
        const val NUMBER_INPUT_1 = "Bilangan pertama wajib di isi"
        const val NUMBER_INPUT_2 = "Bilangan kedua wajib di isi"
        const val NUMBER_INPUT_3 = "Bilangan ketiga wajib di isi"
        const val SUM = "tambah"
        const val MIN = "kurang"
        const val DIV = "bagi"
        const val MULTIPLE = "kali"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            operator(SUM)
        }

        btnMin.setOnClickListener {
            operator(MIN)
        }

        btnDiv.setOnClickListener {
            operator(DIV)

        }

        btnMultiple.setOnClickListener {
            operator(MULTIPLE)
        }
    }

    private fun hideKeyboard() {
        // Check if no view has focus:
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun operator(operator : String){
        reset()
        hideKeyboard()
        if(checkbox1.isChecked ){
            if(edBil1.text.isNullOrEmpty()){
                showMessageError(NUMBER_INPUT_1)
                return
            } else{
                bil1 = edBil1.text.toString().toDouble()
            }
        }

        if(checkbox2.isChecked){
            if(edBil2.text.isNullOrEmpty()){
                showMessageError(NUMBER_INPUT_2)
                return
            } else{
                bil2 = edBil2.text.toString().toDouble()
            }
        }

        if(checkbox3.isChecked){
            if(edBil3.text.isNullOrEmpty()){
                showMessageError(NUMBER_INPUT_3)
                return
            } else{
                bil3 = edBil3.text.toString().toDouble()
            }
        }

        if(operator == SUM){
            result = bil1 + bil2 + bil3
        }


        if(checkbox1.isChecked && checkbox2.isChecked && checkbox3.isChecked){
            when (operator) {
                DIV -> {
                    result = bil1 / bil2 / bil3
                }
                MULTIPLE -> {
                    result = bil1 * bil2 * bil3
                }
                MIN -> {
                    result = bil1 - bil2 - bil3
                }
            }
        }else if(checkbox1.isChecked && checkbox2.isChecked){
            when (operator) {
                DIV -> {
                    result = bil1 / bil2
                }
                MULTIPLE -> {
                    result = bil1 * bil2
                }
                MIN -> {
                    result = bil1 - bil2
                }
            }
        }else if(checkbox1.isChecked && checkbox3.isChecked){
            when (operator) {
                DIV -> {
                    result = bil1 / bil3
                }
                MULTIPLE -> {
                    result = bil1 * bil3
                }
                MIN -> {
                    result = bil1 - bil3
                }
            }
        }else if(checkbox2.isChecked && checkbox3.isChecked){
            when (operator) {
                DIV -> {
                    result = bil2 / bil3
                }
                MULTIPLE -> {
                    result = bil2 * bil3
                }
                MIN -> {
                    result = bil2 - bil3
                }
            }
        }else{
            error = !error
        }

        if(error){
            showMessageError("Silahkan ceklis dua buah checkbox untuk melakukan proses perhitungan")
            tv_result.text = ""
        }else{
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            tv_result.text = df.format(result)
        }
    }

    private fun reset(){
        bil1 = 0.0
        bil2 = 0.0
        bil3 = 0.0
        error = false
    }

    private fun showMessageError(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}