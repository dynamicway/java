package me.java.designpattern.creation.factorymethod.notebook.apple;

import me.java.designpattern.creation.factorymethod.notebook.Notebook;
import me.java.designpattern.creation.factorymethod.notebook.NotebookFactory;

public class AppleNotebookFactory implements NotebookFactory {

    @Override
    public Notebook createNotebook() {
        return new AppleNoteBook();
    }

    @Override
    public Notebook.Type getType() {
        return Notebook.Type.APPLE;
    }

}
