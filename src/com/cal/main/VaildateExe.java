package com.cal.main;

import java.util.List;

import com.cal.generate.FileCal;
import com.cal.generate.GenerateCal;
import com.cal.mold.MainParameter;
import com.cal.mold.OutFileCal;
import com.cal.mold.RPNCalculator;

public class VaildateExe {
	/* 
	*参数类型
	*-e 读取作业
	*-o 输出题目到根目录
	*/  
	 public static void main(String args[]){
		 MainParameter mp = new MainParameter();
		 SetMainPara(mp,args); //获取参数
		 FileCal fc = new FileCal();
		 RPNCalculator rpnc = new RPNCalculator("");
		 //System.out.println("e:"+mp.getE());
		 List<OutFileCal> stuList = fc.ReadAnsFile(mp.getE()); //读取作业文件
		 List<OutFileCal> ansList = rpnc.GetExerciseRes(stuList); //正确结果
		 fc.CompareFile(stuList, ansList);
	 }
	 
	 public static void SetMainPara(MainParameter mp,String args[]){
		 if(0 != args.length){
			 for(int i=0;i<args.length;i++){
				 if(args[i].equals("-e")){mp.setE(args[i+1]);}
				 if(args[i].equals("-o")){mp.setO(args[i+1]);}
			 }
		 }
		 else
			 System.out.println("参数有误,请重新输入！");
	 }
}
