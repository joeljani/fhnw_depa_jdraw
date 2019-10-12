package jdraw.figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;


public class Oval extends AbstractRectangularShapedFigure {

    public Oval(int x, int y, int dx, int dy) {
        super(new Ellipse2D.Float(0,0,0,0));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect((int) getShape().getX(), (int) getShape().getY(), (int) getShape().getWidth(), (int) getShape().getHeight(), (int) getShape().getWidth() + 100, (int) getShape().getHeight() + 100);
        g.setColor(Color.BLACK);
        g.drawRoundRect((int) getShape().getX(), (int) getShape().getY(), (int) getShape().getWidth(), (int) getShape().getHeight(), (int) getShape().getWidth() + 100, (int) getShape().getHeight() + 100);
    }

}
