package jdraw.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import jdraw.figures.AbstractFigure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jdraw.figures.Rect;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

public class RectangleTest extends AbstractFigureTest {

	private AbstractFigure f;
	private int cnt;

	@Override
	AbstractFigure createFigure() {
		return new Rect(1,1,20,20);
	}

}
