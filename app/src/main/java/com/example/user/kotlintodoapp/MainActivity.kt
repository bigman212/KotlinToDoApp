package com.example.user.kotlintodoapp

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.view.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    val ID_ADD_DIALOG = 0

    lateinit var taskList : ArrayList<String>
    lateinit var mAdapter : ArrayAdapter<String>
    lateinit var helper : DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appPath = applicationContext.filesDir.absolutePath
        try {
            helper = DataBaseHelper(appPath)
            taskList = helper.getTasks()
            mAdapter = ArrayAdapter(this, R.layout.item_todo, R.id.task_title, taskList)
            listWork.adapter = mAdapter
        } catch (e : NullPointerException){
            Toast.makeText(this,"Mistake happened", Toast.LENGTH_LONG).show()
        }
    }
    
    override fun onCreateDialog(id: Int): Dialog? {
        //TODO: Replace with Dialog
        when(id){
            ID_ADD_DIALOG -> {
                val taskEditText = EditText(this)
                val dialog = AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What's next?")
                        .setIcon(R.drawable.ic_exposure_plus_1_black_24dp)
                        .setView(taskEditText)
                        .setPositiveButton("Add", { dialogInterface, i ->
                            val taskText = taskEditText.text.toString()
                            taskList.add(taskText)
                            helper.insertTask(taskText)
                            mAdapter.notifyDataSetChanged()
                        })
                        .setNegativeButton("Cancel", null)
                return dialog.create()
            }
        }
        return null
    }

    fun addTask(view : View){
        showDialog(ID_ADD_DIALOG)
    }

    fun deleteTask(view: View){
        val parent = view.parent as View
        val task = parent.task_title.text.toString()
        taskList.remove(task)
        helper.removeTask(task)
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        helper.closeConnection()
    }
}
