import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Square;

class SquareTest {

    @Test
    void acceptShouldReturnAJsonSquare() {
        String jsonSquare = "";
        Square square = new Square(125, 50);
        Visitor visitor = new JSonVisitor();

        jsonSquare = square.accept(visitor);

        assertEquals("{\"type\":\"square\",\"x\":100,\"y\":25}", jsonSquare);
    }

    @Test
    void acceptShouldReturnAnXMLSquare() {
        String xmlSquare = "";
        Square square = new Square(25, 25);
        Visitor visitor = new XMLVisitor();

        xmlSquare = square.accept(visitor);

        assertEquals("<shape><type>square</type><x>0</x><y>0</y></shape>", xmlSquare);
    }
}
