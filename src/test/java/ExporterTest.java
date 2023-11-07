import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import edu.uga.miage.m1.polygons.gui.exporters.Exporter;
import edu.uga.miage.m1.polygons.gui.exporters.JsonShapes;
import edu.uga.miage.m1.polygons.gui.exporters.XmlShapes;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;

class ExporterTest {
    @Test
    // @Disabled
    void testGetEmptyJsonShapesAsString() {
        Exporter exporter = Exporter.getInstance();
        JsonShapes jsonShapes = new JsonShapes();

        String jsonShapesAsString = exporter.getJsonShapesAsString(jsonShapes);

        assertEquals("{\"shapes\":[]}", jsonShapesAsString);
    }

    @Test
    // @Disabled
    void testGetJsonShapesAsString() {
        Exporter exporter = Exporter.getInstance();
        JsonShapes jsonShapes = new JsonShapes();

        Circle circle = new Circle(25, 25);
        Visitor visitor = JSonVisitor.getInstance();
        jsonShapes.pushToShapeList(circle.accept(visitor));

        String jsonShapesAsString = exporter.getJsonShapesAsString(jsonShapes);

        assertEquals("{\"shapes\":[{\"type\":\"circle\",\"x\":0,\"y\":0}]}", jsonShapesAsString);
    }

    @Test
    // @Disabled
    void testGetEmptyXmlShapesAsString() {
        Exporter exporter = Exporter.getInstance();
        XmlShapes xmlShapes = new XmlShapes();

        String xmlShapesAsString = exporter.getXmlShapesAsString(xmlShapes);

        assertEquals("<?xml version='1.0' encoding='UTF-8'?><root><shapes/></root>", xmlShapesAsString);
    }

    @Test
    // @Disabled
    void testGetXmlShapesAsString() {
        Exporter exporter = Exporter.getInstance();
        XmlShapes xmlShapes = new XmlShapes();

        Circle circle = new Circle(25, 25);
        Visitor visitor = XMLVisitor.getInstance();
        xmlShapes.pushToShapeList(circle.accept(visitor));

        String xmlShapesAsString = exporter.getXmlShapesAsString(xmlShapes);

        assertEquals("<?xml version='1.0' encoding='UTF-8'?><root><shapes><shape><type>circle</type><x>0</x><y>0</y></shape></shapes></root>",
                xmlShapesAsString);
    }

    @Test
    // @Disabled
    void testWriteEmptyJsonShapes() {
        Exporter exporter = Exporter.getInstance();
        JsonShapes jsonShapes = new JsonShapes();

        String jsonShapesAsString = exporter.writeJsonShapes(jsonShapes);

        assertEquals("{\"shapes\":[]}", jsonShapesAsString);
    }

    @Test
    // @Disabled
    void testWriteJsonShapes() {
        Exporter exporter = Exporter.getInstance();
        JsonShapes jsonShapes = new JsonShapes();

        Circle circle = new Circle(25, 25);
        Visitor visitor = JSonVisitor.getInstance();
        jsonShapes.pushToShapeList(circle.accept(visitor));

        String jsonShapesAsString = exporter.writeJsonShapes(jsonShapes);

        assertEquals("{\"shapes\":[{\"type\":\"circle\",\"x\":0,\"y\":0}]}", jsonShapesAsString);
    }

    @Test
    // @Disabled
    void testWriteEmptyXmlShapes() {
        Exporter exporter = Exporter.getInstance();
        XmlShapes xmlShapes = new XmlShapes();

        String xmlShapesAsString = exporter.writeXmlShapes(xmlShapes);

        assertEquals("<?xml version='1.0' encoding='UTF-8'?><root><shapes/></root>", xmlShapesAsString);
    }

    @Test
    // @Disabled
    void testWriteXmlShapes() {
        Exporter exporter = Exporter.getInstance();
        XmlShapes xmlShapes = new XmlShapes();

        Circle circle = new Circle(25, 25);
        Visitor visitor = XMLVisitor.getInstance();
        xmlShapes.pushToShapeList(circle.accept(visitor));

        String xmlShapesAsString = exporter.writeXmlShapes(xmlShapes);

        assertEquals(
                "<?xml version='1.0' encoding='UTF-8'?><root><shapes><shape><type>circle</type><x>0</x><y>0</y></shape></shapes></root>",
                xmlShapesAsString);
    }

}
