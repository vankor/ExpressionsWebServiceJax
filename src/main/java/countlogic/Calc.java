package countlogic;

public class Calc {
	
	private Double result;
	private String expression;
	
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Calc (String s) {
		this.expression = s;
		
	}
	
			
	public Double getResult() throws SimpleException{
		if(result==null){
			return count(expression);
		}
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	Double count (String s) throws SimpleException{
		Parser f;
		CountFunctions t;
		t = new CountFunctions(s);
		t.parseAndCalc();
		if(t.getLeftind()!=-1 || t.getRightind()!=-1){
		s = repl(s,t.getLeftind(),t.getRightind(),t.getResult()); 
		return count(s);
		}
		else{
		f = new Parser(s);
		if (f.getRight()!=-1 && f.getLeft()!=-1){
		s = repl(s,f.getLeft(),f.getRight(),f.getRes());
		return count(s);
	}
		else {
			Double res = f.getRes();
			setResult(res);
			return res;
		}
		}
		}
	
	static String repl(String s, int begin, int end, double res) {
		s = s.substring(0, begin)+Double.toString (res)+s.substring(end+1);
		return s;
	}
}
