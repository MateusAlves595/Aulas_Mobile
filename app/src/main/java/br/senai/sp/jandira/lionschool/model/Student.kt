package br.senai.sp.jandira.lionschool.model

data class Student(
    val id : Long,
    val foto : String,
    val nome : String,
    val sexo : String,
    val matricula : String,
    val curso : String,
    val status  : String
)
