package jdraw.figures.decorators;

import jdraw.framework.Figure;

import java.awt.*;

public class LogDecorator extends AbstractDecorator {

    public LogDecorator(Figure inner, Figure outer) {
        super(inner, outer);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        System.out.println("move called");
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        super.setBounds(origin, corner);
        System.out.println("setBounds called");
    }
}
