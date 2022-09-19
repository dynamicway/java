package me.java.designpattern.creation.factorymethod.notebook.apple;

import me.java.designpattern.creation.factorymethod.notebook.Notebook;

public class AppleNoteBook implements Notebook {
    @Override
    public Type getType() {
        return Type.APPLE;
    }

    @Override
    public void boot() {
        System.out.println("boot by Apple");
    }
}
