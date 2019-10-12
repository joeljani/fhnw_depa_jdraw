package jdraw.figures;

import jdraw.framework.Figure;

import java.awt.*;
import java.awt.geom.RectangularShape;

public abstract class AbstractRectangularShapedFigure implements Figure {

    public RectangularShape shape;

    public AbstractRectangularShapedFigure(RectangularShape shape) {
        this.shape = shape;
    }

    public RectangularShape getShape() {
        return shape;
    }

    @Override
    public boolean contains(int x, int y) {
        return shape.contains(x, y);
    }

    @Override
    public Figure clone() {
        return null;
    }
}
