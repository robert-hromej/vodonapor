package com.mottimotti.vodonapor.test.commands;

import android.test.AndroidTestCase;
import com.mottimotti.vodonapor.commands.ChangePositionCommand;
import com.mottimotti.vodonapor.controllers.Document;
import com.mottimotti.vodonapor.test.mocks.MockDocumentListener;
import com.mottimotti.vodonapor.util.LayerPosition;
import com.mottimotti.vodonapor.views.Graph;
import com.mottimotti.vodonapor.views.GraphParams;
import com.mottimotti.vodonapor.views.GraphType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChangePositionCommandTest extends AndroidTestCase {

    private Document document;

    @Before
    public void setUp() {
        document = new Document(getContext());

        document.setListener(new MockDocumentListener());

        document.addGraphObject(new Graph(getContext(), new GraphParams(100, 20, 400, 100), GraphType.BlueTriangle));
        document.addGraphObject(new Graph(getContext(), new GraphParams(10, 20, 40, 100), GraphType.YellowRect));
        document.addGraphObject(new Graph(getContext(), new GraphParams(-100, 20, 400, 10), GraphType.GreenCircle));
        document.addGraphObject(new Graph(getContext(), new GraphParams(10, 20, 40, 100), GraphType.YellowRect));
        document.addGraphObject(new Graph(getContext(), new GraphParams(-100, 20, 400, 10), GraphType.GreenCircle));
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testMoveBackCommand() {
        document.changeSelected(2);
        assertChangePosition(LayerPosition.MOVE_BACK, 2, 0);

        document.changeSelected(0);
        assertChangePosition(LayerPosition.MOVE_BACK, 0, 0);

        document.changeSelected(4);
        assertChangePosition(LayerPosition.MOVE_BACK, 4, 0);
    }

    @Test
    public void testMoveBackwardsCommand() {
        document.changeSelected(2);
        assertChangePosition(LayerPosition.MOVE_BACKWARDS, 2, 1);

        document.changeSelected(0);
        assertChangePosition(LayerPosition.MOVE_BACKWARDS, 0, 0);

        document.changeSelected(4);
        assertChangePosition(LayerPosition.MOVE_BACKWARDS, 4, 3);
    }

    @Test
    public void testMoveForwardsCommand() {
        document.changeSelected(2);
        assertChangePosition(LayerPosition.MOVE_FORWARDS, 2, 3);

        document.changeSelected(0);
        assertChangePosition(LayerPosition.MOVE_FORWARDS, 0, 1);

        document.changeSelected(4);
        assertChangePosition(LayerPosition.MOVE_FORWARDS, 4, 4);
    }

    @Test
    public void testMoveFronCommand() {
        document.changeSelected(2);
        assertChangePosition(LayerPosition.MOVE_FRONT, 2, 4);

        document.changeSelected(0);
        assertChangePosition(LayerPosition.MOVE_FRONT, 0, 4);

        document.changeSelected(4);
        assertChangePosition(LayerPosition.MOVE_FRONT, 4, 4);
    }

    private void assertChangePosition(LayerPosition type, int from, int to) {
        ChangePositionCommand command = new ChangePositionCommand(document, type);

        assertEquals(document.indexOfSelected(), from);
        command.execute();
        assertEquals(document.indexOfSelected(), to);
        command.undo();
        assertEquals(document.indexOfSelected(), from);
        command.execute();
        assertEquals(document.indexOfSelected(), to);
    }
}
