package jdraw.figures.toolFactories;

import jdraw.figures.RectTool;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class RectangleToolFactory extends AbstractToolFactory {

    @Override
    public DrawTool createTool(DrawContext context) {
        return new RectTool(context, getName(), getIconName());
    }
}
