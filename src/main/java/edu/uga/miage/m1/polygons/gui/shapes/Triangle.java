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

import static java.awt.geom.Path2D.WIND_EVEN_ODD;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

/**
 * This inner class implements the triangle <tt>SimpleShape</tt> service.
 * It simply provides a <tt>draw()</tt> that paints a triangle.
 *
 * @author <a href=
 *         "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class Triangle implements SimpleShape, Visitable {

    int x;

    int y;

    public Triangle(int x, int y) {
        this.x = x - 25;
        this.y = y - 25;
    }

    private GeneralPath getPolygon(){
        int[] xcoords = { x + 25, x, x + 50 };
        int[] ycoords = { y, y + 50, y + 50 };
        GeneralPath polygon = new GeneralPath(WIND_EVEN_ODD, xcoords.length);
        polygon.moveTo(x + 25f, y);
        for (int i = 0; i < xcoords.length; i++) {
            polygon.lineTo(xcoords[i], ycoords[i]);
        }
        polygon.closePath();
        return polygon;
    } 

    /**
     * Implements the <tt>SimpleShape.draw()</tt> method for painting
     * the shape.
     * 
     * @param g2 The graphics object used for painting.
     */
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.GREEN, x + 50f, y, Color.WHITE);
        g2.setPaint(gradient);
        GeneralPath polygon = getPolygon();
        g2.fill(polygon);
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.black);
        g2.setStroke(wideStroke);
        g2.draw(polygon);
    }

    public void drawEmptyTriangle(Graphics2D g2){
        GeneralPath polygon = getPolygon();
        g2.setColor(Color.WHITE);
        g2.fill(polygon);
        g2.setColor(Color.WHITE);
        g2.draw(polygon);
    }

    public void highlight(Graphics2D g2, Boolean highlight){
        GeneralPath polygon = getPolygon();
        if (highlight){
            g2.setColor(Color.YELLOW);
        }else{
            g2.setColor(Color.BLACK);
        }        g2.draw(polygon);
    }

    public Area getArea(){
        GeneralPath polygon = getPolygon();
        return new Area(polygon);
    }

    public Boolean overlaps(SimpleShape shape){
        Area theArea = getArea();
        theArea.intersect(shape.getArea());
        return !theArea.isEmpty();
    }

    public ArrayList<SimpleShape> overlapingShapes(ArrayList<SimpleShape> allShapes){
        ArrayList<SimpleShape> overlapingShapes = new ArrayList<>();
        for(SimpleShape shape:allShapes){
            if(overlaps(shape)){
                overlapingShapes.add(shape);
            }
        }
        return overlapingShapes;
    }
 
    public void erase(Graphics2D g2, ArrayList<SimpleShape> allShapes ){
        allShapes.remove(this);
        ArrayList<SimpleShape> overlapingShapes = overlapingShapes(allShapes);
        drawEmptyTriangle(g2);
        for(SimpleShape shape : overlapingShapes){
            shape.draw(g2);
        }
    }

    public void move (Graphics2D g2, ArrayList<SimpleShape> allShapes, int x, int y){
        erase(g2, allShapes);
        this.setX(x);
        this.setY(y);
        draw(g2);
        allShapes.add(this);
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    private void setX(int x){
        this.x=x-25;
    }

    private void setY(int y){
        this.y=y-25;
    }
}
