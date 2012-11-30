package com.mottimotti.vodonapor.test.views;

import android.graphics.Point;
import com.mottimotti.vodonapor.views.GraphParams;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GraphParamsTest extends TestCase {

    private GraphParams params;

    @Before
    public void setUp() {
        params = new GraphParams(10, 20, 30, 40);
    }

    @After
    public void tearDown() {
        params = null;
    }

    @Ignore
    @Test
    public void parse() {
//      TODO
    }

    @Test
    public void getCoordinate() {
        assertEquals(params.getCoordinate(), new Point(10, 20));
    }

    @Test
    public void testGetSize() {
        assertEquals(params.getSize(), new Point(30, 40));
    }

    @Test
    public void testGetLeftX() {
        assertEquals(params.getLeftX(), 10);
    }

    @Test
    public void testGetRightX() {
        assertEquals(params.getRightX(), 40);
    }

    @Test
    public void testGetTopY() {
        assertEquals(params.getTopY(), 20);
    }

    @Test
    public void testGetBottomY() {
        assertEquals(params.getBottomY(), 60);
    }

    @Ignore
    @Test
    public void testToJson() {
//      TODO
    }

    @Test
    public void testMoveTo() {
        params.moveTo(-10, 100);
        assertEquals(params.x, -10);
        assertEquals(params.y, 100);
    }

    @Test
    public void testMoveBy()
    {
        final int x = params.x;
        final int y = params.y;

        params.moveBy(50, 60);

        assertEquals(params.x, x + 50);
        assertEquals(params.y, y + 60);
    }

    @Test
    public void testResizeTo() {
        params.resizeTo(200, 323);
        assertEquals(params.getSize(), new Point(200, 323));
    }

    @Test
    public void testCopy() {
        GraphParams newParams = params.copy();

        assertEquals(params.x, newParams.x);
        assertEquals(params.y, newParams.y);
        assertEquals(params.width, newParams.width);
        assertEquals(params.height, newParams.height);
    }
}
