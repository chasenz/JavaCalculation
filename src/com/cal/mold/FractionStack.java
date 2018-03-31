package com.cal.mold;

public class FractionStack {
	/** 
	*分装一个分数，用于Stack,FractionCal计算使用
	*/
	private int numerator; //分子
	private int denominator;//分母
	public int getNumerator() {
		return numerator;
	}
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}
	public int getDenominator() {
		return denominator;
	}
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	/* 
	*结果形如 5/4
	*/
	public String getRes(){
		return this.numerator+"/"+this.denominator;
	}
	/* 
	*可以得到真分数
	*/
	public String getRealRes(){
		if(this.denominator == 1)
			return String.valueOf(this.numerator);
		if(this.numerator > this.denominator){
			int factor = (this.numerator - this.numerator % this.denominator) / this.denominator;
			this.numerator = this.numerator % this.denominator;
			String res = String.valueOf(factor)+"'"+ this.numerator+"/"+this.denominator;
			return res;
		}
		if(this.numerator == this.denominator)
			return "1";
		else
			return this.numerator+"/"+this.denominator;
	}
}
