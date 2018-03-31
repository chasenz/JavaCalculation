package com.cal.main;

import java.util.Arrays;
import com.cal.mold.*;
import com.cal.generate.*;
public class GetExercise {
	/* 
	*参数类型
	*-n 生成题目数量
	*-r 数值范围
	*-o 输出题目到根目录
	*-l 题目难度
	*/  
	 public static void main(String args[]){
		 MainParameter mp = new MainParameter();
		 //System.out.println("四则运算生成器: -n 选择题目数量  -r 数值范围  -o 文件名   -l 题目难度");
		 SetMainPara(mp,args); //获取参数
		 //System.out.println("-n:"+mp.getN());
		 GenerateCal gc = new GenerateCal(mp);
		 gc.Generation();
	 }
	 
	 public static void SetMainPara(MainParameter mp,String args[]){
		 if(0 != args.length){
			 for(int i=0;i<args.length;i++){
				 if(args[i].equals("-r")){mp.setR(args[i+1]);}
				 if(args[i].equals("-n")){mp.setN(args[i+1]);}
				 if(args[i].equals("-o")){mp.setO(args[i+1]);}
				 if(args[i].equals("-l")){mp.setL(args[i+1]);}
			 }
		 }
		 else
			 System.out.println("参数有误,请重新输入！");
	 }
}
