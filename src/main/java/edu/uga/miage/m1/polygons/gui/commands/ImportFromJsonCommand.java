package edu.uga.miage.m1.polygons.gui.commands;

import java.awt.Graphics2D;
import javax.swing.JPanel;

import com.fasterxml.jackson.databind.JsonNode;

import edu.uga.miage.m1.polygons.gui.io.Importer;
import edu.uga.miage.m1.polygons.gui.io.JsonImporter;
import edu.uga.miage.m1.polygons.gui.listofshapes.JsonShapes;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

public class ImportFromJsonCommand implements Command {

    private Importer importer;
    private JsonShapes jsonShapes;
    private JsonShapes jsonShapesBackup;

    private JPanel panel;
    private ShapeFactory shapeFactory;

    public ImportFromJsonCommand(JPanel panel, JsonShapes js) {
        this.panel = panel;
        this.shapeFactory = ShapeFactory.getInstance();
        this.importer = JsonImporter.getInstance();
        this.jsonShapes = js;
        this.jsonShapesBackup = js;

    }

    public JsonShapes getShapes() {
        return jsonShapes;
    }

    @Override
    public void execute() {
        jsonShapes = (JsonShapes) importer.readShapes();
        drawAllShapes(jsonShapes);
    }

    @Override
    public void undo() {
        jsonShapes = jsonShapesBackup;
        drawAllShapes(jsonShapesBackup);
    }

    @Override
    public boolean isUndoable() {
        return true; // Export is not undoable
    }

    private void drawAllShapes(JsonShapes js) {
        if (panel != null) {
            Graphics2D g2 = (Graphics2D) panel.getGraphics();
            g2.setColor(g2.getBackground());
            g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());

            for (JsonNode shape : js.getShapeList()) {
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