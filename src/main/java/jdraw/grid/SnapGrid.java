package jdraw.grid;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawGrid;
import jdraw.framework.Figure;

import java.awt.*;
import java.util.stream.Stream;

public class SnapGrid implements DrawGrid {

    private DrawContext context;
    private Stream<Figure> figureList;

    public SnapGrid(DrawContext context) {
        this.context = context;
    }

    @Override
    public Point constrainPoint(Point p) {
        Point apoint = new Point(281,166);
        if(p.distance(apoint) > 15) {
            return apoint;
        } else return p;
    }

    @Override
    public int getStepX(boolean right) {
        System.out.println("getStepX called");
        return 1;
    }

    @Override
    public int getStepY(boolean down) {
        System.out.println("getStepY called");
        return 1;
    }

    @Override
    public void activate() {
        this.figureList = this.context.getModel().getFigures();
        System.out.println("activate() called");
    }

    @Override
    public void deactivate() {
        System.out.println("deactivate() called");
    }

    @Override
    public void mouseDown() {
        System.out.println("mouseDown() called");
    }

    @Override
    public void mouseUp() {
        System.out.println("mouseUp() called");
    }
}

