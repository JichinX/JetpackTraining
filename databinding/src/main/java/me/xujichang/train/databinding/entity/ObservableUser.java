package me.xujichang.train.databinding.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;

import me.xujichang.train.databinding.BR;

/**
 * Info:for JetpackTraining  in me.xujichang.train.databinding.entity.ObservableUser
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/4/10 5:55 PM
 * @since 1.0.0
 */
public class ObservableUser extends BaseObservable {
    private String name = "许继昌";
    private int age = 22;
    @Bindable
    private String address = "潍坊市...";

    public ObservableUser(String pName, int pAge, String pAddress) {
        name = pName;
        age = pAge;
        address = pAddress;
    }

    public ObservableUser() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String pAddress) {
        address = pAddress;
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public String getName() {
        return name;
    }

    //@Bindable 不起作用
    public void setName(String pName) {
        name = pName;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int pAge) {
        age = pAge;
        notifyPropertyChanged(BR.age);
    }
}
