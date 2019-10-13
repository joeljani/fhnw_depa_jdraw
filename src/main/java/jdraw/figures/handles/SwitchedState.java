package jdraw.figures.handles;

public class SwitchedState implements HandleState {

    @Override
    public void doSwitch(AbstractFigureHandle figureHandle) {
        figureHandle.setState(new UnchangedState());
    }

    @Override
    public Boolean isSwitched() {
        return true;
    }
}
