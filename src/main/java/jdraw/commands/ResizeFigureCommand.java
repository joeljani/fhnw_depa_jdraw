package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

import java.awt.*;

public class ResizeFigureCommand implements DrawCommand {
    DrawModel model;
    Rectangle oldFigureBounds;
    Rectangle newFigureBounds;

    public ResizeFigureCommand(DrawModel model, Rectangle oldFigureBounds, Rectangle newFigureBounds) {
        System.out.println("ResizeFigureCommand called");
        this.model = model;
        this.oldFigureBounds = oldFigureBounds;
        this.newFigureBounds = newFigureBounds;
    }


    @Override
    public void undo() {
        Figure revertedFigure = model.getFigures().filter(figure ->
                figure.getBounds().x == newFigureBounds.x && figure.getBounds().y == newFigureBounds.y
                        && figure.getBounds().width == newFigureBounds.width
                        && figure.getBounds().height == newFigureBounds.height)
                .findAny().get();
        System.out.println("oldFigureBounds width: " + oldFigureBounds.width + " height: " + oldFigureBounds.height);
        revertedFigure.setBounds(new Point(newFigureBounds.x, newFigureBounds.y), new Point(oldFigureBounds.width, oldFigureBounds.height));
        System.out.println(revertedFigure.getBounds());
    }

    @Override
    public void redo() {

    }
}
