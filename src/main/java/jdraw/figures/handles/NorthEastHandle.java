package jdraw.figures.handles;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthEastHandle extends AbstractFigureHandle{

    private Point corner;

    public NorthEastHandle(AbstractFigure figure) {
        super(figure);
    }

    @Override
    public Figure getOwner() {
        return getFigure();
    }

    @Override
    public Point getLocation() {
        return getOwner().getBounds().getLocation();
    }

    @Override
    public void draw(Graphics g) {
        Point northEastPoint = new Point(getOwner().getBounds().x + getOwner().getBounds().width, getOwner().getBounds().y);
        g.setColor(Color.WHITE);
        g.fillRect(northEastPoint.x-3, northEastPoint.y -3, 10,10);
        g.setColor(Color.BLACK);
        g.drawRect(northEastPoint.x-3, northEastPoint.y -3, 10,10);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
    }

    @Override
    public boolean contains(int x, int y) {
        Point northEastPoint = new Point(getOwner().getBounds().x + getOwner().getBounds().width, getOwner().getBounds().y);
        Rectangle rectangle = new Rectangle(northEastPoint.x, northEastPoint.y, 10, 10);
        return rectangle.contains(x,y);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = new Point(getOwner().getBounds().x, getOwner().getBounds().y + getOwner().getBounds().height);
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
