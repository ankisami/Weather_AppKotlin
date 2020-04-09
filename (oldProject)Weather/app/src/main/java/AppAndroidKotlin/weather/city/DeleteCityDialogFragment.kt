package AppAndroidKotlin.weather.city

import AppAndroidKotlin.weather.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DeleteCityDialogFragment : DialogFragment() {

    interface DeleteCityDialogListener{
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    companion object {
        val EXTRA_CITY_NAME: String = "kotlin.weather.extras.EXTRA_CITY_NAME"

        fun newInstance(cityName: String) : DeleteCityDialogFragment {
            val fragment = DeleteCityDialogFragment()
            fragment.arguments = Bundle().apply {
                putString(EXTRA_CITY_NAME, cityName)
            }

            return fragment
        }
    }

    var listener: DeleteCityDialogListener? = null
    private lateinit var cityName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cityName = arguments!!.getString(EXTRA_CITY_NAME)!!
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)

        builder.setTitle(getString(R.string.deletcity_title, cityName))
            .setPositiveButton(getString(R.string.deletecity_positive),
                DialogInterface.OnClickListener{_, _ -> listener?.onDialogPositiveClick() })
            .setNegativeButton(getString(R.string.deletecity_negative),
                DialogInterface.OnClickListener{_,_ -> listener?.onDialogNegativeClick() })

        return builder.create()
    }


}