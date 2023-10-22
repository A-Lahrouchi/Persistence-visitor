package edu.uga.miage.m1.polygons.gui.persistence;

/**
 * If you accept to be visited, implement this Interface!
 * 
 * @author <a href=
 *         "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public interface Visitable {

    /**
     * Accept a visitor.
     * @param visitor
     * @return a string in JSON or XML
     */
    String accept(Visitor visitor);
}