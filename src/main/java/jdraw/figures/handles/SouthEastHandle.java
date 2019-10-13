package jdraw.figures.handles;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthEastHandle extends AbstractFigureHandle {
    private Point corner;


    public SouthEastHandle(AbstractFigure figure) {
        super(figure);
    }

    @Override
    public Figure getOwner() {
        return getFigure();
    }

    @Override
    public Point getLocation() {
        Point southEastPoint = new Point(getOwner().getBounds().x + getOwner().getBounds().width, getOwner().getBounds().y + getOwner().getBounds().height);
        return southEastPoint;
    }

    @Override
    public void draw(Graphics g) {
        Point southEastPoint = new Point(getOwner().getBounds().x + getOwner().getBounds().width, getOwner().getBounds().y + getOwner().getBounds().height);
        g.setColor(Color.WHITE);
        g.fillRect(southEastPoint.x - 3, southEastPoint.y -3, 10,10);
        g.setColor(Color.BLACK);
        g.drawRect(southEastPoint.x - 3, southEastPoint.y -3, 10,10);
    }

    @Override
    public Cursor getCursor() {
        if(getState().isSwitched()) {
            getState().doSwitch(this);
            return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
        }
        return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
    }

    @Override
    public boolean contains(int x, int y) {
        Point southEastPoint = new Point(getOwner().getBounds().x + getOwner().getBounds().width, getOwner().getBounds().y + getOwner().getBounds().height);
        Rectangle rectangle = new Rectangle(southEastPoint.x, southEastPoint.y,10, 10);
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
            getState().doSwitch(this);
        }
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = null;
    }
}
