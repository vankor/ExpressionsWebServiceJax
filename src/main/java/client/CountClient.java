package client;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

import countservice.CountServiceJaxEndpoint;


public class CountClient extends HttpServlet{
	
	@WebServiceRef(wsdlLocation="http://localhost:8080/CountWebServiceJaxDeployed?wsdl")
    static CountServiceJaxEndpoint service;
	
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
    	 try {
             CountClient client = new CountClient();
             client.doTest("12+13");
         } catch(Exception e) {
             e.printStackTrace();
         }
	}

	

    public void doTest(String name) {
        try {
            System.out.println("Retrieving the port from the following service");
           
            
            String str = service.getCountResultAsString(name);
            System.out.println("Counting on the port.");
            System.out.println("Результат подсчета: "+str);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}