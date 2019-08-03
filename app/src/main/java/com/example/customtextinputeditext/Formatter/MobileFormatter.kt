package com.example.customtextinputeditext.Formatter

import android.util.Log

class MobileFormatter {

    companion object {

        private val specialCharacterSet = listOf<String>("<",">",";",":","\'","\"","|","[","]","{","}","(",")","/","\\","~","!","@","#","$","%","^","&","*","_","-","=","?",".","`"," ","ñ","Ñ","/")

        /*
        '<', '>', ';', ':', '\'', '"', '|', '[', ']', '{', '}', '(', ')', '/', '\\',
            '~','!','@','#','$','%','^','&','*','_','+','-','=','?','.','`',' ','ñ','Ñ','/'
        */

        fun formatContact(mobileNumber : String) : String{
            Log.d("MobileFormatter","mobileNumber")

            var filteredNumber = mobileNumber
            specialCharacterSet.map {
                filteredNumber = filteredNumber.replace(it,"").trim()
                Log.d("MobileFormatter","${it}-${filteredNumber}")
            }
            filteredNumber.replace("+639", "0")

            return if (filteredNumber.contains("+639")) {
                Log.d("MobileFormatter","if")
                "09${filteredNumber.substring(4)}"
            }
            else {
                Log.d("MobileFormatter","else")
                filteredNumber
            }
        }
    }
}