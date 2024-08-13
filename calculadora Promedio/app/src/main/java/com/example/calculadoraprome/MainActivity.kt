package com.example.calculadoraprome

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declaración de variables para los elementos de la interfaz de usuario
    private lateinit var studentNameInput: EditText
    private lateinit var note1Input: EditText
    private lateinit var note2Input: EditText
    private lateinit var note3Input: EditText
    private lateinit var note4Input: EditText
    private lateinit var note5Input: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de los elementos de la interfaz de usuario
        studentNameInput = findViewById(R.id.studentNameInput)
        note1Input = findViewById(R.id.note1Input)
        note2Input = findViewById(R.id.note2Input)
        note3Input = findViewById(R.id.note3Input)
        note4Input = findViewById(R.id.note4Input)
        note5Input = findViewById(R.id.note5Input)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        // Configuración del listener para el botón
        calculateButton.setOnClickListener { computeFinalGrade() }
    }

    // Método para calcular el promedio del estudiante
    private fun computeFinalGrade() {
        // Recolección de datos ingresados
        val studentName = studentNameInput.text.toString()
        val note1 = note1Input.text.toString().toDoubleOrNull()
        val note2 = note2Input.text.toString().toDoubleOrNull()
        val note3 = note3Input.text.toString().toDoubleOrNull()
        val note4 = note4Input.text.toString().toDoubleOrNull()
        val note5 = note5Input.text.toString().toDoubleOrNull()

        // Validación de los datos
        if (studentName.isEmpty() || note1 == null || note2 == null || note3 == null || note4 == null || note5 == null) {
            Toast.makeText(this, getString(R.string.error_missing_data), Toast.LENGTH_SHORT).show()
            return
        }

        if (note1 !in 0.0..10.0 || note2 !in 0.0..10.0 || note3 !in 0.0..10.0 || note4 !in 0.0..10.0 || note5 !in 0.0..10.0) {
            Toast.makeText(this, getString(R.string.error_invalid_grades), Toast.LENGTH_SHORT).show()
            return
        }

        // Cálculo del promedio ponderado
        val finalGrade = (note1 * 0.15) + (note2 * 0.15) + (note3 * 0.20) + (note4 * 0.25) + (note5 * 0.25)

        // Determinación del estado del estudiante
        val status = if (finalGrade >= 6.0) "Aprobado" else "Reprobado"

        // Mostrar el resultado
        resultTextView.text = getString(R.string.final_result_message, studentName, finalGrade, status)
    }
}
