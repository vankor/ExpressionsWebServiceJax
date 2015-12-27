package countlogic;

import java.util.*;

import java.lang.*;
import java.io.*;

public class Parser {
	private int right;
	private int left;
	private Double res;
		
	
	private boolean checkSkobki(String need){
		Boolean istrue;
		int counter = 0;
		for(int i = 0; i < need.length(); i++){
			if(need.charAt(i) == '('){
				
				counter++;
			}
			
			if(need.charAt(i) == ')'){
				
				counter--;
			}
		}
		
		return (counter==0);
		
	}
	
	Parser(String need){
		
		try {
			setRes(countInnerRes(need));
			setRight(need.indexOf(')'));
			setLeft(prevDujka(need, need.indexOf(')')));
		} catch (SimpleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			
		
		}


	Double countInnerRes(String need) throws SimpleException {
		if(!checkSkobki(need)){
			throw new SimpleException("Число открывающихся скобок должно равняться числу закрывающихся скобок!");
		}
//		if(!checkChars(need)){
//			throw new SimpleException("Неверные символы в строке!");
//		}
		int id1;
		int id2;
		Double result;
						
		id2 = need.indexOf(')');
		id1 = prevDujka(need, id2);
		
		if (id1!= -1 && id2!= -1){
			return countInnerRes(need.substring(id1+1, id2));
		}
		result = getPlusminusres(need,getFirstnumb(need),next(need,0));
		return result;
	}
			
	private boolean checkChars(String need) {
		Boolean istrue;
		char[] set = new char[3];
		int counter = 0;
		int pos = 0;
		for(int i = 0; i < need.length(); i++){
//			if(!Character.isDigit(need.charAt(i)){
				
//				counter++;
//			}
			
			if(need.charAt(i) == ')'){
				
				counter--;
			}
		}
		
		return (counter==0);
	}

	int prevDujka(String s, int id2){
		if (id2 == -1){
			return -1;
		}
		else{
		while (s.charAt(id2)!= '('){
		id2--;}
		return id2;
		}
	}
	
	Double getRes(){
		return res;
	}
	
	void setRes(Double res){
		this.res = res;
	}
	
	int getRight(){
		return this.right;
	}
	
	void setRight(int right){
		this.right = right;
	}
	
	int getLeft(){
		return this.left;
	}
	
	void setLeft(int left){
		this.left = left;
	}
	
	static Double getFirstnumb(String s){
		int i =0;
		Double result;
		if(s.length()==1){return new Double(s);}
		if(Character.isDigit(s.charAt(next(s,0))) ) 
		{result = new Double(s.substring(0)); }
		
		else if(s.charAt(0)=='-'){
			
			result = - new Double (s.substring(1,next(s,0)));
			
		}
		else{
			result = new Double (s.substring(0,next(s,0)));
		}
		return result;
	}
	
	static Double getPlusminusres(String s, Double result, int pos){
		if(next(s,pos)<s.length()){	
	if (!Character.isDigit(s.charAt(next(s,pos)))){
		
		if (pos == -1){return result;}
		switch (s.charAt(pos)){
		case '+': 
			if (s.charAt(next(s,pos)) == '*' || s.charAt(next(s,pos)) == '/'){
			result = result+getDivmultyres(s, rightnumb(s, pos),next(s,pos)); 
				if(nextAfterdivmulty(s,next(s,pos))==-1) {return result;}
			
			pos =  nextAfterdivmulty(s,next(s,pos));
			result = getPlusminusres(s, result, pos);}
			else
			{result  = result+rightnumb(s, pos);
			pos =  next(s,pos);
			result = getPlusminusres(s, result, pos);}
			break;
		
		case '-': 
			if (s.charAt(next(s,pos)) == '*' || s.charAt(next(s,pos)) == '/'){
				result = result-getDivmultyres(s, rightnumb(s, pos),next(s,pos));
					if(nextAfterdivmulty(s,next(s,pos))==-1) {return result;}
				pos =  nextAfterdivmulty(s,next(s,pos));
				result = getPlusminusres(s, result, pos);}
				else{result  = result-rightnumb(s, pos);
				pos =  next(s,pos);
				result = getPlusminusres(s, result, pos);}
				break;
		}
		
		if (s.charAt(pos) == '*' || s.charAt(pos) == '/'){
			result = getDivmultyres(s,result, pos);
			pos = nextAfterdivmulty(s, pos);
			result = getPlusminusres(s, result, pos);
		}
				
	}
	if (Character.isDigit(s.charAt(next(s,pos)))) {
		Double a;
		if (next(s,0) == pos){
		a = rightnumb(s, pos);
		}
		else {a = rightnumb(s, pos)/2;}
		
		switch (s.charAt(pos)){
		case '+': result  = result+a;break;
		case '-': result  = result-a;break;
		case '*': result  = result*a;break;
		case '/': result  = result/a;break;
			}
				
		}
		}
	return result;
	}
	
    static Double getDivmultyres(String s, Double result, int pos){
		
		
		if(s.charAt(pos) == '*'){	
		result = result*rightnumb(s, pos);}
		
		else if (s.charAt(pos) == '/'){	
		result = result/rightnumb(s, pos);}
		
		else {return result;}
		
		pos =  next(s,pos);
		result = getDivmultyres(s, result, pos);
		
		
		return result;
				}
	
	static Double rightnumb (String s, int id){
		Double num;
		try{
		if (!Character.isDigit(s.charAt(next(s,id)))){
		num = new Double(s.substring(id+1,next(s,id)));
		return num;
		}
		else {num = new Double(s.substring(id+1,next(s,id)+1));
		return num;}
		}
		catch (NumberFormatException e){return 0.0;}
	}
	
	
	
	static int prev (String s, int ix)
	{
	ix = ix-1;
	while(Character.isDigit(s.charAt(ix)) && ix >0) {ix --;}	
	return ix;
	}
	
	static int next (String s, int ix)
	{
	
	ix = ix+1;
	if(ix<s.length()){
	while((Character.isDigit(s.charAt(ix))||s.charAt(ix) == '.') && ix < s.length()-1) 	
			{ix ++;}	
	}
	return ix;
	}
	
	static int nextAfterdivmulty(String s, int pos) {
		int nextpos;
		if (!Character.isDigit(s.charAt(next(s, pos)))){
		if (s.charAt(next(s,pos)) == '*' || s.charAt(next(s,pos)) == '/') {
			pos = next (s, pos);
		nextpos = nextAfterdivmulty(s, pos);
		return nextpos;
		}
		
		else {return next (s,pos);}
		}
		else {return -1;}
		
	}
	
	public static void main(String args[]) throws SimpleException{
		
		
	BufferedReader d = new BufferedReader(new InputStreamReader(System.in));

	for(int i=0;i<10;i++){
	System.out.println("Введите выражение:");
	try{
	String s = d.readLine();
	Calc f = new Calc(s);
	System.out.println("Результат:" + f.getResult());
	}
	catch (IOException e) {
	System.out.println("Ошибка при вводе!");
			}
		}
	}		
}




	


class CountFunctions{
	private int leftind;
	private int rightind;
	private double result;
	private String body;
	private Functions funcname;
	private List<String> funcargs;
	
