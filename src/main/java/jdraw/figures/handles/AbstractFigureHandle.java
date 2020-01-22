package jdraw.figures.handles;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

public abstract class AbstractFigureHandle implements FigureHandle, Cloneable {

    private Figure figure;
    private HandleState state;

    public AbstractFigureHandle(Figure figure) {
        this.figure = figure;
    }

    public void setState(HandleState state) {
        this.state = state;
    };

    public HandleState getState() {
        return state;
    }

    public Figure getFigure() {
        return figure;
    }
}
