package com.mottimotti.vodonapor.test.mocks;

import com.mottimotti.vodonapor.commands.CommandManager;

public class MockCommandManagerListener implements CommandManager.Listener {

    private int onChangeStateCalls = 0;

    public int getOnChangeStateCalls() {
        return onChangeStateCalls;
    }

    @Override
    public void onChangeState() {
        onChangeStateCalls++;
    }
}
