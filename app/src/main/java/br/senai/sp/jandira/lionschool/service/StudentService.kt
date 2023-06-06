package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.StudentsList
import retrofit2.http.GET
import retrofit2.Call

interface StudentService {

    @GET("alunos")
    fun getStudents(): Call<StudentsList>

}