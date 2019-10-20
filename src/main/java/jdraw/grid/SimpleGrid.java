package jdraw.grid;

import jdraw.framework.DrawGrid;

import java.awt.*;

public class SimpleGrid implements DrawGrid {

    @Override
    public Point constrainPoint(Point p) {
        System.out.println("SimpleGrid:constrainPoint: " + p);
        return p;
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