	int getLeftind(){
		return this.leftind;
	}
	int getRightind(){
		return this.rightind;
	}
	
	void setLeftind(int leftind){
		this.leftind = leftind;
	}
	
	void setRightind(int rightind){
		this.rightind = rightind;
	}
	
	void setBody(String body){
		this.body = body;
	}
	
	
	String getBody(){
		return this.body;
	}
	Functions getFuncname(){
		return this.funcname;
	}
	
	void setFuncname(Functions funcname){
		this.funcname = funcname;
	}
	
	void setFuncargs(List <String> funcargs){
		this.funcargs = funcargs;
	}
	
	List <String> getFuncargs(){
		return this.funcargs;
	}
	
	double getResult(){
		return this.result;
	}
	
	void setResult(double result){
		this.result = result;
	}
	
	CountFunctions(String s) {
		body=s;
	
	}
	
	public void parseAndCalc() throws SimpleException{
		String s = body;
		int i =0;
		int j =0;
		int count =0;
		int pos=0;
		int buck=0;
		int buck1=0;
		
	
		
		while(i<s.length()){
			if(Character.isLetter(s.charAt(i))){
			pos = i;
			break;}
			i++;
			}
		if(i==s.length()){
			setLeftind(-1);
			setRightind(-1);
		}
		else{
		for(j=pos;j<s.length();j++){
			if(s.charAt(j)=='('){
				buck++;
			}
			if(s.charAt(j)==')'){
			buck1++;
			if(buck1==buck){
			count=count+2-buck1;
			break;}
			count++;
			}
			count++;
			}
		
		setLeftind(pos);
		setRightind(pos+count-1);
		setBody(s.substring(pos,pos+count));
		try{
		parseFunction();
		calcFunc();}
		catch (StringIndexOutOfBoundsException e){
			throw new SimpleException("Неверный формат строки");
		}
		}
		
		
		
		
	}	
	
