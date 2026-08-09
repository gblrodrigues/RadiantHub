package com.gblrod.radianthub.data.room.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
                CREATE TABLE favorites_new (
                    uuid TEXT NOT NULL,
                    name TEXT NOT NULL,
                    imageUrl TEXT,
                    type TEXT NOT NULL,
                    `index` INTEGER NOT NULL,
                    PRIMARY KEY(uuid)
                )
                """.trimIndent()
        )

        db.execSQL(
            """
                INSERT INTO favorites_new (
                    uuid,
                    name,
                    imageUrl,
                    type,
                    `index`
                )
                SELECT
                    uuid,
                    name,
                    imageUrl,
                    type,
                    0
                FROM favorites
                """.trimIndent()
        )

        db.execSQL(
            """
                DROP TABLE favorites
                """.trimIndent()
        )

        db.execSQL(
            """
                ALTER TABLE favorites_new
                RENAME TO favorites
                """.trimIndent()
        )
    }
}