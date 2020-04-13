package training.android.myweather.city

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import training.android.myweather.R

class CreateCityDialogFragment: DialogFragment() {

    interface CreateCityDialogListener {
        fun onDialogPositiveClick(cityName: String)
        fun onDialogNegativeClick()
    }

    var listener: CreateCityDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)

        val input = EditText(context)
        with(input) {
            inputType = InputType.TYPE_CLASS_TEXT
            hint = context.getString(R.string.createCity_CityHint)
        }

        builder.setTitle(getString(R.string.createCity_title))
            .setView(input)
            .setPositiveButton(getString(R.string.createCity_PositiveClick),
                DialogInterface.OnClickListener{_, _ ->
                    listener?.onDialogPositiveClick(input.text.toString())
                })
            .setNegativeButton(getString(R.string.createCity_NegativeClick),
                DialogInterface.OnClickListener {_, _ ->
                    dialog?.cancel()
                    listener?.onDialogNegativeClick()
                })

        return builder.create()
    }

}