package com.example.nbaapp.utils

fun translateConference(conference: String): String {
    return when (conference) {
        "East" -> "Восточная"
        "West" -> "Западная"
        else -> conference
    }
}

fun translateDivision(division: String): String {
    return when (division) {
        "Atlantic" -> "Атлантический"
        "Central" -> "Центральный"
        "Southeast" -> "Юго-Восточный"
        "Northwest" -> "Северо-Западный"
        "Pacific" -> "Тихоокеанский"
        "Southwest" -> "Юго-Западный"
        else -> division
    }
}

fun translatePosition(position: String): String {
    return when (position) {
        "G" -> "Защитник"
        "F" -> "Форвард"
        "C" -> "Центровой"
        "SG" -> "Атакующий защитник"
        "PG" -> "Разыгрывающий защитник"
        "F-G" -> "Форвард-Защитник"
        "G-F" -> "Защитник-Форвард"
        "C-F" -> "Центровой-Форвард"
        "F-C" -> "Форвард-Центровой"
        else -> position
    }
}