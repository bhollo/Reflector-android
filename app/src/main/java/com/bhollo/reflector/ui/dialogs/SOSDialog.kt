package com.bhollo.reflector.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.bhollo.reflector.R
import com.bhollo.reflector.constants.Constants
import com.bhollo.reflector.ui.activities.ReflectorActivity
import java.lang.NullPointerException

class SOSDialog: DialogFragment() {

    companion object{

        const val TAG = "SOSDialog"

        fun show(fragmentActivity: FragmentActivity, color: Int){
            val dialog = SOSDialog().apply {
                arguments = bundleOf(Constants.COLOR to color)
            }
            dialog.show(fragmentActivity.supportFragmentManager, TAG)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = activity ?: throw IllegalStateException("Not attached")
        val color = arguments?.getInt(Constants.COLOR)?: throw NullPointerException("Color is null")

        return MaterialDialog(context).show {
            title(R.string.sos)
            message(R.string.sos_dialog_message)
            positiveButton(R.string.proceed) {
                val intent = ReflectorActivity.getIntent(activity!!, color, forSOS = true )
                context.startActivity(intent)
            }

            negativeButton(R.string.cancel)
            onDismiss {
                this.dismiss()
            }
        }
    }
}