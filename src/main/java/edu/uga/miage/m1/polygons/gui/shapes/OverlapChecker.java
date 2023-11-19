package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class OverlapChecker {

    public OverlapChecker(){

    }

    public Boolean overlap(Circle circle1, Circle circle2){
        double distanceBtwCenters=Math.sqrt(Math.pow((circle1.x-circle2.x), 2) +
            Math.pow((circle1.y-circle2.y),2));
        return distanceBtwCenters<= 50*2;
    }
    
    public Boolean overlap(Circle circle, Square square){

        Point2D circleCenter= new Point2D.Double(circle.getX(), circle.getY());

        int[] xcoords = { square.getX()-50, square.getX()+50,square.getX()+50, square.getX()-50};
        int[] ycoords = {square.getY()+50, square.getY()+50, square.getY()-50, square.getY()-50};

        for(int i=0; i<xcoords.length; i++){
            int j=(i+1)%xcoords.length;
            
            Point2D point1 =new Point2D.Double(xcoords[i], ycoords[i]);
            Point2D point2= new Point2D.Double(xcoords[j], ycoords[j]);
            Line2D Line= new Line2D.Double(point1, point2);

            if (Line.ptLineDist(circleCenter)>=50){
                return true;
            } 
        }

        return false;
    }
    
    public Boolean overlap(Circle circle, Triangle triangle){
        Point2D circleCenter= new Point2D.Double(circle.getX(), circle.getY());
        int[] xcoords = {triangle.getX() + 25, triangle.getX(), triangle.getX()+ 50 };
        int[] ycoords = {triangle.getY(), triangle.getY()+ 50, triangle.getY()+ 50 };

        for(int i=0; i<xcoords.length; i++){
            int j=(i+1)%xcoords.length;
            
            Point2D point1 =new Point2D.Double(xcoords[i], ycoords[i]);
            Point2D point2= new Point2D.Double(xcoords[j], ycoords[j]);
            Line2D Line= new Line2D.Double(point1, point2);

            if (Line.ptLineDist(circleCenter)>=50){
                return true;
            } 
        }

        return false;
    }

    public Boolean overlap(Square square1, Square square2){
        Area areaSquare1 = square1.getArea();
        Area areaSquare2 = square2.getArea();

        areaSquare1.intersect(areaSquare2);
        return !areaSquare1.isEmpty();
    }

    public Boolean overlap(Square square, Triangle triangle){

        return true;
    }

    public Boolean overlap(Square square, Circle circle){
        return overlap(circle, square);
    }

    public Boolean overlap(Triangle triangle, Square square){
        return overlap(square, triangle);
    }

    public Boolean overlap(Triangle triangle, Circle circle){
        return overlap(circle, triangle);
    }


    public Boolean overlap(Triangle triangle1, Triangle triangle2){

        return true;
    }
}
