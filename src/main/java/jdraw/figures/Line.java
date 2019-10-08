package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.awt.geom.Line2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class Line implements Figure {

    private final List<FigureListener> figureListeners = new CopyOnWriteArrayList<>();

    private final Line2D line2D;

    public Line(int x, int y, int dx, int dy) {
        this.line2D = new Line2D.Float(0, 0, 0, 0);
     }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine((int) line2D.getX1(),(int) line2D.getY1(),(int) line2D.getX2(),(int) line2D.getY2());
    }

    @Override
    public void move(int dx, int dy) {
        if(!(dx == 0 && dy == 0)) {
            line2D.setLine(new Point2D.Double(line2D.getX1(), line2D.getY1()), new Point2D.Double(dx, dy));
            notifyListeners();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return line2D.contains(x,y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        line2D.setLine(origin, corner);
        notifyListeners();
    }

    @Override
    public Rectangle getBounds() {
        return this.line2D.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        if(!figureListeners.contains(listener)) figureListeners.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        figureListeners.remove(listener);
    }

    @Override
    public Figure clone() {
        return null;
    }


    private void notifyListeners() {
        figureListeners.forEach(figureListener -> figureListener.figureChanged(new FigureEvent(this)));
    }
}
