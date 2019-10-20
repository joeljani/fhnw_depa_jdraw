package jdraw.figures;

import jdraw.figures.handles.*;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractFigure implements Figure {

    private Shape shape;
    private final List<FigureListener> figureListeners = new CopyOnWriteArrayList<>();
    private final List<FigureHandle> figureHandles = new ArrayList<>();

    public AbstractFigure(Shape shape) {
        this.shape = shape;
    }

    public AbstractFigure() {}

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
        figureHandles.add(new NorthWestHandle(this));
        figureHandles.add(new NorthHandle(this));
        figureHandles.add(new NorthEastHandle(this));
       //figureHandles.add(new EastHandle(this));
        figureHandles.add(new SouthEastHandle(this));
        figureHandles.add(new SouthHandle(this));
        figureHandles.add(new SouthWestHandle(this));
        //figureHandles.add(new WestHandle(this));
        return this.figureHandles;
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

    /******** Figurehandling ********/



}
