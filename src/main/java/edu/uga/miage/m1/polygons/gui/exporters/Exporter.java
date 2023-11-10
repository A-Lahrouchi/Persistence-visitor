package edu.uga.miage.m1.polygons.gui.exporters;

import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.ListOfShapes;

public interface Exporter {
    public String getShapesAsString(ListOfShapes listOfShapes);

    public ListOfShapes readShapes();

    public void writeShapes(ListOfShapes listOfShapes);
}
