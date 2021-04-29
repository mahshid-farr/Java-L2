import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;


import java.io.File;

/**
 * 
 */

/**
 * @author Mahshid Farrahinia
 * This class connect to Oracle database through thin driver and then query the sporty cars table
 * and use the resultSet to create an XML file
 *
 */
public class DomCreateSoprtyCarXML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	      Connection connection = null;
	      Statement statement  = null;
	      ResultSet  resultSet  = null;
	      

	   			
	      try {
	    	   
	   	      // Create a DocumentBuilder
	   		  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	   		  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	          // Create an xml Document
			  Document document = dBuilder.newDocument();
			  // Create the root element
			  Element rootElement = document.createElement("SportyCars");
			  document.appendChild(rootElement);
			  	
			 //Step1.Loading drivers
	    	  Class.forName(OracleInfo.Driver_Class_Oracle);
	    	  
	    	  //Step2.Establishing Connections
	    	  connection = DriverManager.getConnection(OracleInfo.URL, OracleInfo.Username, OracleInfo.Password);
			  System.out.println("Connected database successfully...");
			  
			  //Step3.Creating and executing statements
			  String query = "select * from SPORTYCARS ";
				  
			  //Create a prepared statement
			  statement = connection.createStatement();
			  			
			  //Step4.Processing ResultSet
			  resultSet = statement.executeQuery(query);
			  
			  ResultSetMetaData resultSetmd = resultSet.getMetaData();
			    int columnCount = resultSetmd.getColumnCount();
			  
				  while (resultSet.next()) {
					  // Create a row element
					  Element row = document.createElement("Row");
					  rootElement.appendChild(row);
					  for (int i = 1; i <= columnCount; i++) {
					        String columnName = resultSetmd.getColumnName(i);
					        Object value = resultSet.getObject(i);
					        Element node = document.createElement(columnName);
					        node.appendChild(document.createTextNode(value.toString()));
					        row.appendChild(node);
						}
		         }
				  

					// write the content into xml file
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					
					//A TransformerFactory instance can be used to create Transformer and Templates objects.
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source        = new DOMSource(document);
					StreamResult result     = new StreamResult(new File("cars.xml"));
					transformer.transform(source, result);// transform method transform the xml source to the result
					// Output to console for testing
					StreamResult consoleResult = new StreamResult(System.out);
					transformer.transform(source, consoleResult);

	}catch (SQLException se) {
		// Handle errors for JDBC
		se.printStackTrace();
	} catch (Exception e) {
		// Handle errors for Class.forName
		e.printStackTrace();
	}

	//Step5.Close the connection in finally block.
	finally {
		try {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}

}
