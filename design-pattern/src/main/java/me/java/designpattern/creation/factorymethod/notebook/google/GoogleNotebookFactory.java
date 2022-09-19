package me.java.designpattern.creation.factorymethod.notebook.google;

import me.java.designpattern.creation.factorymethod.notebook.Notebook;
import me.java.designpattern.creation.factorymethod.notebook.NotebookFactory;

public class GoogleNotebookFactory implements NotebookFactory {
    @Override
    public Notebook createNotebook() {
        return new GoogleNotebook();
    }

    @Override
    public Notebook.Type getType() {
        return Notebook.Type.GOOGLE;
    }
}
