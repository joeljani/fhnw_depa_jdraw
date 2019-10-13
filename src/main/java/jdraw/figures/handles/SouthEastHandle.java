package jdraw.figures.handles;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthEastHandle implements FigureHandle {
    private AbstractFigure figure;
    private Point corner;

    private State state = State.INIT;

    private enum State {
        INIT, SWITCHED
    }

    public SouthEastHandle(AbstractFigure figure) {
        this.figure = figure;
    }

    @Override
    public Figure getOwner() {
        return this.figure;
    }

    @Override
    public Point getLocation() {
        int xR = getOwner().getBounds().x + getOwner().getBounds().width;
        int yR = getOwner().getBounds().y + getOwner().getBounds().height;
        Point loc = new Point(xR, yR);
        return loc;
    }

    @Override
    public void draw(Graphics g) {
        int xR = (getOwner().getBounds().x + getOwner().getBounds().width) - 3;
        int yR = (getOwner().getBounds().y + getOwner().getBounds().height) - 3;
        Point loc = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(xR, yR, 10,10);
        g.setColor(Color.BLACK);
        g.drawRect(xR, yR, 10,10);
    }

    @Override
    public Cursor getCursor() {
        if(state == State.SWITCHED) {
            state = State.INIT;
            return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
        }
        return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
    }

    @Override
    public boolean contains(int x, int y) {
        int xR = getOwner().getBounds().x + getOwner().getBounds().width;
        int yR = getOwner().getBounds().y + getOwner().getBounds().height;
        Rectangle rectangle = new Rectangle(xR, yR,10, 10);
        return rectangle.contains(x,y);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        int xR = getOwner().getBounds().x;
        int yR = getOwner().getBounds().y;
        corner = new Point(xR,yR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        getOwner().setBounds(new Point(x,y), corner);
        if(getOwner().getBounds().getLocation().equals(new Point(x,y))) {
            state = State.SWITCHED;
        }
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = null;
    }
}
