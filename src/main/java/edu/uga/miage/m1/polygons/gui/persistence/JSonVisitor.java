package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href=
 *         "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {
    private static JSonVisitor singletonJSonVisitor;

    private JSonVisitor() {

    }

    public static JSonVisitor getInstance() {
        if (singletonJSonVisitor == null) {
            singletonJSonVisitor = new JSonVisitor();
        }
        return singletonJSonVisitor;
    }

    @Override
    public String visit(Circle circle) {
        return getJsonShape("circle", circle.getX(), circle.getY());
    }

    @Override
    public String visit(Square square) {
        return getJsonShape("square", square.getX(), square.getY());
    }

    @Override
    public String visit(Triangle triangle) {
        return getJsonShape("triangle", triangle.getX(), triangle.getY());
    }

    /**
     * @return the representation in JSon example for a Circle
     *         {@code { "shape": { "type": "circle", "x": -25, "y": -25 } } }
     */
    public String getJsonShape(String shapeType, int x, int y) {
        return String.format("{\"type\":\"%s\",\"x\":%d,\"y\":%d}", shapeType, x, y);
    }
}
