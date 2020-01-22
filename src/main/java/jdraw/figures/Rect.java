/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.*;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */
public class Rect extends AbstractRectangularShapedFigure {
	private static final long serialVersionUID = 9120181044386552132L;

	/**
	 * Create a new rectangle of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the rectangle
	 * @param y the y-coordinate of the upper left corner of the rectangle
	 * @param w the rectangle's width
	 * @param h the rectangle's height
	 */
	public Rect(int x, int y, int w, int h) {
		super(new Rectangle(x,y,w,h));
	}

	/**
	 * Draw the rectangle to the given graphics context.
	 * @param g the graphics context to use for drawing.
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int) getShape().getX(),(int) getShape().getY(),(int) getShape().getWidth(),(int) getShape().getHeight());
		g.setColor(Color.BLACK );
		g.drawRect((int) getShape().getX(),(int) getShape().getY(),(int) getShape().getWidth(),(int) getShape().getHeight());
	}

	@Override
	public Rect clone() {
		Rect r = (Rect) super.clone();
		return r;
	}
}
