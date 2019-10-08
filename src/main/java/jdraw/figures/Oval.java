package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Oval implements Figure {
    private final List<FigureListener> figureListeners = new CopyOnWriteArrayList<>();

    private final Ellipse2D ellipse2D;

    public Oval(int x, int y, int dx, int dy) {
        this.ellipse2D = new Ellipse2D.Float(0,0,0,0);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRoundRect((int) ellipse2D.getX(), (int) ellipse2D.getY(), (int) ellipse2D.getWidth(), (int) ellipse2D.getHeight(), (int) ellipse2D.getWidth() + 100, (int) ellipse2D.getHeight() + 100);
    }

    @Override
    public void move(int dx, int dy) {
        if(!(dx == 0 && dy == 0)) {
            ellipse2D.setFrame(new Point2D.Double(ellipse2D.getX(), ellipse2D.getY()), new Dimension(dx, dy));
            notifyListeners();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return ellipse2D.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        ellipse2D.setFrameFromDiagonal(origin, corner);
        notifyListeners();
    }

    @Override
    public Rectangle getBounds() {
        return ellipse2D.getBounds();
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

    private void notifyListeners() {
        figureListeners.forEach(figureListener -> figureListener.figureChanged(new FigureEvent(this)));
    }

    @Override
    public Figure clone() {
        return null;
    }
}
