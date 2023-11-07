package edu.uga.miage.m1.polygons.gui.shapes;

public class ShapeFactory {
    private static ShapeFactory singletonShapeFactory;

    private ShapeFactory() {

    }

    public static ShapeFactory getInstance() {
        if (singletonShapeFactory == null) {
            singletonShapeFactory = new ShapeFactory();
        }
        return singletonShapeFactory;
    }

    public SimpleShape createShape(String shapeType, int x, int y) {
        SimpleShape shape = null;
        switch (shapeType) {
            case "circle":
                shape = new Circle(x, y);
                break;

            case "square":
                shape = new Square(x, y);
                break;

            case "triangle":
                shape = new Triangle(x, y);
                break;

            default:
                break;
        }
        return shape;
    }  
}
