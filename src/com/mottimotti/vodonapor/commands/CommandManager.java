package com.mottimotti.vodonapor.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    public static final int NONE = 0;
    public static final int ONLY_UNDO = 1;
    public static final int ONLY_REDO = 2;
    public static final int ALL = 3;

    private final List<ICommand> history = new ArrayList<ICommand>();

    private int currentVersion = -1;

    private int state;

    private Listener listener;

    public CommandManager() {
        updateState();
    }

    public void undo() {
        if (currentVersion < 0) return;

        ICommand command = history.get(currentVersion--);
        command.undo();
        updateState();
    }

    public void redo() {
        if (currentVersion == getLastIndex()) return;

        ICommand command = history.get(++currentVersion);
        command.execute();
        updateState();
    }

    public void addCommand(ICommand command) {
        while (getLastIndex() > currentVersion) history.remove(getLastIndex());

        history.add(command);
        command.execute();

        currentVersion = getLastIndex();

        updateState();
    }

    private int getLastIndex() {
        return history.size() - 1;
    }

    public void resetHistory() {
        currentVersion = -1;
        history.clear();
        updateState();
    }

    private void updateState() {
        if (history.size() == 0) {
            state = NONE;
        } else {
            if (currentVersion == -1) {
                state = ONLY_REDO;
            } else if (currentVersion == getLastIndex()) {
                state = ONLY_UNDO;
            } else {
                state = ALL;
            }
        }

        if (listener != null) listener.onChangeState();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public int getState() {
        return state;
    }

    public static interface Listener {
        public void onChangeState();
    }
}
