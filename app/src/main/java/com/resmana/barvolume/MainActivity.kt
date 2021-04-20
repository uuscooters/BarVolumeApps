package com.resmana.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

//    private lateinit var merupakan deklarasi secara global agar bisa dikenal oleh bagian kelasnya
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView


    companion object {
        private const val STATE_RESULT = "state_result"
    }

// Metode utk menyimpan data sementara ketika sebuah orientasi berubah
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// Casting View
        edtWidth = edt_width
        edtHeight = edt_height
        edtLength = edt_length
        btnCalculate = btn_calculate
        tvResult = tv_result

        btnCalculate.setOnClickListener(this)

// pengecekan sebuah bundle savedInsranceState dari data yg sudah di simpan
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calculate){
//  mengambil value dari editText
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

// Cek inputan yg kosong
            var isEmptyFields = false
            when {
                inputLength.isEmpty() -> {
                    isEmptyFields = true
                    edtLength.error = "Field ini tidak boleh kosong"
                }

                inputWidth.isEmpty() -> {
                    isEmptyFields = true
                    edtWidth.error = "Field ini tidak boleh kosong"
                }

                inputHeight.isEmpty() -> {
                    isEmptyFields = true
                    edtHeight.error = "Field ini tidak boleh kosong"
                }
            }

// Menampilkan data ke TextView
            if(!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = volume.toString()
            }
        }
    }
}