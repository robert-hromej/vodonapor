package com.mottimotti.vodonapor.commands;

import com.mottimotti.vodonapor.views.Graph;
import com.mottimotti.vodonapor.views.GraphParams;

public class MoveCommand implements ICommand {

    private final Graph graph;
    private final GraphParams from;
    private final GraphParams to;

    public MoveCommand(Graph graph, GraphParams from, GraphParams to) {
        this.graph = graph;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        graph.moveTo(to.getCoordinate());
    }

    @Override
    public void undo() {
        graph.moveTo(from.getCoordinate());
    }
}
