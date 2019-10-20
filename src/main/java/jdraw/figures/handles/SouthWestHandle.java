package jdraw.figures.handles;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthWestHandle extends AbstractFigureHandle {
    private Point corner;


    public SouthWestHandle(AbstractFigure figure) {
        super(figure);
    }

    @Override
    public Figure getOwner() {
        return getFigure();
    }

    @Override
    public Point getLocation() {
        Point southWestPoint = new Point(getOwner().getBounds().x, getOwner().getBounds().y + getOwner().getBounds().height);
        return southWestPoint;
    }

    @Override
    public void draw(Graphics g) {
        Point southWestPoint = new Point(getOwner().getBounds().x, getOwner().getBounds().y + getOwner().getBounds().height);
        g.setColor(Color.WHITE);
        g.fillRect(southWestPoint.x - 3, southWestPoint.y -3, 10,10);
        g.setColor(Color.BLACK);
        g.drawRect(southWestPoint.x - 3, southWestPoint.y -3, 10,10);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
    }

    @Override
    public boolean contains(int x, int y) {
        Point southWestPoint = new Point(getOwner().getBounds().x, getOwner().getBounds().y + getOwner().getBounds().height);
        Rectangle rectangle = new Rectangle(southWestPoint.x, southWestPoint.y,10, 10);
        return rectangle.contains(x,y);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = new Point(getOwner().getBounds().x + getOwner().getBounds().width, getOwner().getBounds().y);;
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
