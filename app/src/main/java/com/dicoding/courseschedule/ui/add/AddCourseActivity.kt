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

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    private lateinit var viewModel: AddCourseViewModel

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
                val name = findViewById<TextView>(R.id.ed_course_name).text.toString()
                val day = findViewById<Spinner>(R.id.spinner_day).selectedItemId.toInt()
                val startTime = findViewById<TextView>(R.id.tv_start_time).text.toString()
                val endTime = findViewById<TextView>(R.id.tv_end_time).text.toString()
                val lecturer = findViewById<TextView>(R.id.ed_lecturer).text.toString()
                val note = findViewById<TextView>(R.id.ed_note).text.toString()

                viewModel.insertCourse(name, day, startTime, endTime, lecturer, note)
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