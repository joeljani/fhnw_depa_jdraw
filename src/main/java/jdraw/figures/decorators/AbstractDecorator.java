package jdraw.figures.decorators;

import jdraw.figures.AbstractFigure;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.util.List;

public class AbstractDecorator extends AbstractFigure {
    private Figure inner;
    private Figure outer;

    public AbstractDecorator(Figure inner, Figure outer) {
        this.inner = inner;
        this.outer = outer;
    }

    public Figure getInner() {
        return inner;
    }

    public void setInner(Figure inner) {
        this.inner = inner;
    }

    public void setOuter(Figure outer) {
        this.outer = outer;
    }

    public Figure getOuter() {
        return outer;
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        inner.addFigureListener(listener);
    }

    @Override
    public Figure clone() {
        return inner.clone();
    }

    @Override
    public boolean isSame(Figure other) {
        return this.inner == other || this.inner.isSame(other);
    }

    @Override
    public boolean isSameImpl(Figure other) {
        return false; // ?
    }

    @Override
    public boolean isInstanceOf(Class<?> type) {
        return type.isAssignableFrom(this.getClass()) || inner.isInstanceOf(type);
    }

    @Override
    public <T> T getInstanceOf(Class<T> type) {
        if(type.isAssignableFrom(this.getClass())){
            return type.cast(this); // checked version of (T)this
        } else {
            return inner.getInstanceOf(type);
        }
    }

    @Override
    public List<FigureListener> getFigureListeners() {
        return inner.getFigureListeners();
    }

    @Override
    public Shape getShape() {
        return null; //!!!
    }

    @Override
    public boolean contains(int x, int y) {
        return inner.contains(x, y);
    }

    @Override
    public void draw(Graphics g) {
        inner.draw(g);
    }

    @Override
    public Rectangle getBounds() {
        return inner.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        return inner.getHandles();
    }

    @Override
    public void move(int dx, int dy) {
        inner.move(dx, dy);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        inner.removeFigureListener(listener);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        inner.setBounds(origin, corner);
    }
}
