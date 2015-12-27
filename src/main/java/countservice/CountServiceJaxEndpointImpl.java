package countservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import countlogic.Calc;
import countlogic.SimpleException;


//Service Implementation
@WebService(endpointInterface = "countservice.CountServiceJaxEndpoint")
public class CountServiceJaxEndpointImpl implements CountServiceJaxEndpoint{

	
	@Override
	public String getCountResultAsString(String expression) {
		try{
			Calc f = new Calc(expression);
			return f.getResult().toString();
			}
			catch (SimpleException e) {
				e.printStackTrace();
				return "Неверный ввод!";
			}
	}

}

