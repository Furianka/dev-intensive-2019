package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?>{
        val parts : List<String>? = fullName?.trim()?.replace("\\s+".toRegex(), " ")?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return Pair(if (firstName.isNullOrBlank()) null else firstName, if (lastName.isNullOrBlank()) null else lastName)
    }

    fun transliteration(payload: String, divider: String = " "):String{
        return ""
    }

    fun toInitials(firstname: String?, lastname: String?):String{
        return ""
    }
}