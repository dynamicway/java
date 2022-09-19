package me.java.designpattern.creation.factorymethod.notebook.samsung;

import me.java.designpattern.creation.factorymethod.notebook.Notebook;
import me.java.designpattern.creation.factorymethod.notebook.NotebookFactory;

public class SamsungNotebookFactory implements NotebookFactory {

    @Override
    public Notebook createNotebook() {
        return new SamsungNotebook();
    }

    @Override
    public Notebook.Type getType() {
        return Notebook.Type.SAMSUNG;
    }

}
