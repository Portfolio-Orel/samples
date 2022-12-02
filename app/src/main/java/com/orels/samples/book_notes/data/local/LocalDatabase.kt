package com.orels.samples.book_notes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.orels.samples.book_notes.data.local.dao.BookDao
import com.orels.samples.book_notes.data.local.dao.BookNoteDao
import com.orels.samples.book_notes.domain.model.Book
import com.orels.samples.book_notes.domain.model.BookNote

@Database(entities = [BookNote::class, Book::class], version = 5)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun taskDao(): BookNoteDao
    abstract fun bookDao(): BookDao
}