package com.alvaro.aplikasipertama

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.Locale

class NotaCoffeeshop : AppCompatActivity() {
    private lateinit var tvCustomer: TextView
    private lateinit var tvItem: TextView
    private lateinit var tvJumlah: TextView
    private lateinit var tvSubtotal: TextView
    private lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota_coffeeshop)

        try {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        } catch (e: Exception) { }

        init()
        getData()
    }

    private fun init() {

        tvCustomer = findViewById(R.id.tvCust)
        tvItem = findViewById(R.id.tvItem1)
        tvJumlah = findViewById(R.id.tvJml1)
        tvSubtotal = findViewById(R.id.tvSubTotal)
        tvTotal = findViewById(R.id.tvTotal)
    }

    private fun getData() {
        val customer = intent.getStringExtra("Nama") ?: "Guest"

        val qtyAmric = intent.getIntExtra("QtyAmericano", 0)
        val qtyLatte = intent.getIntExtra("QtyLatte", 0)
        val qtyToast = intent.getIntExtra("QtyToast", 0)
        val qtyDonut = intent.getIntExtra("QtyDonut", 0)

        val optAmric = intent.getStringExtra("OptAmericano") ?: "Ice"
        val optLatte = intent.getStringExtra("OptLatte") ?: "Ice"
        val optToast = intent.getStringExtra("OptToast") ?: "-"
        val optDonut = intent.getStringExtra("OptDonut") ?: "-"

        val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))

        val items = mutableListOf<String>()
        val jumlah = mutableListOf<Int>()
        val subtotal = mutableListOf<Int>()

        if (qtyAmric > 0) {
            items.add("Americano ($optAmric)")
            jumlah.add(qtyAmric)
            subtotal.add(qtyAmric * 16000)
        }
        if (qtyLatte > 0) {
            items.add("Caffe Latte ($optLatte)")
            jumlah.add(qtyLatte)
            subtotal.add(qtyLatte * 19000)
        }
        if (qtyToast > 0) {
            items.add("Toast ($optToast)")
            jumlah.add(qtyToast)
            subtotal.add(qtyToast * 20000)
        }
        if (qtyDonut > 0) {
            items.add("Donut ($optDonut)")
            jumlah.add(qtyDonut)
            subtotal.add(qtyDonut * 10000)
        }

        tvCustomer.text = "Order By: $customer"

        if (items.isEmpty()) {
            tvItem.text = "Tidak ada pesanan"
            tvJumlah.text = ""
            tvSubtotal.text = ""
            tvTotal.text = "TOTAL : Rp 0"
        } else {
            tvItem.text = items.joinToString("\n\n")
            tvJumlah.text = jumlah.joinToString("\n\n")
            tvSubtotal.text = subtotal.joinToString("\n\n") { "Rp ${formatter.format(it)}" }

            val total = subtotal.sum()
            tvTotal.text = "TOTAL : Rp ${formatter.format(total)}"
        }
    }
}