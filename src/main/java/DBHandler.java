import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DBHandler handles the acces to database
 * 
 * @author koviroli
 *
 */
public class DBHandler {
	
	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
    Path folder = Paths.get(System.getProperty("user.home"), ".BOWMANGAME");
    Path usersxml = Paths.get(folder.toString(), "users.xml");
	
    File inputfile;
    
	TransformerFactory transformerFactory;
	Transformer transformer;
	
    /**
     * Constructor makes hidden directory and xml files to database,
     * if those not exists.
     */
	public DBHandler() {
		
		try {
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			
	    	
			
		} catch ( ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		createDBDir();
		createDBXML();
  
        
	}
	
	public boolean newRegistry(User user){
		try {
			
			if(checkUser(user))
				return false;
			
			inputfile = new File(Paths.get(usersxml.toString()).toString());
			Document doc = dBuilder.parse(inputfile);
			
			
        	Element rootElement = doc.getDocumentElement();
       	 	//user element
	        Element userElement = doc.createElement("user");
	        rootElement.appendChild(userElement);
	            
	        //setting attribute to element
	        Attr attr = doc.createAttribute("id");
	        attr.setValue(Integer.toString(user.getUserId()));
	        userElement.setAttributeNode(attr);    
	        
	        Element username = doc.createElement("username");
	        username.appendChild(doc.createTextNode(user.getUsername()));
	        userElement.appendChild(username);
	            
	        Element password = doc.createElement("password");
	        password.appendChild(doc.createTextNode(user.getUserpassword()));
	        userElement.appendChild(password);
	        
	        Element email = doc.createElement("email");
	        email.appendChild(doc.createTextNode(user.getEmailaddress()));
	        userElement.appendChild(email);
	            
	        Element registrationDate = doc.createElement("registrationDate");
	        registrationDate.appendChild(doc.createTextNode(user.getRegistrationDate()));
	        userElement.appendChild(registrationDate);           

	        
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        DOMSource source = new DOMSource(doc);        
	        
	        //StreamResult result = new StreamResult(new File("users.xml"));
	        StreamResult result = new StreamResult(usersxml.toFile());
	        transformer.transform(source, result);
			
			
			
		} catch (SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean DBLogin(String user, String pw){
		if (checkUserPassword(user, pw)){
			return true;
		}
		return false;
	}
	
	public boolean checkUser(User user){
		try{
			inputfile = new File(Paths.get(usersxml.toString()).toString());
			Document doc = dBuilder.parse(inputfile);
			
			Element docEle = doc.getDocumentElement();
	    	NodeList nl = docEle.getChildNodes();
	    	
	    	if (nl != null && nl.getLength() > 0) {
	            for (int i = 0; i < nl.getLength(); i++) {
	                if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                    Element el = (Element) nl.item(i);
	                    if (el.getNodeName().contains("user")) {
	                        String name = el.getElementsByTagName("username").item(0).getTextContent();
	                        String email = el.getElementsByTagName("email").item(0).getTextContent();
	                        
	                        if(user.getUsername().equals(name) || user.getEmailaddress().equals(email)){
	                        	System.out.println("user already exists!");
	                        	return true;
	                        }
	                    }
	                }
	            }
	        }
		}catch (SAXException | IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    	return false;
	}
	
	public boolean checkUserPassword(String user, String userpw){
		try{
			inputfile = new File(Paths.get(usersxml.toString()).toString());
			Document doc = dBuilder.parse(inputfile);
			
			Element docEle = doc.getDocumentElement();
	    	NodeList nl = docEle.getChildNodes();
	    	
	    	if (nl != null && nl.getLength() > 0) {
	            for (int i = 0; i < nl.getLength(); i++) {
	                if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                    Element el = (Element) nl.item(i);
	                    if (el.getNodeName().contains("user")) {
	                        String username = el.getElementsByTagName("username").item(0).getTextContent();
	                        String password = el.getElementsByTagName("password").item(0).getTextContent();
	                        
	                        if(user.equals(username) && userpw.equals(password)){
	                        	System.out.println("user Exists");
	                        	return true;
	                        }
	                    }
	                }
	            }
	        }
		}catch (SAXException | IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    	return false;
	}
	
	
	public boolean createDBDir(){
		if(!Files.exists(folder)){ 
			folder.toFile().mkdirs();
			return true;
		}
     	return false;
	}
	
	public boolean createDBXML(){
		try {
			if(!Files.exists(usersxml)){
				usersxml.toFile().createNewFile();
				
				Document doc = dBuilder.newDocument();
		    	Element rootElement = doc.createElement("users");
		        doc.appendChild(rootElement);
				
		        transformerFactory = TransformerFactory.newInstance();
				transformer = transformerFactory.newTransformer();
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        DOMSource source = new DOMSource(doc);
		        
		        StreamResult result = new StreamResult(usersxml.toFile());
		        transformer.transform(source, result);
				
				return true;
			}
		} catch (IOException  | TransformerException e) {
			e.printStackTrace();
		}
		return false;
	}
}
