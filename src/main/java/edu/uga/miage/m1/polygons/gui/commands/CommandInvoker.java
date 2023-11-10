package edu.uga.miage.m1.polygons.gui.commands;

public class CommandInvoker {

    private static CommandInvoker singletonCommandInvoker;

    private Command command;

    private CommandInvoker(){

    }

    public static CommandInvoker getInstance() {
        if (singletonCommandInvoker == null) {
            singletonCommandInvoker = new CommandInvoker();
        }
        return singletonCommandInvoker;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand(){
        command.execute();
    }

}
