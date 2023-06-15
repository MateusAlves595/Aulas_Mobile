package br.senai.sp.jandira.lionschool

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                LoginScreen()
            }
        }
    }
}


@SuppressLint("SuspiciousIndentation")
@Preview(showBackground = true)
@Composable
fun LoginScreen() {

    val context = LocalContext.current

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lion_schol_login),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(45.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            var openCourse = Intent(context, TelaCursos::class.java)
                            context.startActivity(openCourse)
                        },
                        modifier = Modifier
                            .width(260.dp)
                            .height(70.dp),
                        colors = ButtonDefaults.buttonColors(Color(51, 71, 176)),
                        shape = RoundedCornerShape(15.dp),
                        border = BorderStroke(width = 3.dp, color = Color(255, 194, 61))
                        ) {
                        Text(
                            text = stringResource(id = R.string.course),
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
}