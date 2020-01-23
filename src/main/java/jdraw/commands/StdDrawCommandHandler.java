package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawCommandHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StdDrawCommandHandler implements DrawCommandHandler {

    private Stack<DrawCommand> undoStack = new Stack<>();
    private Stack<DrawCommand> redoStack = new Stack<>();
    private ScriptCommand scriptCommand = null;

    @Override
    public void addCommand(DrawCommand cmd) {
        redoStack.clear(); // update redoStack
        if(cmd != null) {
            if (scriptCommand != null) scriptCommand.addCommand(cmd);
            else undoStack.push(cmd);
        }
    }

    @Override
    public void undo() {
        if(undoPossible()) {
            DrawCommand command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        } else System.out.println("nothing to undo!");
    }

    @Override
    public void redo() {
        if(redoPossible()) {
            DrawCommand command = redoStack.pop();
            command.redo();
            undoStack.push(command);
        } else System.out.println("nothing to redo!");
    }

    @Override
    public boolean undoPossible() {
        return undoStack.size() > 0;
    }

    @Override
    public boolean redoPossible() {
        return redoStack.size() > 0;
    }

    @Override
    public void beginScript() {
        scriptCommand = new ScriptCommand();
    }

    @Override
    public void endScript() {
        ScriptCommand currentScript = scriptCommand;
        scriptCommand = null; //close it, so it doesn't add itself
        addCommand(currentScript); //add macro command (i.e ScriptCommand) to the undo stack
    }

    @Override
    public void clearHistory() {
        undoStack.clear();
        redoStack.clear();
    }
}
