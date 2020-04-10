package training.android.myweather

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class CreateCityDialog: DialogFragment() {

    interface CreateCityDialogueListener {
        fun onDialogPositiveClick(cityName: String)
        fun onDialogNegativeClick()
    }
    var listener: CreateCityDialogueListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(context)

        val input = EditText(context)
        with(input) {
            inputType = InputType.TYPE_CLASS_TEXT
        }

        builder.setTitle("New city name")
                .setView(input)
                .setPositiveButton("Create City",
                        object : DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, id: Int) {
                                listener?.onDialogPositiveClick(input.text.toString())
                            }

                        })
                .setNegativeButton("Cancel",
                        DialogInterface.OnClickListener{ dialog, id ->
                            dialog.cancel()
                            listener?.onDialogNegativeClick()
                        })

        return builder.create()
    }


}