	private void parseFunction() throws StringIndexOutOfBoundsException, SimpleException{
		String s = this.body;
		int i =0;
		int j =0;
		int pos =0;
		String name = "";
		String el = "";
		List<String> argums = new ArrayList<String>();
		while(Character.isLetter(s.charAt(i))){
			name = name + s.charAt(i);
			i++;
		}
		
			setFuncname(getFunction(name));
		
		i++;
		while (i<s.length()-1){
		if (s.charAt(i)!= ','){
			el+=s.charAt(i);
			
			i++;
		}
		
		else{
		argums.add(el);
		el="";
		i++;
		}
		}
		argums.add(el);
		setFuncargs(argums);
	}	
	
	void calcFunc() throws SimpleException{
	double res;
	Double max=0.0;
	Double  min=0.0;
	int i =0;
	
	List<Calc> m = new ArrayList<Calc>();
	List<String> argums = this.funcargs;
	Iterator it = argums.listIterator();
	switch(this.funcname){
	case ABS:
			if(argums.size()!=1){
			throw new SimpleException ("Неверное число аргументов у функции pow. Должен быть 1!");
			}
			
			
			m.add(new Calc(argums.get(0)));
			res = Math.abs(m.get(0).getResult());
			this.result = res;
			break;
			
			
	case POW:	

			if(argums.size()!=2){
			throw new SimpleException ("Неверное число аргументов у функции pow. Должно быть 2!");
			
			}
	//		for(String arg:argums){
	//			try{
	//				Double numb = new Double(arg);
	//			}
	//			catch(NumberFormatException ex){
	//				throw new SimpleException ("Аргумент функции pow должен быть числом!");
					
	//			}
				
	//		}
			
			
				m.add(new Calc(argums.get(0)));
				m.add(new Calc(argums.get(1)));
				res = Math.pow(m.get(0).getResult(), m.get(1).getResult());
				
				setResult(res);
				
				break;
			
	case MAX:
			if(argums.size()==0){
				throw new SimpleException ("У функции max нет аргументов!");
				
			}
			for(String arg:argums){
				try{
					Double numb = new Double(arg);
				}
				catch(NumberFormatException ex){
					throw new SimpleException ("Аргументы функции max должны быть числами!");
					
				}
				
			}
			
			for(i = 0; i < argums.size(); i++) {
				m.add(new Calc(argums.get(i)));
					
				};
				
				max = m.get(0).getResult();
				for(i = 1; i < m.size(); i++){
					if(m.get(i).getResult()>max){
						max = m.get(i).getResult();
					}
				}
				setResult(max);
				
				
			break;
			
	case MIN:	
		if(argums.size()==0){
			throw new SimpleException ("У функции min нет аргументов!");
			
		}
		
		for(String arg:argums){
			try{
				Double numb = new Double(arg);
			}
			catch(NumberFormatException ex){
				throw new SimpleException ("Аргументы функции min должны быть числами!");
				
			}
			
		}
		
		
			for(i = 0; i < argums.size(); i++) {
				m.add(new Calc(argums.get(i)));
					
				};
				
				min = m.get(0).getResult();
				for(i = 1; i < m.size(); i++){
					if(m.get(i).getResult()<min){
						min = m.get(i).getResult();
					}
				}
				setResult(min);
				
		break;
	case LOG:	
		if(argums.size()!=2){
			throw new SimpleException ();
			}
		
		for(String arg:argums){
			try{
				Double numb = new Double(arg);
			}
			catch(NumberFormatException ex){
				throw new SimpleException ("Аргументы функции log должны быть числами!");
				
			}
			
		}
		
		break;
	}
	}
	
	
	String changeString(String s){
		s = s.substring(0,getLeftind())+getResult()+s.substring(getRightind());
		return s;
	}
	
	public static Functions getFunction(String name) throws SimpleException{
		name = name.toLowerCase();
		switch(name){
		case "min": return Functions.MIN;
		case "max": return Functions.MAX;
		case "log": return Functions.LOG;
		case "pow": return Functions.POW;
		case "abs": return Functions.ABS;
		default: throw new SimpleException("Функция " + name+ " не существует");
		}
		
		
	}
}

enum Functions{
	MIN("min"), MAX("max"), LOG("log"), POW("pow"), ABS("abs");
	
	private String name;
	Functions(String name){
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	
	
	
}







