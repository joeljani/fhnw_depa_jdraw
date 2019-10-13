package jdraw.figures.handles;

public interface HandleState {

    void doSwitch(AbstractFigureHandle figureHandle);
    Boolean isSwitched();

}
