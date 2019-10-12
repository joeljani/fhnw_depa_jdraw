package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractFigure implements Figure {

    private Shape shape;
    private final List<FigureListener> figureListeners = new CopyOnWriteArrayList<>();

    public AbstractFigure(Shape shape) {
        this.shape = shape;
    }

    public abstract Shape getShape();

    @Override
    public boolean contains(int x, int y) {
        return getShape().contains(x,y);
    }

    @Override
    public Rectangle getBounds() {
        return getShape().getBounds();
    }

    @Override
    public Figure clone() {
        return null;
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


    public void notifyListeners() {
        figureListeners.forEach(figureListener -> figureListener.figureChanged(new FigureEvent(this)));
    }
}
