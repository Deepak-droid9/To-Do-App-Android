package com.deepakverma.todolist.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.deepakverma.todolist.model.ToDoDao

@Database(entities = [ToDoDataClass::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun todoDao(): ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDataBase::class.java, "my_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}