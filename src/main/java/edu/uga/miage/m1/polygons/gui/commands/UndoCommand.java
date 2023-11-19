package edu.uga.miage.m1.polygons.gui.commands;

import java.awt.Graphics2D;
import java.util.ArrayList;

import edu.uga.miage.m1.polygons.gui.shapes.*;


public class UndoCommand implements Command {

    Graphics2D g2;

    ArrayList<SimpleShape> allShapes;

    SimpleShape shape; 

    public UndoCommand(Graphics2D g2, SimpleShape shape, ArrayList<SimpleShape> allShapes){
        this.allShapes= allShapes;
        this.g2=g2;
        this.shape=shape;
    }

    public void execute(){
        shape.erase(g2, allShapes);
    }
    
}
