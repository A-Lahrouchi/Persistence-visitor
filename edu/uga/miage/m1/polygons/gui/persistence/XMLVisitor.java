package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href=
 *         "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {

    private String representation = null;

    public XMLVisitor() {
    }

    @Override
    public void visit(Circle circle) {
        // TODO Request callback for the circle
        representation = String.format("<shape><type>circle</type><x>%d</x><y>%d</y></shape>", circle.getX(), circle.getY());
    }

    @Override
    public void visit(Square square) {
        // TODO Request callback for the square
        representation = String.format("<shape><type>square</type><x>%d</x><y>%d</y></shape>", square.getX(), square.getY());
    }

    @Override
    public void visit(Triangle triangle) {
        // TODO Request callback for the triangle
        representation = String.format("<shape><type>triangle</type><x>%d</x><y>%d</y></shape>", triangle.getX(), triangle.getY());
    }

    /**
     * @return the representation in JSon example for a Triangle:
     *
     *         <pre>
     * {@code
     *  <shape>
     *    <type>triangle</type>
     *    <x>-25</x>
     *    <y>-25</y>
     *  </shape>
     * }
     * </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}
