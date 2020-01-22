package jdraw.figures;


import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Line extends AbstractFigure {

    private final Line2D line2D;

    public Line(int x, int y, int widthx, int widthy) {
        this.line2D = new Line2D.Float(0, 0, 0, 0);
     }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine((int) line2D.getX1(),(int) line2D.getY1(),(int) line2D.getX2(),(int) line2D.getY2());
    }

    @Override
    public void move(int dx, int dy) {
        if(!(dx == 0 && dy == 0)) {
            line2D.setLine(new Point2D.Double(line2D.getX1(), line2D.getY1()), new Point2D.Double(dx, dy));
            notifyListeners();
        }
    }

    @Override
    public Shape getShape() {
        return this.line2D;
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        line2D.setLine(origin, corner);
        notifyListeners();
    }
    
}
