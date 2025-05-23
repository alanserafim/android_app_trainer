package br.com.puc.trainer.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class para representar um Treino
// Usando os nomes de propriedade do seu arquivo Treino.kt
data class Treino(
    val id: Int, // Adicionando um ID para facilitar o gerenciamento na lista
    val titulo: String,
    val descricao: String,
    val exercicios: Int, // Mantendo o nome como no seu model
    val tempo: String     // Mantendo o nome como no seu model
)

class WorkoutsActivity : ComponentActivity() { // Renomeando a Activity para clareza
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aplicando um tema Material3 básico para melhor visualização
            MaterialTheme {
                ListaTreinosScreen(
                    onAdicionarTreinoClick = {
                        // Lógica para quando o botão de adicionar treino for clicado
                        // Por exemplo, navegar para uma tela de cadastro
                        println("Botão Adicionar Treino clicado!")
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTreinosScreen(onAdicionarTreinoClick: () -> Unit) {
    // Lista de treinos (mocada para este exemplo)
    // Em uma aplicação real, você obteria isso de um ViewModel ou outra fonte de dados
    val treinos = remember {
        mutableStateListOf(
            Treino(1, "Aquecimento Completo", "Prepara o corpo para o treino intenso.", 5, "10 min"),
            Treino(2, "Força Total - Peito e Tríceps", "Foco em supino, flexões e extensões de tríceps.", 8, "60 min"),
            Treino(3, "Resistência - Pernas e Abdômen", "Agachamentos, avanços e pranchas.", 7, "45 min"),
            Treino(4, "Cardio Intenso", "Corrida intervalada e burpees para queimar calorias.", 4, "30 min"),
            Treino(5, "Resistência - Pernas e Abdômen", "Agachamentos, avanços e pranchas.", 7, "45 min"),
            Treino(6, "Resistência - Pernas e Abdômen", "Agachamentos, avanços e pranchas.", 7, "45 min")

        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meus Treinos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdicionarTreinoClick) {
                Icon(Icons.Filled.Add, contentDescription = "Cadastrar novo treino")
            }
        }
    ) { innerPadding ->
        if (treinos.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Nenhum treino cadastrado ainda.",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 8.dp), // Padding para a lista
                verticalArrangement = Arrangement.spacedBy(12.dp) // Espaçamento entre os cards
            ) {
                items(treinos, key = { it.id }) { treino -> // Usando o ID como chave para melhor performance
                    TreinoCard(treino = treino)
                }
            }
        }
    }
}

@Composable
fun TreinoCard(treino: Treino) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = treino.titulo,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = treino.descricao,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                InfoTreinoItem(icon = Icons.Filled.Clear, label = "Exercícios", value = treino.exercicios.toString())
                InfoTreinoItem(icon = Icons.Filled.Clear, label = "Duração", value = treino.tempo)
            }
        }
    }
}

@Composable
fun InfoTreinoItem(icon: ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f) // Levemente mais claro
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun ListaTreinosScreenPreview() {
    MaterialTheme {
        ListaTreinosScreen(onAdicionarTreinoClick = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ListaTreinosScreenVaziaPreview() {
    MaterialTheme {
        // Para simular a lista vazia, podemos passar uma função que não adiciona itens
        // ou criar um estado específico para o preview, mas para simplificar:
        val treinosDePreview = remember { mutableStateListOf<Treino>() }
        Scaffold(
            topBar = { TopAppBar(title = { Text("Meus Treinos") }) },
            floatingActionButton = { FloatingActionButton(onClick = {}) { Icon(Icons.Filled.Add, "Cadastrar") } }
        ) { innerPadding ->
            if (treinosDePreview.isEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Nenhum treino cadastrado ainda.", style = MaterialTheme.typography.headlineSmall)
                }
            } else {
                LazyColumn(modifier = Modifier.padding(innerPadding)) { /* ... */ }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TreinoCardPreview() {
    MaterialTheme {
        TreinoCard(
            treino = Treino(0, "Treino Card Teste", "Descrição detalhada do treino para visualização no preview.", 8, "50 min")
        )
    }
}