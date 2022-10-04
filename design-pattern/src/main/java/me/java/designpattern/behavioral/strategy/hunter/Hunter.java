package me.java.designpattern.behavioral.strategy.hunter;

import me.java.designpattern.behavioral.strategy.weapon.Weapon;

public class Hunter {

    public void attack(Weapon weapon) {
        weapon.attack();
    }

}
