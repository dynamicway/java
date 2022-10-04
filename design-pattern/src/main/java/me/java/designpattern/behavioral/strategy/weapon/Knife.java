package me.java.designpattern.behavioral.strategy.weapon;

public class Knife implements Weapon {
    @Override
    public void attack() {
        System.out.println("wield");
    }
}
