package jdraw.figures.handles;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthWestHandle extends AbstractFigureHandle {

    private Point corner;

    public NorthWestHandle(AbstractFigure figure) {
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
        Point loc = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(loc.x-3, loc.y -3, 10,10);
        g.setColor(Color.BLACK);
        g.drawRect(loc.x-3, loc.y -3, 10,10);
    }

    @Override
    public Cursor getCursor() {
        if(getState().isSwitched()) {
            getState().doSwitch(this);
            return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
        }
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
        Point southEastPoint = new Point(getOwner().getBounds().x + getOwner().getBounds().width, getOwner().getBounds().y + getOwner().getBounds().height);
        if(new Point(x,y).equals(southEastPoint)) {
            getState().doSwitch(this);
        }
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = null;
    }
}
