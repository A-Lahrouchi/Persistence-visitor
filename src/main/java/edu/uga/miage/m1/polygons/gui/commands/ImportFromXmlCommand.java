package edu.uga.miage.m1.polygons.gui.commands;

import java.awt.Graphics2D;
import javax.swing.JPanel;

import com.fasterxml.jackson.databind.JsonNode;

import edu.uga.miage.m1.polygons.gui.io.Importer;
import edu.uga.miage.m1.polygons.gui.io.XmlImporter;
import edu.uga.miage.m1.polygons.gui.listofshapes.XmlShapes;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

public class ImportFromXmlCommand implements Command {

    private Importer importer;
    private XmlShapes xmlShapes;
    private XmlShapes xmlShapesBackup;

    private JPanel panel;
    private ShapeFactory shapeFactory;

    public ImportFromXmlCommand(JPanel panel, XmlShapes xs) {
        this.panel = panel;
        this.shapeFactory = ShapeFactory.getInstance();
        this.importer = XmlImporter.getInstance();
        this.xmlShapes = xs;
        this.xmlShapesBackup = xs;

    }

    public XmlShapes getShapes() {
        return xmlShapes;
    }

    @Override
    public void execute() {
        xmlShapes = (XmlShapes) importer.readShapes();
        drawAllShapes(xmlShapes);
    }

    @Override
    public void undo() {
        xmlShapes = xmlShapesBackup;
        drawAllShapes(xmlShapesBackup);
    }

    @Override
    public boolean isUndoable() {
        return true; // Export is not undoable
    }

    private void drawAllShapes(XmlShapes xs) {
        if (panel != null) {
            Graphics2D g2 = (Graphics2D) panel.getGraphics();
            g2.setColor(g2.getBackground());
            g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());

            for (JsonNode shape : xs.getShapeList()) {
                String shapeType = shape.get("type").textValue();
                int x = shape.get("x").intValue() + 25;
                int y = shape.get("y").intValue() + 25;

                switch (shapeType) {
                    case "circle":
                        Circle c = (Circle) shapeFactory.createShape(shapeType, x, y);
                        c.draw(g2);
                        break;
                    case "square":
                        Square s = (Square) shapeFactory.createShape(shapeType, x, y);
                        s.draw(g2);
                        break;
                    case "triangle":
                        Triangle t = (Triangle) shapeFactory.createShape(shapeType, x, y);
                        t.draw(g2);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
