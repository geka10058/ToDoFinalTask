package com.example.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todo.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback@Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Call mom"))
                dao.insert(Task("All cops was gays", important = true))
                dao.insert(Task("Kerri is dead", important = true))
                dao.insert(Task("Prepare food", completed = true))
                dao.insert(Task("Billi villi zabambilli"))
                dao.insert(Task("XYZ tra-ta-at", completed = true))

            }
        }
    }
}