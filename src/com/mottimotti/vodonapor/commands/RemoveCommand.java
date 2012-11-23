package com.mottimotti.vodonapor.commands;

import com.mottimotti.vodonapor.controllers.Document;
import com.mottimotti.vodonapor.views.Graph;

public class RemoveCommand implements ICommand {

    private final Document document;
    private final Graph graph;
    private final int index;

    public RemoveCommand(Document document, Graph graph, int index) {
        this.document = document;
        this.graph = graph;
        this.index = index;
    }

    @Override
    public void execute() {
        document.removeGraphObject(graph);
        document.selected = null;
    }

    @Override
    public void undo() {
        document.addGraphObject(index, graph);
        document.changeSelected(graph);
    }
}
