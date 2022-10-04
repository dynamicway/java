package me.java.designpattern.behavioral.strategy.weapon;

public class Gun implements Weapon {
    @Override
    public void attack() {
        System.out.println("fire");
    }
}
