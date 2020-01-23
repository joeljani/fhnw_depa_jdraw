package jdraw.figures.toolFactories;

import jdraw.figures.LineTool;
import jdraw.figures.RectTool;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class LineToolFactory extends AbstractToolFactory {

    @Override
    public DrawTool createTool(DrawContext context) {
        return new LineTool(context, getName(), getIconName());
    }
}
