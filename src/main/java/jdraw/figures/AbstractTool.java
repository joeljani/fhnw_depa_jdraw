package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractTool implements DrawTool {

    /**
     * The context we use for drawing.
     */
    private final DrawContext context;

    /**
     * The context's view. This variable can be used as a shortcut, i.e.
     * instead of calling context.getView().
     */
    private final DrawView view;

    private final String toolName;

    /**
     * the image resource path.
     */
    private static final String IMAGES = "/images/";

    private String imageName;

    public AbstractTool(DrawContext context, String name, String imageName) {
        this.context = context;
        this.view = context.getView();
        this.toolName = name;
        this.imageName = imageName;
    }

    public DrawContext getContext () {
        return this.context;
    }

    public DrawView getView() {
        return this.view;
    }

    /**
     * Activates the Rectangle Mode. There will be a
     * specific menu added to the menu bar that provides settings for
     * Rectangle attributes
     */
    @Override
    public void activate() {
        getContext().showStatusText("Rectangle Mode");
    }

    /**
     * Deactivates the current mode by resetting the cursor
     * and clearing the status bar.
     * @see jdraw.framework.DrawTool#deactivate()
     */
    @Override
    public void deactivate() {
        getContext().showStatusText("");
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    @Override
    public String getName() {
        return this.toolName;
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + imageName));
    }
}
