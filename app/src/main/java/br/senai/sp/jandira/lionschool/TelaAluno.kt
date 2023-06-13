package br.senai.sp.jandira.lionschool

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class TelaAluno : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme() {
                val matriculaAluno = intent.getStringExtra("matricula")
                StudentInfoScreen(matriculaAluno.toString())
            }
        }
    }
}

@Composable
fun StudentInfoScreen(matricula : String) {

}