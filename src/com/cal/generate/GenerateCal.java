package com.cal.generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import com.cal.mold.*;
public class GenerateCal {
	private static HashMap<String, String> hm = new HashMap<String, String>(5);
	private static String[] sign = {"+","*","-"};
	private List<OutFileCal> caList=new ArrayList<OutFileCal>();
	private RPNCalculator rpnc = new RPNCalculator("1+1");
	public GenerateCal(MainParameter mp){
		hm.put("n", mp.getN());
		hm.put("r", mp.getR());
		hm.put("o", mp.getO());
		if(mp.getL() != null)
			hm.put("l", mp.getL());
	}
	/* 
	*GetExercise入口方法
	*/
	public void Generation(){
		int n = Integer.parseInt(hm.get("n"));
		int num = 1;
		Random r = new Random();
		while(num <= n){
			//TODO:根据L生成题目长度
			int len = (r.nextInt(5)+1)*2+1;
			int range = Integer.parseInt(hm.get("r"));
			String res = GenerateCalExe(len,range); //得到题目
			SetFileCal(num,res); //放入题号和题目
			caList = rpnc.GetExerciseRes(caList); //计算结果，得到完整的题号,题目,结果
			//System.out.println("res:"+caList.get(num-1).getCalRes());
			num++;
		}
		FileCal fc = new FileCal();
		fc.FileOutCal(caList, hm.get("o"));
	}
	/* 
	*生成一道四则远算
	*/
	public String GenerateCalExe(int len,int range){ 
		String CalExe = "";
		Random r = new Random();
		FractionStack fs = new FractionStack();
		for(int i=0;i<len;i++){
			fs.setNumerator(r.nextInt(range)+1);
			fs.setDenominator(r.nextInt(range)+1);
			if(i==0)
				CalExe += Integer.toString(r.nextInt(range)+1);
			else{
				if(i%2 != 0)
					CalExe += GetSign();
				else
					CalExe += r.nextBoolean() ? Integer.toString(r.nextInt(range)+1) : "("+fs.getRes()+")";
			}
		}
		return CalExe;
	}
	/* 
	*算数符号出现频率控制
	*/  
	public String GetSign(){
		Random r = new Random();
		int n = r.nextInt(10);
		if(n<5)
			return sign[0];
		if(n>=5&&n<8)
			return sign[1];
		else
			return sign[2];
	}
	/* 
	*题号,题目放入List中
	*/
	public void SetFileCal(int num,String res){
		OutFileCal ofc = new OutFileCal();
		ofc.setNum(num);
		ofc.setCalExe(res);
		caList.add(ofc);
		//System.out.println(num + ":" +ofc.getCalExe());
	}
}
