import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

class ShapeFactoryTest {
    @Test
    void testCreateJsonCircle(){
        Visitor visitor = JSonVisitor.getInstance();
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        Circle simpleShape = (Circle) shapeFactory.createShape("circle", 125, 50);

        String result = simpleShape.accept(visitor);

        assertEquals("{\"type\":\"circle\",\"x\":100,\"y\":25}", result);
    }

    @Test
    void testCreateJsonSquare(){
        Visitor visitor = JSonVisitor.getInstance();
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        Square square = (Square) shapeFactory.createShape("square", 125, 50);

        String result = square.accept(visitor);

        assertEquals("{\"type\":\"square\",\"x\":100,\"y\":25}", result);
    }

    @Test
    void testCreateJsonTriangle(){
        Visitor visitor = JSonVisitor.getInstance();
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        Triangle triangle = (Triangle) shapeFactory.createShape("triangle", 125, 50);

        String result = triangle.accept(visitor);

        assertEquals("{\"type\":\"triangle\",\"x\":100,\"y\":25}", result);
    }

    @Test
    void testCreateXmlCircle(){
        Visitor visitor = XMLVisitor.getInstance();
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        Circle simpleShape = (Circle) shapeFactory.createShape("circle", 125, 50);

        String result = simpleShape.accept(visitor);

        assertEquals("<shape><type>circle</type><x>100</x><y>25</y></shape>", result);

    }

    @Test
    void testCreateXmlSquare(){
        Visitor visitor = XMLVisitor.getInstance();
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        Square square = (Square) shapeFactory.createShape("square", 125, 50);

        String result = square.accept(visitor);

        assertEquals("<shape><type>square</type><x>100</x><y>25</y></shape>", result);
    }

    @Test
    void testCreateXmlTriangle(){
        Visitor visitor = XMLVisitor.getInstance();
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        Triangle triangle = (Triangle) shapeFactory.createShape("triangle", 125, 50);

        String result = triangle.accept(visitor);

        assertEquals("<shape><type>triangle</type><x>100</x><y>25</y></shape>", result);
    }

}