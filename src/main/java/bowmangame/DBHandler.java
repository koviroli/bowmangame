package bowmangame;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DBHandler az adatbázishozzáférést kezeli.
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
	
	public static Logger logger = LoggerFactory.getLogger(DBHandler.class);
	
    /**
     * A konstruktor elkészíti a rejtett mappát a felhaszánáló home könyvtárában és az adatbázist tároló xml fájlt,
     * ha az nem létezik.
     */
	public DBHandler() {	
		try {
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
	    	
			
		} catch ( ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		createDBDir();
		createDBXML();       
	}
	
	/**
	 * Új fejlhasználó létrehozása
	 * @param user az user akiszerint létrehozzuk az új bejegyzést az adatbázisban
	 * @return igaz, ha sikerült létrehőzni, egyébként hamis
	 */
	public boolean newRegistry(User user){
		try {
			
			if(checkUser(user)){
				logger.error("sikertelen regisztráció");
				logger.error("már van ilyen felhasználó.");
				return false;
			}
			
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
	        
	        Element points = doc.createElement("points");
	        points.appendChild(doc.createTextNode("0"));
	        userElement.appendChild(points);
	        
	        Element level = doc.createElement("level");
	        level.appendChild(doc.createTextNode("1"));
	        userElement.appendChild(level);
	        
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
		logger.info("sikeres regisztráció.");
		return true;
	}
	
	public User DBLogin(String user, String pw){
		User uUser = checkUserPassword(user, pw);
		if (uUser != null){
			logger.info("sikeres bejelentkezes.");
			logger.info(uUser.getUsername());
			logger.info(uUser.getPassword());
			logger.info(uUser.getEmailaddress());
			logger.info(Integer.toString(uUser.getPoints()));
			logger.info(Integer.toString(uUser.getLevel()) );
			return uUser;
		}
		logger.error("nincs ilyen felhasznalo.");
		return null;
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
	                        	logger.error("már van ilyen felhasználó.");
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
	
	public User checkUserPassword(String user, String userpw){
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
	                        String email = el.getElementsByTagName("email").item(0).getTextContent();
	                        String points = el.getElementsByTagName("points").item(0).getTextContent();
	                        String level = el.getElementsByTagName("level").item(0).getTextContent();
	                        
	                        if(user.equals(username) && userpw.equals(password)){
	                        	User uUser = new User(username, password, email);
	                        	uUser.setPoints(Integer.parseInt(points));
	                        	uUser.setLevel(Integer.parseInt(level));
	                        	return uUser;
	                        }
	                    }
	                }
	            }
	        }
		}catch (SAXException | IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    	return null;
	}
	
	public void modifyUserPoints(User user){
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
	                        
	                        if(user.getUsername().equals(username)){
	                        	el.getElementsByTagName("points").item(0).setTextContent(Integer.toString(user.getPoints()));
	                        }
	                    }
	                }
	            }
	        }
	    	
	    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        DOMSource source = new DOMSource(doc);        
	        
	        //StreamResult result = new StreamResult(new File("users.xml"));
	        StreamResult result = new StreamResult(usersxml.toFile());
	        transformer.transform(source, result);
	    	
		}catch (SAXException | IOException | TransformerException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
	}
	
	public void modifyUserLevel(User user){
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
	                        
	                        
	                        if(user.getUsername().equals(username)){
	                        	el.getElementsByTagName("level").item(0).setTextContent(Integer.toString(user.getLevel()));
	                        }
	                    }
	                }
	            }
	        }
		}catch (SAXException | IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
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
