package com.josegrillo.marvelapi.utils

fun String.makeSecurePath() = if (this.startsWith("http://")) {
    this.replace("http://", "https://")
} else {
    this
}