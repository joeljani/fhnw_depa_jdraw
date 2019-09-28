/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jdraw.framework.*;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Joel Jani
 *
 */
public class StdDrawModel implements DrawModel {

	private final List<Figure> figures = new ArrayList<>();
	private final List<DrawModelListener> drawModelListeners = new ArrayList<>();

	public int countListeners = 0;


	@Override
	public void addFigure(Figure f) {
		if(!figures.contains(f)) {
			figures.add(f);
			System.out.println(f.getBounds().x);
			//notify
			drawModelListeners.forEach(d -> d.modelChanged(new DrawModelEvent(this, f, DrawModelEvent.Type.FIGURE_ADDED)));
			this.countListeners++;
			System.out.println("countlisteners: " + countListeners);
		}
	}

	@Override
	public Stream<Figure> getFigures() {
		return figures.stream(); // Only guarantees, that the application starts -- has to be replaced !!!
	}

	@Override
	public void removeFigure(Figure f) {
		if(figures.contains(f)) {
			figures.remove(f);
			//notify
			drawModelListeners.forEach(d -> d.modelChanged(new DrawModelEvent(this, f, DrawModelEvent.Type.FIGURE_REMOVED)));
		}
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		if(!drawModelListeners.contains(listener)) drawModelListeners.add(listener);
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		drawModelListeners.remove(listener);
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	@Override
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		figures.set(index, f);
		//notify
		drawModelListeners.forEach(d -> d.modelChanged(new DrawModelEvent(this, f, DrawModelEvent.Type.FIGURE_CHANGED)));
	}

	@Override
	public void removeAllFigures() {
		figures.clear();
		//notify
		drawModelListeners.forEach(d -> d.modelChanged(new DrawModelEvent(this, null, DrawModelEvent.Type.DRAWING_CLEARED)));
		//drawModelListeners.clear();
	}

}
