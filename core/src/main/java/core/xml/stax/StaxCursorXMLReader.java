package core.xml.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import core.xml.dom.Employee;

/**
 * http://tutorials.jenkov.com/java-xml/sax-vs-stax.html
 *
 */
public class StaxCursorXMLReader {

    public static void main(String...args) {
        String fileName = "employee.xml";
        List<Employee> empList = parseXML(fileName);
        for (Employee emp : empList) {
            System.out.println(emp.toString());
        }
    }

    private static List<Employee> parseXML(String fileName) {
        List<Employee> empList = new ArrayList<>();
        Employee emp = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();

                    // set the other varibles from xml elements
                    if (startElement.getName().getLocalPart().equals("name")) {
                        emp = new Employee();
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("age")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setAge(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("role")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setRole(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("gender")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setGender(xmlEvent.asCharacters().getData());
                    }
                }
                // if Employee end element is reached, add employee object to
                // list
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Employee")) {
                        empList.add(emp);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return empList;
    }

}