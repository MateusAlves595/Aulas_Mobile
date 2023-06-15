package br.senai.sp.jandira.lionschool.model

data class StudentNotes(
    val foto: String,
    val nome: String,
    val matricula: String,
    val status: String,
    val disciplinas: List<Disciplina>
)
