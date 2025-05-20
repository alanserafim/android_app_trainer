package br.com.puc.trainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.puc.trainer.ui.theme.TrainerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrainerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyfirstComposable(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

@Composable
fun MyfirstComposable(modifier: Modifier = Modifier){
    Text(
        text = "Hello Mad Composable World",
        modifier = modifier
    );

}

@Preview(
    name= "TextPreview",
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun MyfirstComposablePreview(){
    TrainerTheme {
        MyfirstComposable()
    }

}