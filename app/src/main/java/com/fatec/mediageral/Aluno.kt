package com.fatec.mediageral

data class Aluno(
    var nome: String,
    var notas: MutableList<Double> = mutableListOf()
){
    fun calculoMedia():Double{
        if (notas.isNotEmpty()){
            val media:Double = notas.sum() / 3
            return media
        }else{
            return 0.0
        }
    }
    fun retornoDesempenho():String{
        val resultado = calculoMedia()
        if(resultado < 6.0){
            return "Reprovado"
        }else if(resultado >= 6.0 && resultado <= 9.0 ){
            return "Aprovado"
        }else{
            return "Ótimo Aproveitamento"
        }
    }
}
