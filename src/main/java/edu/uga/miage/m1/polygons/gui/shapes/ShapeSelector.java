package edu.uga.miage.m1.polygons.gui.shapes;

import java.util.ArrayList;
import java.awt.geom.Area;
import java.awt.geom.Point2D;



public class ShapeSelector {

    ArrayList<SimpleShape> allShapes;

    public ShapeSelector(ArrayList<SimpleShape> allShapes){
        this.allShapes=allShapes;
    }

    public SimpleShape selectedShape (int eventX, int eventY){

        Point2D point = new Point2D.Double(eventX, eventY);
        ArrayList<SimpleShape> possiblySelectedShapes= new ArrayList<>();
        
        for(SimpleShape shape: allShapes){
            Area area = shape.getArea();
            if (area.contains(point)) {
                possiblySelectedShapes.add(shape);
            }
        }

        if(possiblySelectedShapes.size()>=1)
            return possiblySelectedShapes.get(possiblySelectedShapes.size()-1); 

        return null;
    }
    
}
