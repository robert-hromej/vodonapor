package com.mottimotti.vodonapor.test.mocks;

import com.mottimotti.vodonapor.commands.ICommand;

import java.util.ArrayList;
import java.util.List;

public class MockCommand implements ICommand {

    private List<Call> calls = new ArrayList<>();

    public List<Call> getCalls() {
        return calls;
    }

    public Call getLastCall() {
        return getCalls().get(getCalls().size() - 1);
    }

    @Override
    public void execute() {
        calls.add(Call.EXECUTE);
    }

    @Override
    public void undo() {
        calls.add(Call.UNDO);
    }

    public static enum Call {EXECUTE, UNDO}
}
