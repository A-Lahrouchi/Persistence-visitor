package edu.uga.miage.m1.polygons.gui.commands;

public interface Command {
    public void execute();
    public void undo();
    public boolean isUndoable();
}
