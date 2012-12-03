package com.mottimotti.vodonapor.test.commands;

import android.test.AndroidTestCase;
import com.mottimotti.vodonapor.commands.AddCommand;
import com.mottimotti.vodonapor.controllers.Document;
import com.mottimotti.vodonapor.views.Graph;
import com.mottimotti.vodonapor.views.GraphParams;
import com.mottimotti.vodonapor.views.GraphType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddCommandTest extends AndroidTestCase {

    private AddCommand command;

    private Document document;

    private Graph graph;

    @Before
    public void setUp() {
        graph = new Graph(getContext());
        document = new Document(getContext());
        command = new AddCommand(document, graph);

        document.addGraphObject(new Graph(getContext(),new GraphParams(100,20,400,100), GraphType.BlueTriangle));
        document.addGraphObject(new Graph(getContext(),new GraphParams(10,20,40,100), GraphType.YellowRect));
        document.addGraphObject(new Graph(getContext(),new GraphParams(-100,20,400,10), GraphType.GreenCircle));
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testExecute() {
        assertEquals(document.size(), 3);
        command.execute();
        assertEquals(document.size(), 4);
    }

    @Test
    public void testUndo() {
        assertEquals(document.size(), 3);
        command.execute();
        assertEquals(document.size(), 4);
        command.undo();
        assertEquals(document.size(), 3);
    }
}
