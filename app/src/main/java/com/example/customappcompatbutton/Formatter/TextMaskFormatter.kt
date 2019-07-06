package com.example.customappcompatbutton.Formatter

class TextMaskFormatter() {

    companion object {

        fun maskText(input: String?, minLength: Int) : String {
            var TextToBeReturn = "•••••••••"

            if(input.isNullOrEmpty()) return  ""
            else if(input?.length!! > 3)
                TextToBeReturn += input.substring(input.length - minLength)
            else
                TextToBeReturn += input

            return TextToBeReturn
        }

        fun maskTextStar(input: String?, minLength: Int) : String {
            var TextToBeReturn = "*********"

            if(input.isNullOrEmpty()) return  ""
            else if(input?.length!! > 3)
                TextToBeReturn += input.substring(input.length - minLength)
            else
                TextToBeReturn += input

            return TextToBeReturn
        }

        fun maskStar(input: String?, minLength: Int) : String{
            var TextToBeReturn = "********"

            if(input.isNullOrEmpty()) return  ""
            else if(input?.length!! > 3) return  TextToBeReturn + input.substring(input.length - minLength)
            else return TextToBeReturn + input
        }
    }
}