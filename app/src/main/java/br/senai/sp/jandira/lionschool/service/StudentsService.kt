package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.StudentsList
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface StudentsService {

    @GET("alunos")
    fun getStudentsByCourse(@Query ("curso") sigla : String): Call<StudentsList>
}