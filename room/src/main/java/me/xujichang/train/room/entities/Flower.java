package me.xujichang.train.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Info:for JetpackTraining  in me.xujichang.train.room.entities.Flower
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/2/12 6:14 PM
 * @since 1.0.0
 */
@Entity(tableName = "tbl_flower")
public class Flower {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long pId) {
        id = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public String getType() {
        return type;
    }

    public void setType(String pType) {
        type = pType;
    }
}
