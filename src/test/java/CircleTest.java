import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;

class CircleTest {

    @Test
    void acceptShouldReturnAJsonCircle() {
        String jsonCircle = "";
        Circle circle = new Circle(125, 50);
        Visitor visitor = new JSonVisitor();

        jsonCircle = circle.accept(visitor);

        assertEquals("{\"type\":\"circle\",\"x\":100,\"y\":25}", jsonCircle);
    }

    @Test
    void acceptShouldReturnAnXMLCircle() {
        String xmlCircle = "";
        Circle circle = new Circle(25, 25);
        Visitor visitor = new XMLVisitor();

        xmlCircle = circle.accept(visitor);

        assertEquals("<shape><type>circle</type><x>0</x><y>0</y></shape>", xmlCircle);
    }
}
