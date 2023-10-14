package edu.uga.miage.m1.polygons.gui.persistence;

import javax.json.Json;
import javax.json.JsonObject;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href=
 *         "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {
    private JsonObject representation = null;

    public JSonVisitor() {
    }

    @Override
    public void visit(Circle circle) {
        representation = Json.createObjectBuilder()
                .add("type", "circle")
                .add("x", circle.getX())
                .add("y", circle.getY())
                .build();
    }

    @Override
    public void visit(Square square) {
        representation = Json.createObjectBuilder()
                .add("type", "square")
                .add("x", square.getX())
                .add("y", square.getY())
                .build();
    }

    @Override
    public void visit(Triangle triangle) {
        representation = Json.createObjectBuilder()
                .add("type", "triangle")
                .add("x", triangle.getX())
                .add("y", triangle.getY())
                .build();
    }

    public JsonObject getRepresentation() {
        return representation;
    }

/* 
    private String representation = null;

    public JSonVisitor() {
    }

    @Override
    public void visit(Circle circle) {
        // TODO Request callback for the circle
        representation = String.format("""
                {
                    "type": "circle",
                    "x": %d,
                    "y": %d
                }
                """, circle.getX(), circle.getY());
        representation = Json.createObjectBuilder()
                .add("type", "circle")
                .add("x", circle.getX())
                .add("y", circle.getY())
                .build();
    }

    @Override
    public void visit(Square square) {
        // TODO Request callback for the square
        representation = String.format("""
                {
                    "type": "square",
                    "x": %d,
                    "y": %d
                }
                """, square.getX(), square.getY());
    }

    @Override
    public void visit(Triangle triangle) {
        // TODO Request callback for the triangle
        representation = String.format("""
                {
                    "type": "triangle",
                    "x": %d,
                    "y": %d
                }
                """, triangle.getX(), triangle.getY());
    }

    
    //  * @return the representation in JSon example for a Circle
    //  *
    //  *         <pre>
    //  * {@code
    //  *  {
    //  *     "shape": {
    //  *     	  "type": "circle",
    //  *        "x": -25,
    //  *        "y": -25
    //  *     }
    //  *  }
    //  * }
    //  *         </pre>
    public String getRepresentation() {
        return representation;
    }
 */
}
