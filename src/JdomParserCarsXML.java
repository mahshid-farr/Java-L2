import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * @author Mahshid Farrahinia
 * 
 * This class use JDOM API to read the cars.xml file
 *
 */
public class JdomParserCarsXML {


	public static void main(String[] args) {
		
		try {
	    	  
	         // Create a DocumentBuilder
	         SAXBuilder saxBuilder = new SAXBuilder();

	         // Create a Document from a file 
	         File inputFile = new File("cars.xml");
	         Document document = saxBuilder.build(inputFile);

	         // Extract the root element
	         Element rootElement = document.getRootElement();
	         System.out.println("Root element: " + rootElement.getName());
	         
	         // Examine sub-elements
	         List<Element> sportCarList = rootElement.getChildren();
	         System.out.println("================================");

	         for (int i = 0; i < sportCarList.size(); i++) {    
	        	 
	        	// Examine a single sub-element
	            Element sportCar = sportCarList.get(i);
	            System.out.println("\nElement :"  + sportCar.getName());
	            
	            // Examine the sub-text nodes and print out
	            System.out.println("Year : " + sportCar.getChild("YEAR").getText());
	            System.out.println("Make : "+ sportCar.getChild("MAKE").getText());
	            System.out.println("Model : "+ sportCar.getChild("MODEL").getText());
	            System.out.println("Price : "+ sportCar.getChild("PRICE").getText());	            		
	         }
	      }catch(JDOMException e){
	         e.printStackTrace();
	      }catch(IOException ioe){
	         ioe.printStackTrace();
	      }

	}

}
