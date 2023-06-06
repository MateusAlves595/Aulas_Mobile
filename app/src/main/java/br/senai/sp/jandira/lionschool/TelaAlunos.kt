package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class TelaAlunos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                StudentScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable

fun StudentScreen() {

    val context = LocalContext.current

    val listStudent by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Students>())
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
                            text = stringResource(id = R.string.system_development),
                            fontSize = 35.sp,
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
                            onClick = { /*TODO*/ },
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
                            onClick = { /*TODO*/ },
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

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Card(
                            modifier = Modifier
                                .width(330.dp)
                                .height(120.dp)
                                .padding(bottom = 10.dp),
                            backgroundColor = colorResource(id = R.color.azul),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(width = 3.dp, color = Color(255, 194, 61)),
                        ) {

                        }
                    }

                }
            }
    }
}