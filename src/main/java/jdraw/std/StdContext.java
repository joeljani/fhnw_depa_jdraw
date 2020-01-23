/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */
package jdraw.std;

import jdraw.figures.*;
import jdraw.figures.decorators.AbstractDecorator;
import jdraw.figures.decorators.LogDecorator;
import jdraw.figures.decorators.FillRedDecorator;
import jdraw.figures.decorators.GreenDecorator;
import jdraw.framework.*;
import jdraw.grid.Grid20;
import jdraw.grid.Grid50;
import jdraw.grid.SnapGrid;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Standard implementation of interface DrawContext.
 *
 * @see DrawView
 * @author Dominik Gruntz &amp; Christoph Denzler
 * @version 2.6, 24.09.09
 */
@SuppressWarnings("serial")
public class StdContext extends AbstractContext {

    private List<Figure> clipboard = new ArrayList<>();

    /**
     * Constructs a standard context with a default set of drawing tools.
     * @param view the view that is displaying the actual drawing.
     */
    public StdContext(DrawView view) {
        super(view, null);
    }

    /**
     * Constructs a standard context. The drawing tools available can be
     * parameterized using <code>toolFactories</code>.
     *
     * @param view  the view that is displaying the actual drawing.
     * @param toolFactories  a list of DrawToolFactories that are available to the user
     */
    public StdContext(DrawView view, List<DrawToolFactory> toolFactories) {
        super(view, toolFactories);
    }

    /**
     * Creates and initializes the "Edit" menu.
     *
     * @return the new "Edit" menu.
     */
    @Override
    protected JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        final JMenuItem undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
        editMenu.add(undo);
        undo.addActionListener(e -> {
                    final DrawCommandHandler h = getModel().getDrawCommandHandler();
                    if (h.undoPossible()) {
                        h.undo();
                    }
                }
        );

