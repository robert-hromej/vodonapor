package com.mottimotti.vodonapor.commands;

import com.mottimotti.vodonapor.controllers.Document;
import com.mottimotti.vodonapor.util.LayerPosition;

public class ChangePositionCommand implements ICommand {

    private final Document document;
    private final int fromIndex;
    private final int toIndex;

    public ChangePositionCommand(Document document, LayerPosition layerPosition) {
        this.document = document;
        this.fromIndex = document.indexOfSelected();

        if (layerPosition == LayerPosition.MOVE_BACK) {
            this.toIndex = 0;
        } else if (layerPosition == LayerPosition.MOVE_BACKWARDS) {
            this.toIndex = Math.max(fromIndex - 1, 0);
        } else if (layerPosition == LayerPosition.MOVE_FORWARDS) {
            this.toIndex = Math.min(fromIndex + 1, document.size() - 1);
        } else {
            this.toIndex = document.size() - 1;
        }
    }

    @Override
    public void execute() {
        document.changePosition(fromIndex, toIndex);
    }

    @Override
    public void undo() {
        document.changePosition(toIndex, fromIndex);
    }
}
