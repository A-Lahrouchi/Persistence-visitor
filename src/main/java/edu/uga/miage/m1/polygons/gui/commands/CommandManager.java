package edu.uga.miage.m1.polygons.gui.commands;

import java.util.LinkedList;

public class CommandManager {

    private static CommandManager singletonCommandManager;

    private LinkedList<Command> undoStack;
    private LinkedList<Command> redoStack;

    private CommandManager() {
        this.undoStack = new LinkedList<>();
        this.redoStack = new LinkedList<>();
    }

    public static CommandManager getInstance() {
        if (singletonCommandManager == null) {
            singletonCommandManager = new CommandManager();
        }
        return singletonCommandManager;
    }

    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undoCommand() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            if (command.isUndoable()) {
                command.undo();
                redoStack.push(command);
            } else {
                System.out.println("Cannot undo: " + command.getClass().getSimpleName());
            }
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void redoCommand() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        } else {
            System.out.println("Nothing to redo.");
        }
    }

}
