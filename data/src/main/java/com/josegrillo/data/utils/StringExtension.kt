package com.josegrillo.data.utils

import java.math.BigInteger
import java.security.MessageDigest

fun String.encrypt(): String =
    BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
        .toString(16).padStart(32, '0')