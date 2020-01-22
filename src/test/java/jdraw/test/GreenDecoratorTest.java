package jdraw.test;

import jdraw.figures.decorators.AbstractDecorator;
import jdraw.figures.decorators.GreenDecorator;
import jdraw.figures.Rect;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GreenDecoratorTest extends AbstractFigureTest {
    private Figure f;
    private int cnt;

    @Override
    Figure createFigure() {
        return new GreenDecorator(new Rect(20,20,20,20));
    }

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
            AbstractDecorator decorator = (AbstractDecorator) f;
            assertTrue(decorator.isSame(e.getFigure()));
            cnt++;
        }
    }
}
