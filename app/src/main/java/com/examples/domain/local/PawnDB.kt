package com.examples.domain.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.examples.domain.util.Converters

@Database(
    entities = [CustomerEntity::class, PawnItemEntity::class, TodaysRenewalEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PawnDB : RoomDatabase() {

    //abstract val pawnDao: IPawnDao
    abstract fun getPawnDao(): IPawnDao

    companion object {
        @Volatile
        private var instance: PawnDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, PawnDB::class.java, "Pawn_db.db")
                .build()
    }
}