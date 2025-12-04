package com.alvaro.aplikasipertama// Sesuaikan dengan package name project Anda

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvDisplay: TextView

    // Variabel untuk menyimpan logika kalkulasi
    private var firstOperand: Double = 0.0
    private var currentOperator: String = ""
    private var isNewOperation: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        tvDisplay = findViewById(R.id.tv_display)

        // Inisialisasi semua tombol dan set listener
        val buttons = listOf(
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
            R.id.btn_dot, R.id.btn_plus, R.id.btn_minus, R.id.btn_multiply,
            R.id.btn_divide, R.id.btn_equals, R.id.btn_ac, R.id.btn_delete
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        // Safety check jika view null
        val button = view as? Button ?: return

        when (button.id) {
            // Tombol Angka (0-9) dan Titik (.)
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_dot -> {
                handleNumberInput(button.text.toString())
            }

            // Tombol Operator (+, -, X, /)
            R.id.btn_plus, R.id.btn_minus, R.id.btn_multiply, R.id.btn_divide -> {
                handleOperatorInput(button.text.toString())
            }

            // Tombol Sama Dengan (=)
            R.id.btn_equals -> {
                calculateResult()
            }

            // Tombol AC (All Clear)
            R.id.btn_ac -> {
                resetCalculator()
            }

            // Tombol DEL (Hapus satu karakter)
            R.id.btn_delete -> {
                deleteLastCharacter()
            }
        }
    }

    private fun handleNumberInput(digit: String) {
        if (isNewOperation) {
            // Jika ini input baru setelah operator ditekan, ganti teks layar
            tvDisplay.text = ""
            isNewOperation = false
        }

        val currentText = tvDisplay.text.toString()

        // Mencegah multiple titik (misal: 1.2.3)
        if (digit == "." && currentText.contains(".")) {
            return
        }

        // Mencegah 0 di depan (kecuali 0.)
        if (currentText == "0" && digit != ".") {
            tvDisplay.text = digit
        } else {
            tvDisplay.append(digit)
        }
    }

    private fun handleOperatorInput(operator: String) {
        // Simpan angka yang ada di layar saat ini sebagai operand pertama
        // toDoubleOrNull mencegah crash jika string kosong
        val currentValue = tvDisplay.text.toString().toDoubleOrNull()

        if (currentValue != null) {
            firstOperand = currentValue
        }

        currentOperator = operator
        isNewOperation = true
    }

    private fun calculateResult() {
        // Ambil angka kedua dari layar
        val secondOperandText = tvDisplay.text.toString()
        val secondOperand = secondOperandText.toDoubleOrNull()

        // Jika input tidak valid, hentikan proses
        if (secondOperand == null) return

        var result = 0.0

        // Lakukan perhitungan berdasarkan operator
        when (currentOperator) {
            "+" -> result = firstOperand + secondOperand
            "-" -> result = firstOperand - secondOperand
            "X" -> result = firstOperand * secondOperand
            "/" -> {
                // Pengecekan manual untuk pembagian dengan nol (pengganti try-catch)
                if (secondOperand != 0.0) {
                    result = firstOperand / secondOperand
                } else {
                    // Tampilkan pesan Error atau tetap 0
                    tvDisplay.text = "Error"
                    isNewOperation = true
                    return
                }
            }
            else -> return // Jika tidak ada operator, tidak lakukan apa-apa
        }

        // Tampilkan hasil
        // Cek apakah hasilnya bilangan bulat (misal 5.0 jadi 5)
        if (result % 1 == 0.0) {
            tvDisplay.text = result.toInt().toString()
        } else {
            tvDisplay.text = result.toString()
        }

        // Siapkan untuk operasi berantai selanjutnya
        isNewOperation = true
    }

    private fun resetCalculator() {
        tvDisplay.text = "0"
        firstOperand = 0.0
        currentOperator = ""
        isNewOperation = true
    }

    private fun deleteLastCharacter() {
        val currentText = tvDisplay.text.toString()
        if (currentText.isNotEmpty() && currentText != "Error") {
            if (currentText.length == 1) {
                tvDisplay.text = "0"
                isNewOperation = true
            } else {
                tvDisplay.text = currentText.dropLast(1)
            }
        }
    }
}