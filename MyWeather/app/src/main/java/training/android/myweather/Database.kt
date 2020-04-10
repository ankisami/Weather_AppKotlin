package training.android.myweather

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper;

class Database(context: Context) :
        SQLiteOpenHelper(context, "WeaterDB", null , 1 ){



    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("Create table activity_main (id INTEGER  PRIMARY KEY , name TEXT )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
    fun createCity(cityName : String){
        val values = ContentValues()
        values.put("name",cityName)

        writableDatabase.insert("activity_main", null,values)
    }


    fun  deleteCity( city : City){
        val delete =writableDatabase.delete("activity_main","id= ?",arrayOf("${city.id}"))

    }

}