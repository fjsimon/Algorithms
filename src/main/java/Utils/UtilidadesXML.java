package Utils;
import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class UtilidadesXML {

    public static Document File2Document(String fichero) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//      ClassLoader loader=(UtilidadesXML.class).getClassLoader();
//      URL urlfichero=loader.getResource(fichero);
        URL urlfichero = new File(fichero).toURI().toURL();
        Document XMLDoc = factory.newDocumentBuilder().parse(new InputSource(urlfichero.openStream()));
        return XMLDoc;
    }
    
    public static String Node2String (Node nodo) throws Exception {
        StringWriter sw = new StringWriter();        
        StreamResult sR=new StreamResult(sw);                
        Transformer transformer =      TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(nodo),sR);        
        return sw.toString();        
    }
    
    public static String Nodes2String (NodeList nodes) throws Exception {
        StringBuffer lista = new StringBuffer(); 
        
        for(int i=0;i<nodes.getLength();i++) {
            Node node = (Node)nodes.item(i);
            lista.append(UtilidadesXML.Node2String(node));            
        }
        
        return lista.toString();
    }

    public static boolean esNodo(Node nodo, String nombre) {
        return (esNodo(nodo) && (nodo.getNodeName().equalsIgnoreCase(nombre)));
    }
    
    public static boolean esNodo(Node nodo) {
        return (nodo.getNodeType() == Node.ELEMENT_NODE);
    }
    
    public static boolean esTexto(Node nodo) {
        return (nodo.getNodeType() == Node.TEXT_NODE);
    }
    
    public static String getTexto (Node n) {
        NodeList nl = n.getChildNodes();
        Node act=null;
        for (int i=0; i < nl.getLength(); i++) {
            act = nl.item(i);
            if (act == null) return null;
            if ((act.getNodeType() == Node.CDATA_SECTION_NODE)
            		|| (act.getNodeType() == Node.TEXT_NODE)) 
            	return act.getNodeValue();
        }
        return "";
      }
    
    public static String dameAtributoNodo(String nombre, Node nodo) {
        NamedNodeMap mapa = nodo.getAttributes();
        String valor = null;
        if(mapa!=null) {
            Node nodoAt = mapa.getNamedItem(nombre);
            if(nodoAt!=null)
                valor = nodoAt.getNodeValue();
        }
        return valor;
    }
    
}
