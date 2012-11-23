package com.mottimotti.vodonapor.commands;

import com.mottimotti.vodonapor.controllers.Document;
import com.mottimotti.vodonapor.views.Graph;

public class DuplicateCommand implements ICommand {

    private final Document document;
    private final Graph graph;

    public DuplicateCommand(Document document, Graph graph) {
        this.document = document;
        this.graph = graph.copy();
        this.graph.moveBy(20, 20);
    }

    @Override
    public void execute() {
        document.addGraphObject(graph);
        document.changeSelected(graph);
    }

    @Override
    public void undo() {
        document.removeGraphObject(graph);
        document.changeSelected(null);
    }
}
