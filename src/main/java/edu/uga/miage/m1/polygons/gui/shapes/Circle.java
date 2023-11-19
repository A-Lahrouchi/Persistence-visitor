/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

public class Circle implements SimpleShape, Visitable {

    int x;

    int y;

    Circle previousCircle;

    public Circle(int x, int y) {
        this.x = x - 25;
        this.y = y - 25;
    }

    public Circle(int x, int y, Circle previousCircle){
        this.x = x - 25;
        this.y = y - 25;
        this.previousCircle=previousCircle;
    }

    /**
     * Implements the <tt>SimpleShape.draw()</tt> method for painting
     * the shape.
     * 
     * @param g2 The graphics object used for painting.
     */
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.RED, x + 50f, y, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(new Ellipse2D.Double(x, y, 50, 50));
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.black);
        g2.setStroke(wideStroke);
        g2.draw(new Ellipse2D.Double(x, y, 50, 50));
    }

    public void highlight(Graphics2D g2, Boolean highlight){
        if (highlight){
            g2.setColor(Color.YELLOW);
        }else{
            g2.setColor(Color.BLACK);
        }
        g2.draw(new Ellipse2D.Double(x, y, 50, 50));   
    }

    public void drawEmptyCircle(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.fill(new Ellipse2D.Double(x, y, 50, 50));
        g2.setColor(Color.WHITE);
        g2.draw(new Ellipse2D.Double(x, y, 50, 50));
    }

    public Area getArea(){
        return new Area(new Ellipse2D.Double(x, y, 50, 50));
    }

    public boolean overlaps(SimpleShape shape){
        Area theArea = getArea();
        theArea.intersect(shape.getArea());
        return !theArea.isEmpty();
    }

    public ArrayList<SimpleShape> overlappingShapes(ArrayList<SimpleShape> allShapes){
        ArrayList<SimpleShape> overlappingShapes = new ArrayList<>();

        for (SimpleShape shape:allShapes){
            if (this.overlaps(shape) && (shape.getX()!= this.getX() && shape.getY()!= this.getY()))
                overlappingShapes.add(shape);
        }

        return overlappingShapes;
    }

    public void erase(Graphics2D g2, ArrayList<SimpleShape> allShapes){
        allShapes.remove(this);
        ArrayList<SimpleShape> overlappingShapes = overlappingShapes(allShapes);
        drawEmptyCircle(g2);
        for(SimpleShape shape: overlappingShapes){
            shape.draw(g2);
        }
    }

    public void undo(Graphics2D g2, ArrayList<SimpleShape> allShapes){
        if(this.previousCircle != null){
            this.previousCircle.draw(g2);
            allShapes.add(previousCircle);
        }
        erase(g2, allShapes);
    }

    public void move (Graphics2D g2,  ArrayList<SimpleShape> allShapes, int x, int y){
        erase(g2, allShapes);
        Circle newCircle = new Circle(x, y, this);
        newCircle.draw(g2);
        allShapes.add(newCircle);
    }


    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
