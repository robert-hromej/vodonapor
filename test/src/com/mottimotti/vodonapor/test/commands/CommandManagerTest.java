package com.mottimotti.vodonapor.test.commands;

import com.mottimotti.vodonapor.commands.CommandManager;
import com.mottimotti.vodonapor.test.mocks.MockCommand;
import com.mottimotti.vodonapor.test.mocks.MockCommandManagerListener;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CommandManagerTest extends TestCase {

    private CommandManager commandManager;
    private MockCommandManagerListener listener = new MockCommandManagerListener();

    @Before
    public void setUp() {
        commandManager = new CommandManager();
        commandManager.setListener(listener);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testUndo() {
        MockCommand command1 = new MockCommand();
        commandManager.addCommand(command1);

        MockCommand command2 = new MockCommand();
        commandManager.addCommand(command2);

        commandManager.undo();
        assertEquals(command2.getLastCall(), MockCommand.Call.UNDO);

        commandManager.undo();
        assertEquals(command1.getLastCall(), MockCommand.Call.UNDO);
    }

    @Test
    public void testRedo() {
        MockCommand command1 = new MockCommand();
        commandManager.addCommand(command1);

        MockCommand command2 = new MockCommand();
        commandManager.addCommand(command2);

        commandManager.undo();
        commandManager.undo();

        commandManager.redo();
        assertEquals(command1.getLastCall(), MockCommand.Call.EXECUTE);

        commandManager.redo();
        assertEquals(command2.getLastCall(), MockCommand.Call.EXECUTE);
    }

    @Test
    public void testAddCommand() {
        MockCommand command1 = new MockCommand();
        commandManager.addCommand(command1);
        assertEquals(command1.getCalls().size(), 1);
        assertEquals(command1.getLastCall(), MockCommand.Call.EXECUTE);
        assertEquals(listener.getOnChangeStateCalls(), 1);

        MockCommand command2 = new MockCommand();
        commandManager.addCommand(command2);
        assertEquals(command2.getCalls().size(), 1);
        assertEquals(command1.getLastCall(), MockCommand.Call.EXECUTE);
        assertEquals(listener.getOnChangeStateCalls(), 2);
    }

    @Test
    public void testResetHistory() {
        commandManager.resetHistory();
        assertEquals(listener.getOnChangeStateCalls(), 1);
    }
}
