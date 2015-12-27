package countservice;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.RPC) //optional
public interface CountServiceJaxEndpoint {

		@WebMethod 
		String getCountResultAsString(String name);

}
