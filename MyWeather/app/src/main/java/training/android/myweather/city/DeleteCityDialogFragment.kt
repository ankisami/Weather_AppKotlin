package training.android.myweather.city

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import training.android.myweather.R

class DeleteCityDialogFragment: DialogFragment() {

    interface DeleteDialogListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    companion object {
        val EXTRA_CITY_NAME = "training.kotlin.weather.extras.EXTRA_CITY_NAME"

        fun newInstance(cityName: String) : DeleteCityDialogFragment {
            val fragment = DeleteCityDialogFragment()
            fragment.arguments = Bundle().apply {
                putString(EXTRA_CITY_NAME, cityName)
            }
            return fragment
        }
    }

    var listener: DeleteDialogListener? = null
    private lateinit var cityName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cityName = arguments!!.getString(EXTRA_CITY_NAME)!!
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)

        builder.setTitle(getString(R.string.deleteCity_Title, cityName))
            .setNegativeButton(getString(R.string.deleteCity_Negative),
                DialogInterface.OnClickListener {_, _ -> listener?.onDialogNegativeClick()})
            .setPositiveButton(getString(R.string.deleteCity_Positive),
                DialogInterface.OnClickListener {_, _ -> listener?.onDialogPositiveClick() })

        return builder.create()
    }
}