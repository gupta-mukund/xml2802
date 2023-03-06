import java.io.File;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidateDocument {
    public static void validate(String XMLDocument, String XSDschema) throws SAXException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaFile = new File(XSDschema);
        Schema schema;

        schema = factory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        Source source = new StreamSource(XMLDocument);
        validator.validate(source);

    }

    public static void main(String[] args) {
        try {
            ValidateDocument.validate("botanica.xml", "botanica.xsd");
            System.out.println("Valido");
        } catch (SAXException | IOException e) {
            System.out.println("Non valido");
            e.printStackTrace();
        }
    }
}
