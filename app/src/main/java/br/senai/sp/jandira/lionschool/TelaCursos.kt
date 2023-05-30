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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.CoursesList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaCursos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                CoursesScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoursesScreen() {

    var listCourse by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Courses>())
    }

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
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.select_a_course),
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(51, 71, 176),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {

                LazyColumn(
                    modifier = Modifier
                        .width(300.dp)
                        .height(400.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    var call = RetrofitFactory().getCourseService().getCourse()

                    call.enqueue(object : Callback<CoursesList>{
                        override fun onResponse(
                            call: Call<CoursesList>,
                            response: Response<CoursesList>
                        ) {
                            listCourse = response.body()!!.cursos
                        }

                        override fun onFailure(call: Call<CoursesList>, t: Throwable) {
                        }

                    })

                    items(listCourse){
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(bottom = 10.dp),
                            backgroundColor = colorResource(id = R.color.azul),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(width = 3.dp, color = Color(255, 194, 61)),

                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = it.sigla,
                                        color = Color.White,
                                        fontSize = 50.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        letterSpacing = 1.sp
                                    )
                                    AsyncImage(
                                        model = it.icone,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(50.dp)
                                            .padding(10.dp)
                                    )
                                }

                                Divider(
                                    modifier = Modifier
                                        .height(3.dp)
                                        .width(270.dp),
                                    color = Color.White
                                )
                                Text(
                                    text = it.nome,
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                            }


                        }
                    }
                }
            }
        }
    }
}