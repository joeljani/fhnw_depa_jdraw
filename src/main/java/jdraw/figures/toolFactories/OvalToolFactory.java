package jdraw.figures.toolFactories;

import jdraw.figures.LineTool;
import jdraw.figures.OvalTool;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class OvalToolFactory extends AbstractToolFactory {

    @Override
    public DrawTool createTool(DrawContext context) {
        return new OvalTool(context, getName(), getIconName());
    }

}
