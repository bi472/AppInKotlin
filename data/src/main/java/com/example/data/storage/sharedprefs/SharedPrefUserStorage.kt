package com.example.data.storage.sharedprefs

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.data.storage.UserStorage
import com.example.data.storage.models.UserNameData
import java.lang.Exception

private const val SHARED_PREFS_NAME = "username_prefs"
private const val KEY_USER = "user"
private const val KEY_PASSWORD = "password"
private const val DEFAULT_USER = "Имя не сохранено"
private const val DEFAULT_PASSWORD = "Пароль не сохранён"

class SharedPrefUserStorage(val context: Context): UserStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun save(saveParam: UserNameData): Boolean {
        return try {
            sharedPreferences.edit().putString(KEY_USER, saveParam.user).apply()
            sharedPreferences.edit().putString(KEY_PASSWORD, saveParam.password).apply()
            true
        }
        catch (exception: Exception){
            Toast.makeText(context, "К сожалению, имя пользователя и пароль не сохранились(", Toast.LENGTH_LONG).show()
            Log.e("USERTAG", exception.toString())
            false
        }
    }

    override fun get(): UserNameData {
        val user = sharedPreferences.getString(KEY_USER, DEFAULT_USER) ?: DEFAULT_USER
        val password = sharedPreferences.getString(KEY_PASSWORD, DEFAULT_PASSWORD) ?: DEFAULT_PASSWORD
        return UserNameData(user = user, password = password)
    }
}