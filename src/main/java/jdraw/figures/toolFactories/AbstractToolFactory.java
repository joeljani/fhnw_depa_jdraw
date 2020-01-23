package jdraw.figures.toolFactories;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawToolFactory;

public abstract class AbstractToolFactory implements DrawToolFactory {
    private String name = null;
    private String iconName = null;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getIconName() {
        return this.iconName;
    }

    @Override
    public void setIconName(String name) {
        this.iconName = name;
    }
}
