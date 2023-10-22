import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

class TriangleTest {

    @Test
    void acceptShouldReturnAJsonTriangle() {
        String jsonTriangle = "";
        Triangle triangle = new Triangle(125, 50);
        Visitor visitor = new JSonVisitor();

        jsonTriangle = triangle.accept(visitor);

        assertEquals("{\"type\":\"triangle\",\"x\":100,\"y\":25}", jsonTriangle);
    }

    @Test
    void acceptShouldReturnAnXMLTriangle() {
        String xmlTriangle = "";
        Triangle triangle = new Triangle(25, 25);
        Visitor visitor = new XMLVisitor();

        xmlTriangle = triangle.accept(visitor);

        assertEquals("<shape><type>triangle</type><x>0</x><y>0</y></shape>", xmlTriangle);
    }
}
