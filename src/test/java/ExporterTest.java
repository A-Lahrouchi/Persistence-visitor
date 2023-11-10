import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.uga.miage.m1.polygons.gui.exporters.Exporter;
import edu.uga.miage.m1.polygons.gui.exporters.JsonExporter;
import edu.uga.miage.m1.polygons.gui.exporters.XmlExporter;
import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.JsonShapes;
import edu.uga.miage.m1.polygons.gui.exporters.exportFormats.XmlShapes;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;

class ExporterTest {

    @Test
    void testReadEmptyJsonShapes() {
        Exporter exporter = JsonExporter.getInstance();
        JsonShapes jsonShapes = (JsonShapes) exporter.readShapes();

        exporter.writeShapes(jsonShapes);
        String jsonShapesAsString = exporter.getShapesAsString(jsonShapes);

        assertEquals("{\"shapes\":[]}", jsonShapesAsString);
    }

    @Test
    void testReadEmptyXmlShapes() {
        Exporter exporter = XmlExporter.getInstance();
        XmlShapes xmlShapes = (XmlShapes) exporter.readShapes();

        exporter.writeShapes(xmlShapes);
        String xmlShapesAsString = exporter.getShapesAsString(xmlShapes);

        assertEquals("<?xml version='1.0' encoding='UTF-8'?><root><shapes/></root>", xmlShapesAsString);
    }

    @Test
    void testReadJsonShapes() {
        Exporter exporter = JsonExporter.getInstance();
        JsonShapes jsonShapes = new JsonShapes();
        Circle circle = new Circle(25, 25);
        Visitor visitor = JSonVisitor.getInstance();

        jsonShapes.pushToShapeList(circle.accept(visitor));
        exporter.writeShapes(jsonShapes);

        jsonShapes = (JsonShapes) exporter.readShapes();
        String jsonShapesAsString = exporter.getShapesAsString(jsonShapes);

        assertEquals("{\"shapes\":[{\"type\":\"circle\",\"x\":0,\"y\":0}]}", jsonShapesAsString);
    }

    @Test
    void testReadXmlShapes() {
        Exporter exporter = XmlExporter.getInstance();
        XmlShapes xmlShapes = new XmlShapes();
        Circle circle = new Circle(25, 25);
        Visitor visitor = XMLVisitor.getInstance();

        xmlShapes.pushToShapeList(circle.accept(visitor));
        exporter.writeShapes(xmlShapes);

        xmlShapes = (XmlShapes) exporter.readShapes();
        String xmlShapesAsString = exporter.getShapesAsString(xmlShapes);

        assertEquals(
                "<?xml version='1.0' encoding='UTF-8'?><root><shapes><shape><type>circle</type><x>0</x><y>0</y></shape></shapes></root>",
                xmlShapesAsString);
    }

    @Test
    void testWriteEmptyJsonShapes() {
        Exporter exporter = JsonExporter.getInstance();
        JsonShapes jsonShapes = new JsonShapes();

        exporter.writeShapes(jsonShapes);
        String jsonShapesAsString = exporter.getShapesAsString(jsonShapes);

        assertEquals("{\"shapes\":[]}", jsonShapesAsString);
    }

    @Test
    void testWriteEmptyXmlShapes() {
        Exporter exporter = XmlExporter.getInstance();
        XmlShapes xmlShapes = new XmlShapes();

        exporter.writeShapes(xmlShapes);
        String xmlShapesAsString = exporter.getShapesAsString(xmlShapes);

        assertEquals("<?xml version='1.0' encoding='UTF-8'?><root><shapes/></root>", xmlShapesAsString);
    }

    @Test
    void testWriteJsonShapes() {
        Exporter exporter = JsonExporter.getInstance();
        JsonShapes jsonShapes = new JsonShapes();

        Circle circle = new Circle(25, 25);
        Visitor visitor = JSonVisitor.getInstance();
        jsonShapes.pushToShapeList(circle.accept(visitor));

        exporter.writeShapes(jsonShapes);
        String jsonShapesAsString = exporter.getShapesAsString(jsonShapes);

        assertEquals("{\"shapes\":[{\"type\":\"circle\",\"x\":0,\"y\":0}]}", jsonShapesAsString);
    }

    @Test
    void testWriteXmlShapes() {
        Exporter exporter = XmlExporter.getInstance();
        XmlShapes xmlShapes = new XmlShapes();

        Circle circle = new Circle(25, 25);
        Visitor visitor = XMLVisitor.getInstance();
        xmlShapes.pushToShapeList(circle.accept(visitor));

        exporter.writeShapes(xmlShapes);
        String xmlShapesAsString = exporter.getShapesAsString(xmlShapes);

        assertEquals(
                "<?xml version='1.0' encoding='UTF-8'?><root><shapes><shape><type>circle</type><x>0</x><y>0</y></shape></shapes></root>",
                xmlShapesAsString);
    }
}
