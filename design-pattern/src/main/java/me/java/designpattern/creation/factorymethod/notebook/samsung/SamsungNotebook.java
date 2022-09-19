package me.java.designpattern.creation.factorymethod.notebook.samsung;

import me.java.designpattern.creation.factorymethod.notebook.Notebook;

public class SamsungNotebook implements Notebook {
    @Override
    public Type getType() {
        return Type.SAMSUNG;
    }

    @Override
    public void boot() {
        System.out.println("boot by Samsung");
    }
}
