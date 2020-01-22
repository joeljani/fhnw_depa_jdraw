package jdraw.figures;

import jdraw.figures.handles.*;
import jdraw.framework.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GroupFigure implements Figure, FigureGroup {

    private ArrayList<Figure> parts;
    private final List<FigureHandle> figureHandles = new ArrayList<>();
    private final List<FigureListener> figureListeners = new CopyOnWriteArrayList<>();


    public GroupFigure(List<Figure> selectedFigures) {
        if (selectedFigures == null || selectedFigures.size() == 0) throw new IllegalArgumentException();
        this.parts = new ArrayList<>(selectedFigures);
    }

    @Override
    public void draw(Graphics g) {
        parts.forEach(f->f.draw(g));
    }

    @Override
    public void move(int dx, int dy) {
        if(dx != 0 || dy != 0) {
            parts.forEach(f->f.move(dx,dy));
            notifyListeners();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return parts.stream().anyMatch(f -> f.contains(x,y));
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        System.out.println("called");
        getBounds().setFrameFromDiagonal(origin, corner);
        notifyListeners();
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = parts.get(0).getBounds();
        parts.stream().skip(1).forEach(f -> bounds.add(f.getBounds()));
        return bounds;
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

    @Override
    public GroupFigure clone() {
        return null;
    }

    @Override
    public boolean isSame(Figure other) {
        return isSameImpl(other);
    }

    @Override
    public boolean isSameImpl(Figure other) {
        return this == other;
    }

    @Override
    public boolean isInstanceOf(Class<?> type) {
        System.out.println("this.getClass()" + this.getClass());
        System.out.println("param type: " + type);
        return this.getClass() == type;
    }

    @Override
    public <T> T getInstanceOf(Class<T> type) {
        return type.cast(this);
    }

    @Override
    public List<FigureListener> getFigureListeners() {
        return this.figureListeners;
    }

    @Override
    public Iterable<Figure> getFigureParts() {
        return Collections.unmodifiableList(parts);
    }
}
