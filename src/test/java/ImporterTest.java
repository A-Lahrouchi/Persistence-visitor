import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.io.Exporter;
import edu.uga.miage.m1.polygons.gui.io.Importer;
import edu.uga.miage.m1.polygons.gui.io.JsonExporter;
import edu.uga.miage.m1.polygons.gui.io.JsonImporter;
import edu.uga.miage.m1.polygons.gui.io.XmlExporter;
import edu.uga.miage.m1.polygons.gui.io.XmlImporter;
import edu.uga.miage.m1.polygons.gui.listofshapes.JsonShapes;
import edu.uga.miage.m1.polygons.gui.listofshapes.XmlShapes;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;

class ImporterTest {

    @Test
    void testReadJsonShapes() {
        Importer importer = JsonImporter.getInstance();
        Exporter exporter = JsonExporter.getInstance();
        JsonShapes jsonShapes = new JsonShapes();
        Circle circle = new Circle(25, 25);
        Visitor visitor = JSonVisitor.getInstance();

        jsonShapes.pushToShapeList(circle.accept(visitor));
        exporter.writeShapes(jsonShapes);

        jsonShapes = (JsonShapes) importer.readShapes();
        String jsonShapesAsString = exporter.getShapesAsString(jsonShapes);

        assertEquals("{\"shapes\":[{\"type\":\"circle\",\"x\":0,\"y\":0}]}", jsonShapesAsString);
    }

    @Test
    void testReadXmlShapes() {
        Importer importer = XmlImporter.getInstance();
        Exporter exporter = XmlExporter.getInstance();
        XmlShapes xmlShapes = new XmlShapes();
        Circle circle = new Circle(25, 25);
        Visitor visitor = XMLVisitor.getInstance();

        xmlShapes.pushToShapeList(circle.accept(visitor));
        exporter.writeShapes(xmlShapes);

        xmlShapes = (XmlShapes) importer.readShapes();
        String xmlShapesAsString = exporter.getShapesAsString(xmlShapes);

        assertEquals(
                "<?xml version='1.0' encoding='UTF-8'?><root><shapes><shape><type>circle</type><x>0</x><y>0</y></shape></shapes></root>",
                xmlShapesAsString);
    }

}
