package com.dicoding.courseschedule.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.courseschedule.data.Task
import com.dicoding.courseschedule.data.DataRepository
import com.dicoding.courseschedule.util.Event

class AddToDoListViewModel(private val repository: DataRepository) : ViewModel() {

    private val _saved = MutableLiveData<Event<Boolean>>()
    val saved: LiveData<Event<Boolean>>
        get() = _saved

    fun insertTask(
        task_name: String,
        task_date: Int,
        task_course: String,
        task_note: String
    ) {
        if (task_name.isEmpty()) {
            _saved.value = Event(false)
            return
        }

        val task = Task(
            task_name = task_name,
            task_date = task_date + 1,
            task_course = task_course,
            task_note = task_note
        )
        repository.insert(course)
        _saved.value = Event(true)
    }
}