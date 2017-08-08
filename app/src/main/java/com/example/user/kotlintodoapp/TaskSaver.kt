package com.example.user.kotlintodoapp

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import ninja.sakib.pultusorm.annotations.AutoIncrement
import ninja.sakib.pultusorm.annotations.PrimaryKey
import ninja.sakib.pultusorm.core.PultusORM
import java.net.ConnectException

/**
 * Created by User on 05.08.2017.
 */

class Task{
    @PrimaryKey
    @AutoIncrement
    var id : Int = 0
    var task : String = ""
}

class DataBaseHelper(appPath : String){

    val database: PultusORM = PultusORM("test.db", appPath)

    fun insertTask(task : String){
        try {
            val value = Task()
            value.task = task
            database.save(value)
        } catch (e: NullPointerException){
            throw NullPointerException("Database connection is NULL")
        }
    }

    fun removeTask(taskText : String){
        try {
            val value = Task()
            value.task = taskText
            database.delete(value)
        } catch (e: NullPointerException){
            throw NullPointerException("Database connection is NULL")
        }
    }

    fun getTasks() : ArrayList<String>{
        try {
            val tasks = database.find(Task())
            val returnValue = ArrayList<String>()
            if (tasks.isNotEmpty()) {
                for(it in tasks) {
                    val task = it as Task
                    returnValue.add(task.task)
                }
            }
            return returnValue
        } catch (e: NullPointerException){
            throw NullPointerException("Database connection is NULL")
        }
    }

    fun closeConnection(){
        try {
            database.close()
        } catch (e:ExceptionInInitializerError){
            throw ExceptionInInitializerError("Database is not init")
        }
    }
}

