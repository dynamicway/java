package me.java.designpattern.creation.factorymethod.notebook;

public interface NotebookFactory {

    Notebook createNotebook();

    Notebook.Type getType();
}
