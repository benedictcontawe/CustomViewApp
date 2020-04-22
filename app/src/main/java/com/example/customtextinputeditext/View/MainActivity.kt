package com.example.customtextinputeditext.View

import android.app.Activity
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.customtextinputeditext.R
import kotlinx.android.synthetic.main.activity_main.*

public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureCustomEditText()
    }

    private fun configureCustomEditText() {
        //TODO: Finish Amount with Custom Numeric Keypad
        amountEditText.setTextInputLayout(textInputLayoutAmountEditText,false)
        //amountEditText.setTextChangeEvent(amountEditText, "PHP")
        amountEditText.setTextChangeEvent(amountEditText,"PHP",
            setUpKeyboard = {
                //Create the keyboard
                val keyboard : Keyboard = Keyboard(this,R.xml.amount_keyboard_layout)

                //Look up the Keyboard View
                keyboard_view

                // Attach the keyboard to the view
                keyboard_view.setKeyboard(keyboard)

                // Install the key handler
                keyboard_view.setOnKeyboardActionListener(object : KeyboardView.OnKeyboardActionListener {
                    override fun onKey(primaryCode: Int, keyCodes: IntArray) {
                        // Get the EditText and its Editable
                        val focusCurrent = this@MainActivity.window.currentFocus
                        val edittext = amountEditText
                        val editable = edittext.text
                        val start = edittext.selectionStart
                        // Handle key
                        when(primaryCode){
                            Keyboard.KEYCODE_DELETE -> if (start > 0) editable?.delete(start - 1, start)
                            Keyboard.KEYCODE_DONE -> hideCustomKeyboard()
                            55006 -> editable?.clear()
                            Keyboard.KEYCODE_CANCEL -> editable?.clear()
                            46 -> if(amountEditText.text?.contains('.')!!)  editable?.insert(start, Character.toString(primaryCode.toChar()))
                            //-4 -> {}
                            0 -> {}
                            else -> editable?.insert(start, Character.toString(primaryCode.toChar()))
                        }
                    }
                    override fun onPress(arg0: Int) {}

                    override fun onRelease(primaryCode: Int) {}

                    override fun onText(text: CharSequence) {}

                    override fun swipeDown() {}

                    override fun swipeLeft() {}

                    override fun swipeRight() {}

                    override fun swipeUp() {}
                });

                // Hide the standard keyboard initially
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

                // Do not show the preview balloons
                keyboard_view.setPreviewEnabled(false)
            },
            showCustomKeyboard = {
                keyboard_view.setVisibility(View.VISIBLE)
                keyboard_view.setEnabled(true)
                (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(
                        it.getWindowToken(),
                        0
                    )
                amountEditText.requestFocus()
            },
            hideCustomKeyboard = {
                hideCustomKeyboard()
            }
        )
    }

    private fun hideCustomKeyboard() {
        keyboard_view.setVisibility(View.GONE)
        keyboard_view.setEnabled(false)
    }

    override fun onPause() {
        super.onPause()
        amountEditText.dispatchKeyEvent(KeyEvent(0, KeyEvent.KEYCODE_DEL))
    }
}
