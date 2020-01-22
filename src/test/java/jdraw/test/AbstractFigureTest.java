package jdraw.test;

import jdraw.figures.AbstractFigure;
import jdraw.figures.Rect;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class AbstractFigureTest {

    abstract Figure createFigure();
    private Figure f;
    private int cnt;

    @BeforeEach
    public void setUp() {
        f = createFigure();
        cnt = 0;
    }

    @Test
    public void testNotification1() {
        FigureListener l = new TestListener();
        f.addFigureListener(l);
        f.move(1, 1);
        assertTrue(cnt == 1, "figureChanged must be called on a registered listener");
        f.removeFigureListener(l);
        f.move(2, 2);
        assertTrue(cnt == 1, "figureChanged must not be called on disconnected listener");
    }



    class TestListener implements FigureListener {
        @Override
        public void figureChanged(FigureEvent e) {
            assertTrue(e.getSource() == f);
            cnt++;
        }
    }

    class RemoveListener implements FigureListener {
        private final Figure f;

        RemoveListener(Figure f) {
            this.f = f;
        }

        @Override
        public void figureChanged(FigureEvent e) {
            f.removeFigureListener(this);
        }
    }

    class UpdateListener implements FigureListener {
        private final Figure f;
        public UpdateListener(Figure f) {
            this.f = f;
        }
        @Override
        public void figureChanged(FigureEvent e) {
            Point p1 = e.getFigure().getBounds().getLocation();
            Point p2 = f.getBounds().getLocation();
            f.move(p1.x - p2.x, p1.y - p2.y);
        }
    }

}
