package com.example.todo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "task_table")
@Parcelize
data class Task(
    private val name: String,
    private val important: Boolean = false,
    private val completed: Boolean = false,
    private val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id: Int = 0
): Parcelable {
    val createdDateFormatted: String
    get() = DateFormat.getDateInstance().format(created)
}