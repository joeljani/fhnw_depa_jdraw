/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import jdraw.commands.StdDrawCommandHandler;
import jdraw.framework.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Joel Jani
 *
 */
public class StdDrawModel implements DrawModel, FigureListener {

	private final List<Figure> figures = new ArrayList<>();
	private final List<DrawModelListener> drawModelListeners = new CopyOnWriteArrayList<>();


	@Override
	public void addFigure(Figure f) {
		if(!figures.contains(f)) {
			figures.add(f);
			f.addFigureListener(this);
			notifyListeners(f, DrawModelEvent.Type.FIGURE_ADDED);
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
			f.removeFigureListener(this);
			notifyListeners(f, DrawModelEvent.Type.FIGURE_REMOVED);
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

	private DrawCommandHandler handler = new StdDrawCommandHandler();

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

		if(!figures.contains(f)) throw new IllegalArgumentException("Figure f doesn't exist");
		if(index < 0 || index >= figures.size()) throw new IndexOutOfBoundsException("Index doesn't match with amount of figures");

		int indexOfFigureToChange = 0;
		while(!figures.get(indexOfFigureToChange).equals(f)) {
			indexOfFigureToChange++;
		}

		//Verschiebung der Indizes der Figuren
		for (int i = indexOfFigureToChange-1; i >= index; i--) {
			int j = i + 1;
			figures.set(j, figures.get(i));
		}

		figures.set(index, f);
		notifyListeners(f, DrawModelEvent.Type.DRAWING_CHANGED);
	}

	@Override
	public void removeAllFigures() {
		for (Figure f: figures) {
			f.removeFigureListener(this);
		}
		figures.clear();
		notifyListeners(null, DrawModelEvent.Type.DRAWING_CLEARED);
	}

	private void notifyListeners(Figure f, DrawModelEvent.Type type) {
		drawModelListeners.forEach(d -> d.modelChanged(new DrawModelEvent(this, f, type)));
	}

	@Override
	public void figureChanged(FigureEvent e) {
		notifyListeners(e.getFigure(), DrawModelEvent.Type.FIGURE_CHANGED);
	}
}
