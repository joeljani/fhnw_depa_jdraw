package jdraw.figures.handles;

import jdraw.figures.AbstractFigure;
import jdraw.framework.FigureHandle;

public abstract class AbstractFigureHandle implements FigureHandle {

    private AbstractFigure figure;
    private HandleState state;

    public AbstractFigureHandle(AbstractFigure abstractFigure) {
        this.figure = abstractFigure;
        setState(new UnchangedState());
    }

    public void setState(HandleState state) {
        this.state = state;
    };

    public HandleState getState() {
        return state;
    }

    public AbstractFigure getFigure() {
        return figure;
    }
}
