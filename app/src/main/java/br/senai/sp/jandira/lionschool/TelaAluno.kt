package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.StudentNotes
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaAluno : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme() {
                val matriculaAluno = intent.getStringExtra("matricula")
                val nomeAluno = intent.getStringExtra("nome")
                StudentInfoScreen(matriculaAluno.toString(), nomeAluno.toString())
            }
        }
    }
}

@Composable
fun StudentInfoScreen(matricula : String, nome : String) {

    var studentNotes by remember {
        mutableStateOf(StudentNotes("","","","", emptyList()))
    }

    val call = RetrofitFactory().getStudentNotes().getStudentByMatriculaNotes(matricula)

    call.enqueue(object : Callback<StudentNotes> {
        override fun onResponse(
            call: Call<StudentNotes>,
            response: Response<StudentNotes>
        ) {
            if(response.isSuccessful){
                val studentResponse = response.body()
                if(studentResponse != null){
                    studentNotes = studentResponse
                }
            }

        }
        override fun onFailure(call: Call<StudentNotes>, t: Throwable) {
        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
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
            modifier = Modifier.height(3.dp),
            color = Color(255, 194, 61)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Column() {
            Card(
                modifier = Modifier
                    .size(width = 300.dp, height = 150.dp),
                backgroundColor = Color(51, 71, 176, 255),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(width = 3.dp, color = Color(255, 194, 61))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    AsyncImage(
                        model = studentNotes.foto,
                        contentDescription = "Student photo",
                        modifier = Modifier
                            .size(80.dp)
                    )

                    Text(text = studentNotes.nome.uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        color = Color(255, 255, 255, 255)
                    )

                    Row(
                    ) {

                        Spacer(modifier = Modifier.width(2.dp))

                        Text(text = studentNotes.status,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            color = Color(255, 255, 255, 255)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Card(
            modifier = Modifier
                .size(width = 300.dp, height = 400.dp),
            backgroundColor = Color(229, 228, 228, 255),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 3.dp, Color(254, 193, 62, 255))
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(studentNotes.disciplinas) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = it.sigla,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                        Surface(

                        ) {
                            Card(
                                modifier = Modifier
                                    .height(10.dp)
                                    .width(100.dp),
                                backgroundColor = Color.Gray
                            ) {
                            }
                            Card(
                                modifier = Modifier
                                    .height(10.dp)
                                    .width(it.media.toDouble().dp),
                                backgroundColor = Color(51, 71, 176, 255)
                            ) {
                            }
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = it.media,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}