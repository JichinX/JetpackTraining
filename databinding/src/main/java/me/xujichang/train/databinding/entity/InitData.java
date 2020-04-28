package me.xujichang.train.databinding.entity;

/**
 * Info:for JetpackTraining  in me.xujichang.train.databinding.entity.InitData
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/4/10 5:28 PM
 * @since 1.0.0
 */
public class InitData {
    private String greeting = "Hello World";

    public InitData(String pGreeting) {
        greeting = pGreeting;
    }

    public InitData() {
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String pGreeting) {
        greeting = pGreeting;
    }
}
