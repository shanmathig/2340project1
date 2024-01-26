package com.dicoding.courseschedule.ui.add

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.ViewModelFactory
import com.dicoding.courseschedule.util.TimePickerFragment
import kotlin.math.min

class AddToDoListActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    private lateinit var viewModel: AddToDoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.createFactory(this)
        viewModel = ViewModelProvider(this, factory)[AddCourseViewModel::class.java]

        timeButton()
    }

    private fun timeButton() {
        val fragment = TimePickerFragment()

        val startTime = findViewById<ImageButton>(R.id.ib_start_time)
        val endTime = findViewById<ImageButton>(R.id.ib_end_time)
        startTime.setOnClickListener {
            fragment.show(supportFragmentManager, "Start Time")
        }
        endTime.setOnClickListener {
            fragment.show(supportFragmentManager, "End Time")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_insert -> {
                val task_name = findViewById<TextView>(R.id.ed_task_name).text.toString()
                val task_date = findViewById<Spinner>(R.id.datePickerButton).selectedItemId.toInt()
                val task_course = findViewById<TextView>(R.id.ed_course).text.toString()
                val task_note = findViewById<TextView>(R.id.ed_note_task).text.toString()

                viewModel.insertCourse(task_name, task_date, task_course, task_note)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val evEndTime = findViewById<TextView>(R.id.tv_end_time)
        val tvStartTime = findViewById<TextView>(R.id.tv_start_time)
        if (tag == "Start Time") {
            tvStartTime.text = String.format("%02d:%02d", hour, minute)
        } else {
            evEndTime.text = String.format("%02d:%02d", hour, minute)
        }
    }
}