package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthHandle extends AbstractFigureHandle {

    private Point corner;
    private Point northPoint;

    public NorthHandle(Figure figure) {
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
        this.northPoint = new Point((getOwner().getBounds().getLocation().x + halfOfWidth), getOwner().getBounds().getLocation().y);
        g.setColor(Color.WHITE);
        g.fillRect(northPoint.x-3, northPoint.y -3, 10,10);
        g.setColor(Color.BLACK);
        g.drawRect(northPoint.x-3, northPoint.y -3, 10,10);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
    }

    @Override
    public boolean contains(int x, int y) {
        Rectangle rectangle = new Rectangle(this.northPoint.x, this.northPoint.y,10, 10);
        return rectangle.contains(x,y);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Point southEastPoint = new Point(getOwner().getBounds().x + getOwner().getBounds().width, getOwner().getBounds().y + getOwner().getBounds().height);
        corner = new Point(southEastPoint);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        System.out.println(getLocation().x + " " + getLocation().y);
        getOwner().setBounds(new Point(r.x, y), corner);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = null;
    }
}
