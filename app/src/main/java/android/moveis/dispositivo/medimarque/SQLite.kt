package android.moveis.dispositivo.medimarque

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLite(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "marcacao.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "formConsulta"
        const val COLUMN_ID = "id"
        const val COLUMN_DATE = "date"
        const val COLUMN_HOUR = "hour"
        const val COLUMN_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME ("+
        "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
        "$COLUMN_NAME TEXT, "+
        "$COLUMN_DATE TEXT, "+
        "$COLUMN_HOUR TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertForm(name:String, date:String, hour: String):Boolean {
        val db=writableDatabase
        val contentValues = ContentValues().apply{
            put(COLUMN_NAME, name)
            put(COLUMN_HOUR, hour)
            put(COLUMN_DATE, date)
        }
        val result = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return result != -1L //irá olhar para a posição L-1 e escrever
    }
}