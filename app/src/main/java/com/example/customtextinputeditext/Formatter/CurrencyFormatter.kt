package com.example.customtextinputeditext.Formatter

import java.math.BigDecimal
import java.text.NumberFormat

class CurrencyFormatter {

    companion object {

        fun formatCurrency(string: String?) : String {
            return if(string == null || string.length == 0) return "0.00"
            else formatCurrency(string.toBigDecimal())
        }

        fun formatCurrency(int: Int?) : String {

            if(int == null) return ""
            return formatCurrency(int.toBigDecimal())
        }

        fun formatCurrency(float: Float?) : String {

            if(float == null) return ""
            return formatCurrency(float.toBigDecimal())
        }

        fun formatCurrency(double: Double?) : String {
            if(double == null) return ""
            return formatCurrency(double.toBigDecimal())
        }

        fun formatCurrency(decimal: BigDecimal) : String {
            val currencyFormat = NumberFormat.getInstance()
            currencyFormat.minimumFractionDigits = 2
            currencyFormat.maximumFractionDigits = 2
            return currencyFormat.format(decimal)
        }

        fun formatCurrencyCodePhP(string: String?, capitalize : Boolean) : String{
            return if(capitalize) "PHP ${formatCurrency(
                string
            )}" else "PHP ${formatCurrency(
                string
            )}"
        }

        fun formatCurrencyCode(amount: Double?, currency: String?) : String {
            return "$currency ${formatCurrency(
                amount
            )}"
        }

        fun formatCurrencyCode(amount: String?, currency: String?) : String {
            //return "$currency ${formatCurrency(amount?.replace(",",""))}"
            //return "$currency ${formatCurrency(amount?.replace("[^A-Za-z0-9]".toRegex(),""))}"
            return formatCurrencyCode(
                amount?.toDouble(),
                currency
            )
        }

        fun formatAmount(amount: String?, currency: String?) : String {
            //return "$currency ${formatCurrency(amount?.replace(",",""))}"
            return "$currency ${formatCurrency(
                amount?.replace(",", "")
            )}"
            //return formatCurrencyCode(amount?.toDouble(), currency)
        }

        fun formatWholeNumber(number: String?) : String {
            return number?.substring(0,number.indexOf("."))?:"0"
        }
    }
}