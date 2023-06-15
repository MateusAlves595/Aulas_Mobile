package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.Students
import br.senai.sp.jandira.lionschool.model.StudentsList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaAlunos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                val siglaCurso = intent.getStringExtra("sigla")
                val nomeCurso = intent.getStringExtra("nome")
                StudentScreen(siglaCurso.toString(), nomeCurso.toString())
            }
        }
    }
}

@Composable

fun StudentScreen(curso : String, nomeCurso : String) {

    val context = LocalContext.current

    var listStudent by remember {
        mutableStateOf(listOf<Students>())
    }

    var listStudents2 by remember {
        mutableStateOf(listOf<Students>())
    }

    val call = RetrofitFactory().getStudentService().getStudentsByCourse(curso)

    call.enqueue(object : Callback<StudentsList>{
        override fun onResponse(
            call: Call<StudentsList>,
            response: Response<StudentsList>
        ) {
            listStudent = response.body()!!.alunos
            listStudents2  =response.body()!!.alunos
        }

        override fun onFailure(call: Call<StudentsList>, t: Throwable) {
        }
    })

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
                modifier = Modifier.fillMaxHeight()
        ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "",
                        modifier = Modifier
                            .height(60.dp)
                            .width(100.dp)
                    )
                }

                Divider(
                    modifier = Modifier
                        .height(3.dp)
                    ,
                    color = Color(255, 194, 61)
                )

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = nomeCurso,
                            fontSize = 25.sp,
                            color = colorResource(id = R.color.azul),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Divider(
                        modifier = Modifier.height(3.dp),
                        color = Color(255, 194, 61)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = stringResource(id = R.string.status),
                            fontSize = 25.sp,
                            color = colorResource(id = R.color.azul),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 20.dp, top = 15.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Button(
                            onClick = {
                                      listStudents2 = listStudent.filter { it.status == "Cursando" }
                            },
                            modifier = Modifier
                                .width(150.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(Color(51, 71, 176)),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(width = 3.dp, color = Color(255, 194, 61))
                        ) {
                            Text(
                                text = stringResource(id = R.string.studying),
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(30.dp))

                        Button(
                            onClick = {
                                      listStudents2 = listStudent.filter { it.status == "Finalizado" }
                            },
                            modifier = Modifier
                                .width(150.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(Color(51, 71, 176)),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(width = 3.dp, color = Color(255, 194, 61))
                        ) {
                            Text(
                                text = stringResource(id = R.string.finished),
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        }
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        items(listStudents2){
                            Card(
                                modifier = Modifier
                                    .width(330.dp)
                                    .height(120.dp)
                                    .padding(bottom = 10.dp)
                                    .clickable {
                                               val showStudent =
                                                   Intent(context, TelaAluno::class.java)
                                               showStudent.putExtra("matricula", it.matricula)
                                               showStudent.putExtra("nome", it.nome)
                                               context.startActivity(showStudent)
                                    }
                                ,
                                backgroundColor = colorResource(id = R.color.azul),
                                shape = RoundedCornerShape(15.dp),
                                border = BorderStroke(width = 3.dp, color = Color(255, 194, 61))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = it.foto,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(80.dp)
                                            .width(80.dp)
                                            .padding(end = 10.dp)
                                    )
                                    Column(
                                        modifier = Modifier
                                            .width(200.dp)
                                            .height(80.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(40.dp),
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = it.nome,
                                                color = Color.White,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Normal,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(40.dp)
                                            )
                                        }
                                        
                                        Row() {
                                            Text(
                                                text = it.status,
                                                color = Color.White,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
             }
          }
       }