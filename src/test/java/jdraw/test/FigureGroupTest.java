package jdraw.test;

import jdraw.figures.AbstractFigure;
import jdraw.figures.GroupFigure;
import jdraw.figures.Line;
import jdraw.figures.Rect;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FigureGroupTest extends AbstractFigureTest {

    private List<Figure> createFigureList() {
        return Arrays.asList(new Rect(20,20,20,20), new Line(2,1,2,1));
    }

    @Override
    Figure createFigure() {
        return new GroupFigure(createFigureList());
    }

}
