package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

import java.util.List;

public class RemoveSelectionCommand implements DrawCommand {
    private List<Figure> selection;
    private DrawModel model;
    //TODO: Index of each fig

    public RemoveSelectionCommand(DrawModel model, List<Figure> selection) {
        this.model = model;
        this.selection = selection;
    }

    @Override
    public void redo() {
        selection.stream().forEach(figure -> this.model.removeFigure(figure));
    }

    @Override
    public void undo() {
        selection.stream().forEach(figure -> this.model.addFigure(figure));
    }
}
