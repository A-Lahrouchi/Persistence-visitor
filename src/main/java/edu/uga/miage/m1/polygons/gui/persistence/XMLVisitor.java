package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href=
 *         "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {

    @Override
    public String visit(Circle circle) {
        return getXmlShape("circle", circle.getX(), circle.getY());
    }

    @Override
    public String visit(Square square) {
        return getXmlShape("square", square.getX(), square.getY());
    }

    @Override
    public String visit(Triangle triangle) {
        return getXmlShape("triangle", triangle.getX(), triangle.getY());
    }

    /**
     * @return the representation in JSon example for a Triangle:
     *         {@code
     *  <shape>
     *    <type>triangle</type>
     *    <x>-25</x>
     *    <y>-25</y>
     *  </shape>
     * }
     */
    public String getXmlShape(String shapeType, int x, int y) {
        return String.format("<shape><type>%s</type><x>%d</x><y>%d</y></shape>", shapeType, x, y);
    }
}
