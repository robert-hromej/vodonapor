package com.mottimotti.vodonapor.commands;

import com.mottimotti.vodonapor.controllers.Document;
import com.mottimotti.vodonapor.views.Graph;

public class AddCommand implements ICommand {

    private final Document document;
    private final Graph graph;

    public AddCommand(Document document, Graph graph) {
        this.document = document;
        this.graph = graph;
    }

    @Override
    public void execute() {
        document.addGraphObject(graph);
    }

    @Override
    public void undo() {
        document.removeGraphObject(graph);
    }
}
