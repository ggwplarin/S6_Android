package com.example.mvvmsqllite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "user.db", null, 1) {

    val TABLE_NAME = "users_data"
    val COL2 = "EMAIL"
    val COL3 = "PASSWORD"

    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, PASSWORD TEXT, TVSHOW TEXT)"
        db!!.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(DROP_USER_TABLE)
        onCreate(db)
    }

    fun addData(email: String?, password: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL2, email)
        contentValues.put(COL3, password)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    fun showData(): Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun updateData(ID: String?, newEmail: String?, newPassword: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL2, newEmail)
        contentValues.put(COL3, newPassword)
        var count = db.update(TABLE_NAME, contentValues, "id = ?", arrayOf(ID))
        return count > 0
    }

    fun deleteData(ID: String?): Boolean {
        val db = this.writableDatabase
        var count = db.delete(TABLE_NAME, "ID = $ID", null)
        return count > 0
    }
}