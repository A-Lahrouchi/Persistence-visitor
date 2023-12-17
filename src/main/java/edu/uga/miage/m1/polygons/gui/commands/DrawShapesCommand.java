package edu.uga.miage.m1.polygons.gui.commands;

import com.fasterxml.jackson.databind.JsonNode;

import java.awt.Graphics2D;

import javax.swing.JPanel;

import edu.uga.miage.m1.polygons.gui.listofshapes.JsonShapes;
import edu.uga.miage.m1.polygons.gui.listofshapes.XmlShapes;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

public class DrawShapesCommand implements Command {

    private String shapeType;
    private int x;
    private int y;

    private JPanel panel;
    private ShapeFactory shapeFactory;
    private JSonVisitor jsonVisitor;
    private XMLVisitor xmlVisitor;
    private JsonShapes jsonShapes;
    private XmlShapes xmlShapes;

    public DrawShapesCommand(JPanel panel, String shape, int x, int y, JsonShapes js, XmlShapes xs) {
        this.shapeType = shape;
        this.x = x;
        this.y = y;

        this.panel = panel;
        this.shapeFactory = ShapeFactory.getInstance();
        this.jsonVisitor = JSonVisitor.getInstance();
        this.xmlVisitor = XMLVisitor.getInstance();
        this.jsonShapes = js;
        this.xmlShapes = xs;
    }

    @Override
    public void execute() {
        switch (shapeType) {
            case "circle":
                Circle c = (Circle) shapeFactory.createShape(shapeType, x, y);
                jsonShapes.pushToShapeList(c.accept(jsonVisitor));
                xmlShapes.pushToShapeList(c.accept(xmlVisitor));
                break;
            case "square":
                Square s = (Square) shapeFactory.createShape(shapeType, x, y);
                jsonShapes.pushToShapeList(s.accept(jsonVisitor));
                xmlShapes.pushToShapeList(s.accept(xmlVisitor));
                break;
            case "triangle":
                Triangle t = (Triangle) shapeFactory.createShape(shapeType, x, y);
                jsonShapes.pushToShapeList(t.accept(jsonVisitor));
                xmlShapes.pushToShapeList(t.accept(xmlVisitor));
                break;
            default:
                break;
        }
        drawAllShapes();
    }

    @Override
    public void undo() {
        jsonShapes.popFromShapeList();
        xmlShapes.popFromShapeList();
        drawAllShapes();
    }

    @Override
    public boolean isUndoable() {
        return true;
    }

    private void drawAllShapes() {
        if (panel != null) {
            Graphics2D g2 = (Graphics2D) panel.getGraphics();
            g2.setColor(g2.getBackground());
            g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());

            for (JsonNode shape : jsonShapes.getShapeList()) {
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
