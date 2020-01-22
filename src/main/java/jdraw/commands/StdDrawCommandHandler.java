package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawCommandHandler;

import java.util.Stack;

public class StdDrawCommandHandler implements DrawCommandHandler {
    private Stack<DrawCommand> doCommands = new Stack<>();
    private Stack<DrawCommand> redoCommands = new Stack<>();
    private ScriptCommand scriptCommand = null;
    private boolean scriptOn = false;


    @Override
    public void addCommand(DrawCommand cmd) {
        doCommands.push(cmd);
        redoCommands.push(cmd);
        scriptCommand.addCommand(cmd);
    }

    @Override
    public void undo() {
        if(doCommands.empty()) System.out.println("do commands empty");
        if(redoCommands.empty()) System.out.println("do commands empty");
        System.out.println("do Commands : " + doCommands.stream().count());
        System.out.println("redo Commands : " + redoCommands.stream().count());
        if(!doCommands.empty()) {
            doCommands.pop().undo();
        }
    }

    @Override
    public void redo() {
        System.out.println("do Commands : " + doCommands.stream().count());
        System.out.println("redo Commands : " + redoCommands.stream().count());
        //redoCommands.stream().forEach(command -> System.out.println(command));
        if(!doCommands.empty()) {
            redoCommands.push(doCommands.peek());
            redoCommands.peek().redo();
        }
    }

    @Override
    public boolean undoPossible() {
        if(doCommands.size() > 0) return true; else return false;
    }

    @Override
    public boolean redoPossible() {
        if(redoCommands.size() > 0) return true; else return false;
    }

    @Override
    public void beginScript() {
        //System.out.println("script begun");
        scriptCommand = new ScriptCommand();
    }

    @Override
    public void endScript() {
        //System.out.println("script ended");
        //System.out.println(scriptCommand);
        doCommands.push(scriptCommand);
        redoCommands.push(scriptCommand);
        scriptCommand = null;
    }

    @Override
    public void clearHistory() {
        doCommands.clear();
        redoCommands.clear();
    }
}
