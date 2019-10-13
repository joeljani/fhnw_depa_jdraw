package jdraw.figures.handles;

public class UnchangedState implements HandleState {

    @Override
    public void doSwitch(AbstractFigureHandle figureHandle) {
        figureHandle.setState(new SwitchedState());
    }

    @Override
    public Boolean isSwitched() {
        return false;
    }

}
