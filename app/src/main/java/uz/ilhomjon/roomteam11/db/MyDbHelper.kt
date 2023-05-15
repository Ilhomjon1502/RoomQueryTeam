package uz.ilhomjon.roomteam11.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.ilhomjon.roomteam11.models.MyContact

@Database(entities = [MyContact::class], version = 1)
abstract class MyDbHelper : RoomDatabase() {
    abstract fun contactDao():MyDao
    companion object{
        private var instance:MyDbHelper? = null
        fun newInstance(context: Context):MyDbHelper{
            if (instance==null){
                instance = Room.databaseBuilder(context, MyDbHelper::class.java, "db_contact")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}