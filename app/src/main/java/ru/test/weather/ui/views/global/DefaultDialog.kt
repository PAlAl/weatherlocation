package ru.test.weather.ui.views.global

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog.*
import ru.test.weather.R

class DefaultDialog(context: Context, private val title: String, private val message: String, private val cancelLabel: String, private val confirmLabel: String,
                    private val onConfirm: () -> Unit, private val onCancel: () -> Unit = {}) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)

        dialog_title.text = title
        dialog_message.text = message
        dialog_cancel_btn.text = cancelLabel
        dialog_confirm_btn.text = confirmLabel


        dialog_confirm_btn.setOnClickListener {
            onConfirm()
            this.dismiss()
        }

        dialog_cancel_btn.setOnClickListener {
            onCancel()
            this.dismiss()
        }

        this.setCancelable(false)
    }
}