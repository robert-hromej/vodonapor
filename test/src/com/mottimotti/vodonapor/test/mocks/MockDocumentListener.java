package com.mottimotti.vodonapor.test.mocks;

import com.mottimotti.vodonapor.commands.ICommand;
import com.mottimotti.vodonapor.controllers.Document;
import com.mottimotti.vodonapor.views.Graph;

import java.util.ArrayList;
import java.util.List;

public class MockDocumentListener implements Document.Listener {

    private List<Graph> onSelectCalls = new ArrayList<>();

    private List<Graph> onChangeParamsCalls = new ArrayList<>();

    private List<ICommand> onCompleteCalls = new ArrayList<>();

    public List<Graph> getOnSelectCalls() {
        return onSelectCalls;
    }

    public List<Graph> getOnChangeParamsCalls() {
        return onChangeParamsCalls;
    }

    public List<ICommand> getOnCompleteCalls() {
        return onCompleteCalls;
    }

    @Override
    public void onSelect(Graph graph) {
        onSelectCalls.add(graph);
    }

    @Override
    public void onChangeParams(Graph graph) {
        onChangeParamsCalls.add(graph);
    }

    @Override
    public void onCompleteChange(ICommand command) {
        onCompleteCalls.add(command);
    }
}
