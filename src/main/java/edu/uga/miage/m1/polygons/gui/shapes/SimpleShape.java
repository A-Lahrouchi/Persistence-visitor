package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.util.ArrayList;

/**
 * This interface defines the <tt>SimpleShape</tt> extension. This extension
 * is used to draw shapes.
 * 
 * @author <a href=
 *         "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public interface SimpleShape {

    /**
     * Method to draw the shape of the extension.
     * 
     * @param g2 The graphics object used for painting.
     **/
    void draw(Graphics2D g2);

    Area getArea();

    void undo (Graphics2D g2, ArrayList<SimpleShape> allShapes);

    void erase (Graphics2D g2, ArrayList<SimpleShape> allShapes);

    void move (Graphics2D g2, ArrayList<SimpleShape> allShapes, int x, int y);

    void highlight(Graphics2D g2, Boolean highlight);

    int getX();

    int getY();
}