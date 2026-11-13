package com.example.imc

import kotlin.math.pow

fun calcularIMC(altura: Double, peso: Double): Double{
    return peso/altura.pow(2.0)
}