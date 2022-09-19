package me.java.designpattern.creation.factorymethod.notebook;

public interface Notebook {

    Type getType();

    void boot();

    enum Type {
        SAMSUNG,
        APPLE
    }

}
