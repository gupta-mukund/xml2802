import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseDocument {

    private List<Pianta> piante;
    private String filename;

    public ParseDocument(String filename) {
        piante = new ArrayList<Pianta>();
        this.filename = filename;
    }

    public void parse() {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element;
        NodeList nodelist;

        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(filename);
            root = document.getDocumentElement();
            nodelist = root.getElementsByTagName("PLANT");
            if (nodelist != null && nodelist.getLength() > 0) {
                for (int i = 0; i < nodelist.getLength(); i++) {
                    element = (Element) nodelist.item(i);
                    piante.add(createPianta(element));
                }
            }
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

    }

    public Pianta createPianta(Element element) {
        Pianta pianta;
        String common = getText(element, "COMMON");
        String botanical = getText(element, "BOTANICAL");
        String zone = getText(element, "ZONE");
        String light = getText(element, "LIGHT");
        String price = getText(element, "PRICE");
        String currency = getAttribute(element, "PRICE", "CURRENCY");
        pianta = new Pianta(common, botanical, Integer.parseInt(zone), light, Double.parseDouble(price), currency);
        return pianta;
    }

    public String getText(Element element, String tag) {
        String value = null;
        NodeList nodelist = element.getElementsByTagName(tag);

        if (nodelist != null && nodelist.getLength() > 0) {
            value = nodelist.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }

    public String getAttribute(Element element, String tag, String attribute) {
        String value = null;
        NodeList nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > 0) {
            value = nodelist.item(0).getAttributes().getNamedItem(attribute).getNodeValue();
        }
        return value;
    }

    public void print() {
        for (int i = 0; i < piante.size(); i++) {
            Pianta.printPianta(piante.get(i));
        }
    }

    public static void main(String[] args) {
        ParseDocument parser = new ParseDocument("botanica.xml");
        parser.parse();
        parser.print();
    }

}