package id.aej.jeky.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.aej.jeky.core.data.source.local.room.dao.UserDao
import id.aej.jeky.core.data.source.local.room.entity.UserEntity


@Database(
  entities = [UserEntity::class],
  version = 2,
  exportSchema = true
)
abstract class JekyDatabase: RoomDatabase() {

  abstract fun userDao(): UserDao

  companion object {
    @Volatile
    private var INSTANCE: JekyDatabase? = null
    fun getInstance(context: Context): JekyDatabase {
      synchronized(this) {
        var instance = INSTANCE
        if (instance == null) {
          val dbBuilder = Room.databaseBuilder(
            context.applicationContext,
            JekyDatabase::class.java,
            "jeky.database"
          )
            .fallbackToDestructiveMigration()

          instance = dbBuilder.build()
          INSTANCE = instance
        }

        return instance
      }
    }
  }
}