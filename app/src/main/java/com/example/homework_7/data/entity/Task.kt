package com.example.homework_7.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "tasks")
data class Task(@PrimaryKey(autoGenerate = true)
                @ColumnInfo(name = "id") @NotNull var id:Int,
                @ColumnInfo(name = "name") @NotNull var name: String
                ) : java.io.Serializable {

}