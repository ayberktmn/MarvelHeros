package com.ayberk.marvelheros.Models

data class HeroItem(
    val color: String,
    val details: List<Detail>,
    val id: Int,
    val name: String,
    val poster: String,
    val quote: String
)