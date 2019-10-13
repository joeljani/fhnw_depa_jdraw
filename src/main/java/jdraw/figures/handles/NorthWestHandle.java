package jdraw.figures.handles;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthWestHandle implements FigureHandle {

    private AbstractFigure figure;
    private Point corner;

    public NorthWestHandle(AbstractFigure figure) {
        this.figure = figure;
    }

    @Override
    public Figure getOwner() {
        return this.figure;
    }

    @Override
    public Point getLocation() {
        return getOwner().getBounds().getLocation();
    }

    @Override
    public void draw(Graphics g) {
        Point loc = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(loc.x-3, loc.y -3, 10,10);
        g.setColor(Color.BLACK);
        g.drawRect(loc.x-3, loc.y -3, 10,10);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }

    @Override
    public boolean contains(int x, int y) {
        Rectangle rectangle = new Rectangle(getLocation().x, getLocation().y, 10, 10);
        return rectangle.contains(x,y);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = new Point(getOwner().getBounds().x + getOwner().getBounds().width,
                           getOwner().getBounds().y + getOwner().getBounds().height);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        getOwner().setBounds(new Point(x,y), corner);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = null;
    }
}
