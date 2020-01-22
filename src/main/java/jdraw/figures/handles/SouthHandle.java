package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthHandle extends AbstractFigureHandle {

    private Point corner;
    private Point southPoint;

    public SouthHandle(Figure figure) {
        super(figure);
    }

    @Override
    public Figure getOwner() {
        return getFigure();
    }

    @Override
    public Point getLocation() {
        Point northPoint = new Point(getOwner().getBounds().getLocation().x, getOwner().getBounds().getLocation().y);
        return northPoint;
    }

    @Override
    public void draw(Graphics g) {
        int width = getOwner().getBounds().width;
        int halfOfWidth = width/2;
        this.southPoint = new Point((getOwner().getBounds().getLocation().x + halfOfWidth), getOwner().getBounds().getLocation().y + getOwner().getBounds().height);
        g.setColor(Color.WHITE);
        g.fillRect(southPoint.x-3, southPoint.y -3, 10,10);
        g.setColor(Color.BLACK);
        g.drawRect(southPoint.x-3, southPoint.y -3, 10,10);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
    }

    @Override
    public boolean contains(int x, int y) {
        Rectangle rectangle = new Rectangle(this.southPoint.x, this.southPoint.y,10, 10);
        return rectangle.contains(x,y);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Point northWestPoint = new Point(getLocation().x, getLocation().y);
        corner = new Point(northWestPoint);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        getOwner().setBounds(new Point(r.x + getOwner().getBounds().width, y), corner);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = null;
    }
}
