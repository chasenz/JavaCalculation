package com.cal.mold;

public class FractionCal {
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fracAdd(1,5,7,20);//结果为：11/20
		fracSub(1,5,7,20);//分数相减
		fracMul(1,5,7,20);//分数相乘
		fractDiv(1,5,7,20);//分数相除
	}*/
	//private FractionStack fs = new FractionStack();
	//分数加法
	public FractionStack fracAdd(FractionStack f1,FractionStack f2){
		FractionStack fs = new FractionStack();
		int first_numerator = f1.getNumerator();int first_denominator = f1.getDenominator();
		int second_numrator = f2.getNumerator();int second_denominator = f2.getDenominator();
		int denominator;
		int numerator;
		
	    if(first_denominator==second_denominator)  //分母相同时加分子     
	    {      
	         denominator=first_denominator;      
	         numerator=first_numerator+second_numrator;      
	    }      
	    else  //否则同分比较分子     
	    {      
	    	denominator=first_denominator*second_denominator;      
	    	numerator=first_numerator*second_denominator+first_denominator*second_numrator;      
	    }    
	    int gcd = gcd(numerator,denominator);
		denominator = denominator / gcd;
		numerator = numerator / gcd;
		fs.setNumerator(numerator);
		fs.setDenominator(denominator);
		//System.out.println("加法输出的结果是"+fs.getRes());
	    return fs;      

	}
	//分数减法
	public FractionStack fracSub(FractionStack f1,FractionStack f2){
		FractionStack fs = new FractionStack();
		int first_numerator = f1.getNumerator();int first_denominator = f1.getDenominator();
		int second_numerator = f2.getNumerator();int second_denominator = f2.getDenominator();
		int denominator;
		int numerator;
		
	    if(first_denominator==second_denominator)  //分母相同时加分子     
	    {      
	         denominator=first_denominator;      
	         numerator=first_numerator-second_numerator;      
	    }      
	    else  //否则同分比较分子     
	    {      
	    	denominator=first_denominator*second_denominator;      
	    	numerator=first_numerator*second_denominator-first_denominator*second_numerator;      
	    }    
	    int gcd = gcd(numerator,denominator);
		denominator = denominator / gcd;
		numerator = numerator / gcd;	
		fs.setNumerator(numerator);
		fs.setDenominator(denominator);
		//System.out.println("减法输出的结果是"+fs.getRes());
	    return fs;      

	}
	//分数乘法
	public FractionStack fracMul(FractionStack f1,FractionStack f2){
		FractionStack fs = new FractionStack();
		int first_numerator = f1.getNumerator();int first_denominator = f1.getDenominator();
		int second_numerator = f2.getNumerator();int second_denominator = f2.getDenominator();
		int denominator;
		int numerator;
		
	    denominator=first_denominator*second_denominator;      
	    numerator=first_numerator*second_numerator; 
	      
	    int gcd = gcd(numerator,denominator);
		denominator = denominator / gcd;
		numerator = numerator / gcd;	
		fs.setNumerator(numerator);
		fs.setDenominator(denominator);
		//System.out.println("乘法输出的结果是"+fs.getRes());
	    return fs;      

	}
	//分数除法
	public FractionStack fractDiv(FractionStack f1,FractionStack f2){
		FractionStack fs = new FractionStack();
		int first_numerator = f1.getNumerator();int first_denominator = f1.getDenominator();
		int second_numerator = f2.getNumerator();int second_denominator = f2.getDenominator();
		
		int denominator;
		int numerator;
		
		numerator = first_numerator*second_denominator;
		denominator = first_denominator*second_numerator;
			    	
	    int gcd = gcd(numerator,denominator);
		denominator = denominator / gcd;
		numerator = numerator / gcd;
		fs.setNumerator(numerator);
		fs.setDenominator(denominator);
		//System.out.println("除法输出的结果是"+fs.getRes());
	    return fs;     

	}
	//获取最大公约数
	static int gcd(int x,int y){
		 int r;      
		    while( y!= 0)      
		    {      
		        r = x%y;      
		        x = y;      
		        y = r;      
		    }      
		return x;		
	}
}
