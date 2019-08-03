package com.example.customtextinputeditext.View

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.customtextinputeditext.CustomView.ContactEditText
import com.example.customtextinputeditext.Formatter.MobileFormatter
import com.example.customtextinputeditext.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureCustomEditText()
    }

    private fun configureCustomEditText() {
        customEditText.setTextInputLayout(textInputLayoutCustomEditText)

        //TODO: Update Masked Edit Text with parameter characterMasked : String characterQuantity : Int
        maskedEditText.setTextInputLayout(textInputLayoutMaskedEditText)
        maskedEditText.setText(true,"MaskedEditText")
        Log.e(MainActivity::class.java.simpleName,maskedEditText.getUnmaskedText())

        //TODO: Finish Amount with   Custom Numeric Keypad
        amountEditText.setTextInputLayout(textInputLayoutAmountEditText,false)
        amountEditText.setTextChangeEvent(amountEditText, "PHP")

        calendarDateEditText.setTextInputLayout(textInputLayoutCalendarDateEditText,false)
        calendarDateEditText.setListener(this,calendarDateEditText)

        contactEditText.setTextInputLayout(textInputLayoutContactEditText, false)
        contactEditText.setListener(contactEditText,
            showContacts = {
                val contactPickerIntent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                ActivityCompat.startActivityForResult(this, contactPickerIntent, ContactEditText.code, null)
            },
            onError = {
                Log.e(MainActivity::class.java.simpleName,it)
            })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode === Activity.RESULT_OK) {
            when (requestCode) {
                ContactEditText.code -> {
                    var cursor: Cursor? = null
                    try {
                        var phoneNo: String? = null
                        val name: String? = null
                        val uri = data?.getData()
                        cursor = contentResolver.query(uri!!, null, null, null, null)
                        cursor?.moveToFirst()
                        val phoneIndex = cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        phoneNo = cursor?.getString(phoneIndex)
                        contactEditText.setText(MobileFormatter.formatContact(phoneNo))
                        contactEditText.setSelection(contactEditText.length())
                        Log.e("onActivityResult Enroll", "${phoneNo}")
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.e("Error Exception", e.message)
                    }
                }
            }
        } else {
            Log.e("onActivityResult", "Failed to pick contact")
        }
    }
}
