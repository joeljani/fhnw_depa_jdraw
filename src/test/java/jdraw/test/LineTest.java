package jdraw.test;

import jdraw.figures.AbstractFigure;
import jdraw.figures.Line;

public class LineTest extends AbstractFigureTest {

    @Override
    AbstractFigure createFigure() {
        return new Line(1,1,1,1);
    }


}
