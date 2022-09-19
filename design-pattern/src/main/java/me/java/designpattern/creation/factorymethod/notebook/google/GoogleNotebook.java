package me.java.designpattern.creation.factorymethod.notebook.google;

import me.java.designpattern.creation.factorymethod.notebook.Notebook;

public class GoogleNotebook implements Notebook {
    @Override
    public Type getType() {
        return Type.GOOGLE;
    }

    @Override
    public void boot() {
        System.out.println("boot by Google");
    }
}
