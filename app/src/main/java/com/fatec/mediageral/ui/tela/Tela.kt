package com.fatec.mediageral.ui.tela

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fatec.mediageral.Aluno


@Composable
fun Tela() {
    var nome by remember {mutableStateOf("")}
    var nota1 by remember { mutableStateOf("") }
    var nota2 by remember { mutableStateOf("") }
    var nota3 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var corResultado by remember { mutableStateOf(Color.Transparent) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Cálculo Média Aluno", style = MaterialTheme.typography.titleMedium, fontSize = 24.sp,modifier = Modifier.padding(top = 8.dp))
        TextField(
            value = nome,
            onValueChange = {nome = it},
            label = { Text("Digite o nome do aluno: ") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = nota1,
                onValueChange = { nota1 = it},
                label = { Text("Nota 1") },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = nota2,
                onValueChange = { nota2 = it},
                label = { Text("Nota 2") },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = nota3,
                onValueChange = { nota3 = it},
                label = { Text("Nota 3") },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val aluno = Aluno(nome)
            aluno.notas.addAll(
                listOf(
                    nota1.toDoubleOrNull() ?: 0.0,
                    nota2.toDoubleOrNull() ?: 0.0,
                    nota3.toDoubleOrNull() ?: 0.0
                )
            )
            val media = aluno.calculoMedia()
            val desempenho = aluno.retornoDesempenho()
            resultado = "Aluno: $nome\nMédia: %.2f - ${desempenho}".format(media)

            corResultado = when(desempenho){
                "Reprovado" -> Color.Red
                "Aprovado" -> Color.Yellow
                "Ótimo Aproveitamento" -> Color.Green
                else -> Color.Transparent
            }
        }) {
            Text("Calcular Média")
        }
        if (resultado.isNotEmpty()){
            Box(
                modifier = Modifier.fillMaxWidth()
                    .background(corResultado)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = resultado,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}