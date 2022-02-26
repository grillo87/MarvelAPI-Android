package com.josegrillo.usecase.utils

fun String.makeSecurePath() = if (this.startsWith("http://")) {
    this.replace("http://", "https://")
} else {
    this
}