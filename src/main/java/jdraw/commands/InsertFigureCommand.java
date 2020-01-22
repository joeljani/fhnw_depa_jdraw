package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class InsertFigureCommand implements DrawCommand {
    private DrawModel model;
    private Figure figure;

    public InsertFigureCommand(DrawModel model, Figure figure) {
        this.model = model;
        this.figure = figure;
    }

    @Override
    public void redo() {
        Figure newFigure = figure.clone();
        model.addFigure(newFigure);
    }

    @Override
    public void undo() {
        model.removeFigure(figure);
    }
}
