package me.xujichang.train.room.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.Entity;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import me.xujichang.train.room.dao.FlowerDao;
import me.xujichang.train.room.entities.Flower;

/**
 * Info:for JetpackTraining  in me.xujichang.train.room.database.Appdatabase
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/2/12 6:12 PM
 * @since 1.0.0
 */
@Database(entities = {Flower.class}, version = 1, exportSchema = false)
public abstract class Appdatabase extends RoomDatabase {

    abstract FlowerDao flowerDao();

}
