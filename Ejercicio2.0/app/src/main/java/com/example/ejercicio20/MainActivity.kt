package com.example.ejercicio20

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declaración de variables para los elementos de la interfaz de usuario
    private lateinit var inputName: EditText
    private lateinit var inputSalary: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var textViewResult: TextView

    // Método que se llama cuando la actividad se crea por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de los elementos de la interfaz de usuario utilizando findViewById
        inputName = findViewById(R.id.inputName)
        inputSalary = findViewById(R.id.inputSalary)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        textViewResult = findViewById(R.id.textViewResult)

        // Configuración del listener para el botón, que llama al método calculate
        buttonCalculate.setOnClickListener { calculateSalary() }
    }

    // Método que realiza el cálculo de los descuentos y muestra el resultado
    private fun calculateSalary() {
        val name = inputName.text.toString()
        val salaryText = inputSalary.text.toString()

        if (name.isEmpty() || salaryText.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu nombre y salario base", Toast.LENGTH_SHORT).show()
            return
        }

        val salary = salaryText.toDouble()
        var renta = 0.0

        // Cálculo de Renta basado en la tabla proporcionada
        renta = when {
            salary > 2038.11 -> (salary - 2038.10) * 0.30 + 288.57
            salary > 895.24 -> (salary - 895.24) * 0.20 + 60.00
            salary > 472.00 -> (salary - 472.00) * 0.10 + 17.67
            else -> 0.0
        }

        // Cálculo de AFP y ISSS
        val afp = salary * 0.0725
        val isss = salary * 0.03

        // Cálculo del salario neto
        val salarioNeto = salary - renta - afp - isss

        // Actualización del TextView para mostrar el resultado
        val result = """
            Nombre: $name
            Salario Base: $salary
            Renta: $renta
            AFP (7.25%): $afp
            ISSS (3%): $isss
            Salario Neto: $salarioNeto
        """.trimIndent()

        textViewResult.text = result
    }
}
