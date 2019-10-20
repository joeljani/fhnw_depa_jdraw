package jdraw.figures;

import java.awt.*;
import java.awt.geom.RectangularShape;


public abstract class AbstractRectangularShapedFigure extends AbstractFigure {

    private RectangularShape shape;

    public AbstractRectangularShapedFigure(RectangularShape shape) {
        super(shape);
        this.shape = shape;
    }

    @Override
    public void move(int dx, int dy) {
        if(!(dx == 0 && dy == 0)) {
            getShape().setFrame(new Point((int) (getShape().getX() + dx), (int) (getShape().getY() + dy)) , getShape().getBounds().getSize());
            notifyListeners();
        }
    }



    @Override
    public void setBounds(Point origin, Point corner) {
        //TODO: Überprüfen ob was verändert wurde
        getShape().setFrameFromDiagonal(origin, corner);
        notifyListeners();
    }

    @Override
    public RectangularShape getShape() {
        return this.shape;
    }




}
