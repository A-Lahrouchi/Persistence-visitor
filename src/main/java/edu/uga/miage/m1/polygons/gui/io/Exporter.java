package edu.uga.miage.m1.polygons.gui.io;

import edu.uga.miage.m1.polygons.gui.listofshapes.ListOfShapes;

public interface Exporter {
    public String getShapesAsString(ListOfShapes listOfShapes);

    public void writeShapes(ListOfShapes listOfShapes);
}
