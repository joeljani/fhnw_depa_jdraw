package jdraw.commands;

import jdraw.framework.DrawCommand;

import java.util.LinkedList;
import java.util.List;

public class ScriptCommand implements DrawCommand {
    private List<DrawCommand> commands = new LinkedList<>();

    @Override
    public void redo() {
        commands.forEach(command -> command.redo());
    }

    @Override
    public void undo() {
        commands.forEach(command -> command.undo());
    }

    public void addCommand(DrawCommand cmd) {
        commands.add(cmd);
    }
}