        final JMenuItem redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke("control Y"));
        editMenu.add(redo);
        redo.addActionListener(e -> {
                    final DrawCommandHandler h = getModel().getDrawCommandHandler();
                    if (h.redoPossible()) {
                        h.redo();
                    }
                }
        );
        editMenu.addSeparator();

        JMenuItem sa = new JMenuItem("SelectAll");
        sa.setAccelerator(KeyStroke.getKeyStroke("control A"));
        editMenu.add(sa);
        sa.addActionListener( e -> {
                    getModel().getFigures().forEachOrdered(f -> getView().addToSelection(f));
                    getView().repaint();
                }
        );

        editMenu.addSeparator();
        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(e -> System.out.println("cut"));

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(e -> System.out.println("copied"));
        copy.addActionListener(e -> clipboard = getView().getSelection()
                                    .stream()
                                    .map(Figure::clone)
                                    .collect(Collectors.toList()));

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(e -> {
                    System.out.println("pasted");
                    for (Figure f : clipboard) {
                        getModel().addFigure(f);
                        getView().getSelection().add(f);
                        f.move(10, 10);
                    }
                    showStatusText("Eingefügt");
                });

        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        editMenu.addSeparator();
        JMenuItem clear = new JMenuItem("Clear");
        editMenu.add(clear);
        clear.addActionListener(e -> {
            getModel().removeAllFigures();
        });

        editMenu.addSeparator();
        JMenuItem group = new JMenuItem("Group");
        group.addActionListener(e -> {
            List<Figure> figuresToGroup = getView().getSelection();
            if(figuresToGroup != null && figuresToGroup.size() >= 2) {
                GroupFigure groupFigure = new GroupFigure(figuresToGroup);
                figuresToGroup.stream().forEach(figure -> getModel().removeFigure(figure));
                getModel().addFigure(groupFigure);
                getView().addToSelection(groupFigure);
            }
        });
        editMenu.add(group);

        JMenuItem ungroup = new JMenuItem("Ungroup");
        editMenu.add(ungroup);
        ungroup.addActionListener(e -> {
            getView().getSelection()
                    .stream()
                    .filter(figure -> figure.isInstanceOf(GroupFigure.class)) //there exists a groupfigure (undearneath the decorators)
                    .forEach(groupFigure -> {
                        System.out.println(groupFigure.getClass());
                        GroupFigure groupToDissolve = groupFigure.getInstanceOf(GroupFigure.class); //get the groupfigure
                        groupToDissolve.getFigureParts().forEach(part -> {
                            getModel().addFigure(part);
                            getView().addToSelection(part);
                        });
                        getModel().removeFigure(groupFigure);
                        getView().removeFromSelection(groupFigure);
                    });
        });

        editMenu.addSeparator();

        JMenu orderMenu = new JMenu("Order...");
        JMenuItem frontItem = new JMenuItem("Bring To Front");
        frontItem.addActionListener(e -> {
            bringToFront(getView().getModel(), getView().getSelection());
        });
        orderMenu.add(frontItem);
        JMenuItem backItem = new JMenuItem("Send To Back");
        backItem.addActionListener(e -> {
            sendToBack(getView().getModel(), getView().getSelection());
        });
        orderMenu.add(backItem);
        editMenu.add(orderMenu);

        JMenu grid = new JMenu("Grid...");

        JRadioButtonMenuItem grid20 = new JRadioButtonMenuItem("Grid20");
        grid20.addActionListener(e -> getView().setGrid(new Grid20()));

        JRadioButtonMenuItem grid50 = new JRadioButtonMenuItem("Grid50");
        grid50.addActionListener(e -> getView().setGrid(new Grid50()));

        JRadioButtonMenuItem snapGrid = new JRadioButtonMenuItem("SnapGrid");
        snapGrid.addActionListener(e -> getView().setGrid(new SnapGrid(this)));

        JRadioButtonMenuItem noGrid = new JRadioButtonMenuItem("Kein Grid");
        noGrid.addActionListener(e -> getView().setGrid(null));

        ArrayList<JRadioButtonMenuItem> grids = new ArrayList<>();
        grids.add(grid20);
        grids.add(grid50);
        grids.add(snapGrid);
        grids.add(noGrid);

        grid20.addActionListener(e -> grids.stream().filter(g -> g.getText() != "Grid20").forEach(g -> g.setSelected(false)));
        grid50.addActionListener(e -> grids.stream().filter(g -> g.getText() != "Grid50").forEach(g -> g.setSelected(false)));
        snapGrid.addActionListener(e -> grids.stream().filter(g -> g.getText() != "SnapGrid").forEach(g -> g.setSelected(false)));
        noGrid.addActionListener(e -> grids.stream().filter(g -> g.getText() != "Kein Grid").forEach(g -> g.setSelected(false)));

        JMenuItem greenDecorator = new JMenuItem("Toggle Green Decorator");
        editMenu.add(greenDecorator);
        greenDecorator.addActionListener(e -> {
            getView().getSelection().stream().forEach(figure -> {
                if(figure.isInstanceOf(GreenDecorator.class)) {
                    GreenDecorator greenDec = figure.getInstanceOf(GreenDecorator.class); //get the GreenDecorator
                    if(greenDec.getOuter() != null) {
                        ((AbstractDecorator) greenDec.getOuter()).setInner(greenDec.getInner());
                        deleteDecorator(greenDec, greenDec.getOuter());
                    } else replaceOldWithNewFigure(figure, ((AbstractDecorator) figure).getInner());
                } else {
                    replaceOldWithNewFigure(figure, new GreenDecorator(figure, null));
                }
            });
                }
        );

        JMenuItem redFillerDecorator = new JMenuItem("Toggle Redfiller Decorator");
        editMenu.add(redFillerDecorator);
        redFillerDecorator.addActionListener(e -> {
                    getView().getSelection().stream().forEach(figure -> {
                        if(figure.isInstanceOf(FillRedDecorator.class)) {
                            FillRedDecorator fillRedDec = figure.getInstanceOf(FillRedDecorator.class); //get the FillRedDecorator
                            if(fillRedDec.getOuter() != null) {
                                ((AbstractDecorator) fillRedDec.getOuter()).setInner(fillRedDec.getInner());
                                deleteDecorator(fillRedDec, fillRedDec.getOuter());
                            } else replaceOldWithNewFigure(figure, ((AbstractDecorator) figure).getInner());
                        } else {
                            replaceOldWithNewFigure(figure, new FillRedDecorator(figure, null));
                        }
                    });
                }
        );

        JMenuItem logDecorator = new JMenuItem("Toggle logDecorator");
        editMenu.add(logDecorator);
        logDecorator.addActionListener(e -> {
                    getView().getSelection().stream().forEach(figure -> {
                        if(figure.isInstanceOf(LogDecorator.class)) { //nicht besser hasInstanceOf??
                            LogDecorator logDec = figure.getInstanceOf(LogDecorator.class); //get the LogDecorator
                            if(logDec.getOuter() != null) {
                                ((AbstractDecorator) logDec.getOuter()).setInner(logDec.getInner());
                                deleteDecorator(logDec, logDec.getOuter());
                            } else replaceOldWithNewFigure(figure, ((AbstractDecorator) figure).getInner());
                        } else {
                            System.out.println("logDecorator activated");
                            replaceOldWithNewFigure(figure, new LogDecorator(figure, null));
                        }
                    });
                }
        );


        grid.add(noGrid);
        grid.add(grid20);
        grid.add(grid50);
        grid.add(snapGrid);
        editMenu.add(grid);

        return editMenu;
    }

    private void replaceOldWithNewFigure(Figure old, Figure decorated) {
        if(old instanceof AbstractDecorator) ((AbstractDecorator) old).setOuter(decorated);
        getView().removeFromSelection(old);
        getModel().removeFigure(old);
        getModel().addFigure(decorated);
        getView().addToSelection(decorated);
    }

    private void deleteDecorator(Figure decorator, Figure outer) {
        getView().removeFromSelection(decorator);
        getModel().removeFigure(decorator);
        getView().addToSelection(outer);
        getView().repaint();
    }

    /**
     * Creates and initializes items in the file menu.
     *
     * @return the new "File" menu.
     */
    @Override
    protected JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        fileMenu.add(open);
        open.setAccelerator(KeyStroke.getKeyStroke("control O"));
        open.addActionListener(e -> doOpen());

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke("control S"));
        fileMenu.add(save);
        save.addActionListener(e ->	doSave());

        JMenuItem exit = new JMenuItem("Exit");
        fileMenu.add(exit);
        exit.addActionListener(e -> System.exit(0));

        return fileMenu;
    }

    @Override
    protected void doRegisterDrawTools() {
        getToolFactories()
                .stream()
                .filter(Objects::nonNull)
                .forEach(concreteToolFactory -> addTool(concreteToolFactory.createTool(this)));
    }

    /**
     * Changes the order of figures and moves the figures in the selection
     * to the front, i.e. moves them to the end of the list of figures.
     * @param model model in which the order has to be changed
     * @param selection selection which is moved to front
     */
    public void bringToFront(DrawModel model, List<Figure> selection) {
        // the figures in the selection are ordered according to the order in the model
        List<Figure> orderedSelection = model.getFigures().filter(f -> selection.contains(f)).collect(Collectors.toList());
        Collections.reverse(orderedSelection);
        int pos = (int) model.getFigures().count();
        for (Figure f : orderedSelection) {
            model.setFigureIndex(f, --pos);
        }
    }

    /**
     * Changes the order of figures and moves the figures in the selection
     * to the back, i.e. moves them to the front of the list of figures.
     * @param model model in which the order has to be changed
     * @param selection selection which is moved to the back
     */
    public void sendToBack(DrawModel model, List<Figure> selection) {
        // the figures in the selection are ordered according to the order in the model
        List<Figure> orderedSelection = model.getFigures().filter(f -> selection.contains(f)).collect(Collectors.toList());
        int pos = 0;
        for (Figure f : orderedSelection) {
            model.setFigureIndex(f, pos++);
        }
    }

    /**
     * Handles the saving of a drawing to a file.
     */
    private void doSave() {
        JFileChooser chooser = new JFileChooser(getClass().getResource("").getFile());
        chooser.setDialogTitle("Save Graphic");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);

        chooser.setFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.draw)", "draw"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.xml)", "xml"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.json)", "json"));

        int res = chooser.showSaveDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            // save graphic
            File file = chooser.getSelectedFile();
            FileFilter filter = chooser.getFileFilter();
            if(filter instanceof FileNameExtensionFilter && !filter.accept(file)) {
                file = new File(chooser.getCurrentDirectory(), file.getName() + "." + ((FileNameExtensionFilter)filter).getExtensions()[0]);
            }
            System.out.println("save current graphic to file " + file.getName() + " using format "
                    + ((FileNameExtensionFilter)filter).getExtensions()[0]);
        }
    }

    /**
     * Handles the opening of a new drawing from a file.
     */
    private void doOpen() {
        JFileChooser chooser = new JFileChooser(getClass().getResource("")
                .getFile());
        chooser.setDialogTitle("Open Graphic");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public String getDescription() {
                return "JDraw Graphic (*.draw)";
            }

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".draw");
            }
        });
        int res = chooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            // read jdraw graphic
            System.out.println("read file "
                    + chooser.getSelectedFile().getName());
        }
    }

}

