package edu.uga.miage.m1.polygons.gui.persistence;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {

    private String representation = null;

    public XMLVisitor() {
    }

    @Override
    public void visit(Circle circle) {
    // TODO Request callback for the circle
        
    }

    @Override
    public void visit(Square square) {
    // TODO Request callback for the square
    }

    @Override
    public void visit(Triangle triangle) {
    // TODO Request callback for the triangle
    }

 /*    public Document createXmlDocument(){
        Document representationDocument;
        try {
            DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            representationDocument=documentBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            // TODO: handle exception
            System.out.println(e.getStackTrace().toString
        }

        return representationDocument;

    } */

    /**
     * @return the representation in JSon example for a Triangle:
     *
     *         <pre>
     * {@code
     *  <shape>
     *    <type>triangle</type>
     *    <x>-25</x>
     *    <y>-25</y>
     *  </shape>
     * }
     * </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}
