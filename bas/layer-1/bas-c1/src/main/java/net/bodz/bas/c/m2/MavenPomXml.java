package net.bodz.bas.c.m2;

import java.io.File;
import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.IllegalUsageException;

public class MavenPomXml {

    static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    static XPathFactory xPathfactory = XPathFactory.newInstance();

    Document doc;

    public MavenPomXml(Document doc) {
        this.doc = doc;
    }

    public static MavenPomXml open(File file)
            throws SAXException, IOException {
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new IllegalUsageException(e.getMessage(), e);
        }
        Document doc = builder.parse(file);
        return new MavenPomXml(doc);
    }

    public Object select(String xpathExpr, QName type)
            throws XPathExpressionException {
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile(xpathExpr);
        return expr.evaluate(doc, type);
    }

    public String select(String xpath)
            throws XPathExpressionException {
        String result = (String) select(xpath, XPathConstants.STRING);
        return result;
    }

    public <node_t extends Node> node_t select(String xpath, int index)
            throws XPathExpressionException {
        NodeList nodes = selectNodes(xpath);
        return (node_t) nodes.item(index);
    }

    public NodeList selectNodes(String xpath)
            throws XPathExpressionException {
        NodeList nodes = (NodeList) select(xpath, XPathConstants.NODESET);
        return nodes;
    }

    public ArtifactId getId()
            throws XPathExpressionException, SAXException, IOException {
        String groupId = this.select("/project/groupId");
        if (Nullables.isEmpty(groupId))
            groupId = this.select("/project/parent/groupId");

        String artifactId = this.select("/project/artifactId");

        String version = this.select("/project/version");
        if (Nullables.isEmpty(version))
            version = this.select("/project/parent/version");

        String packaging = this.select("/project/packaging");
        if (Nullables.isEmpty(packaging))
            packaging = "jar";

        ArtifactId id = new ArtifactId(groupId, artifactId, packaging, version);
        return id;
    }

}
