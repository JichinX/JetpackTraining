package me.xujichang.train.paging.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * Info:for JetpackTraining  in me.xujichang.train.paging.entity.User
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/4/14 7:19 PM
 * @since 1.0.0
 */
public class User {
    private long mUserId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public User(long pIndex) {
        setName("User :" + String.format("%03d", pIndex));
        setUserId(pIndex);
    }

    public User() {
    }

    public long getUserId() {

        return mUserId;
    }

    public void setUserId(long pId) {
        mUserId = pId;
    }

    @Override
    public boolean equals(Object vpO) {
        if (this == vpO) return true;
        if (vpO == null || getClass() != vpO.getClass()) return false;
        User vvUser = (User) vpO;
        return mUserId == vvUser.mUserId &&
                Objects.equals(name, vvUser.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mUserId, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "mUserId=" + mUserId +
                ", name='" + name + '\'' +
                '}';
    }
}
