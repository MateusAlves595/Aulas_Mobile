package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.StudentList
import retrofit2.Call
import retrofit2.http.GET

interface StudentService {

    @GET("aluno")
    fun getStudentByRegystration(): Call<StudentList>
}