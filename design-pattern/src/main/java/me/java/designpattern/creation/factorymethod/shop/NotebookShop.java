package me.java.designpattern.creation.factorymethod.shop;

import me.java.designpattern.creation.factorymethod.notebook.Notebook;
import me.java.designpattern.creation.factorymethod.notebook.NotebookFactory;

import java.util.EnumMap;
import java.util.List;

public class NotebookShop {

    private final EnumMap<Notebook.Type, NotebookFactory> notebookFactories;

    public NotebookShop(List<NotebookFactory> notebookFactories) {
        this.notebookFactories = new EnumMap<>(NotebookFactory.class);
        for (NotebookFactory notebookFactory : notebookFactories) {
            this.notebookFactories.put(notebookFactory.getType(), notebookFactory);
        }
    }

    public Notebook getNotebook(Notebook.Type notebookType) {
        NotebookFactory notebookFactory = getNotebookFactory(notebookType);
        return notebookFactory.createNotebook();
    }

    private NotebookFactory getNotebookFactory(Notebook.Type notebookType) {
        NotebookFactory notebookFactory = notebookFactories.get(notebookType);
        if (notebookFactory == null)
            throw new IllegalStateException();
        return notebookFactory;
    }

}
