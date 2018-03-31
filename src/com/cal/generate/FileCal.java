package com.cal.generate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import com.cal.mold.*;

public class FileCal {
	public void FileOutCal(List<OutFileCal> caList,String filename){
		 try {
	            //向桌面写入输出结果
	            File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
	            String desktopPath = desktopDir.getAbsolutePath();
	            FileOutputStream fos = new FileOutputStream(desktopPath+"/"+filename+".txt");
	            for(int i=0;i<caList.size();i++){
	            	//格式形如 1 1+5/4 2'1/4
	            	String font = String.valueOf(caList.get(i).getNum())+"\t"
	            					+caList.get(i).getCalExe()+"\t\t"
	            					+caList.get(i).getCalRes();
	            	fos.write(font.getBytes());
	            	fos.write("\r\n".getBytes());// 写入一个换行
	            } 
	            fos.close();
	            System.out.println("四则远算题目已经创建在桌面的"+filename+".txt文件中！");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	public List<OutFileCal> ReadAnsFile(String filename){
        //读取桌面结果
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();
        File file = new File(desktopPath+"/"+filename+".txt");
		//保存一行数组数组
		String[] line = new String[3];
		List<OutFileCal> ResList = new ArrayList<OutFileCal>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            	line = s.split("\t");
            	//System.out.println("NUM:"+line[0]);
            	OutFileCal ofc = new OutFileCal();
            	ofc.setNum(Integer.parseInt(line[0])); //题号
            	ofc.setCalExe(line[1]); //题目
            	ofc.setCalRes(line[2]); //答案
            	ResList.add(ofc);
            	//System.out.println("timu:"+line[1]);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return ResList;
	}
	
	public void CompareFile(List<OutFileCal> stu,List<OutFileCal> ans){
		 try {
	            //向桌面写入输出结果
	            File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
	            String desktopPath = desktopDir.getAbsolutePath();
	            FileOutputStream fos = new FileOutputStream(desktopPath+"/Grade.txt");
	            List<String> correct = new ArrayList<String>();
	            List<String> wrong = new ArrayList<String>();
	            for(int i=0;i<ans.size();i++){
	            	//比较结果
	            	if(ans.get(i).getCalRes().equals(stu.get(i).getCalRes()))
	            		correct.add(String.valueOf(ans.get(i).getNum())); //获取正确题号
	            	else
	            		wrong.add(String.valueOf(ans.get(i).getNum()));
	            }
	            fos.write(("Correct:"+correct.size()+"(").getBytes());
	            for(int i=0;i<correct.size();i++){
	            	fos.write(correct.get(i).getBytes());
	            	if(i<correct.size()-1)
	            		fos.write(",".getBytes());
	            }
	            fos.write(")".getBytes());
	            fos.write("\r\n".getBytes());
	            fos.write(("Wrong:"+wrong.size()+"(").getBytes());
	            for(int i=0;i<wrong.size();i++){
	            	fos.write(wrong.get(i).getBytes());
	            	if(i<wrong.size()-1)
	            		fos.write(",".getBytes());
	            }
	            fos.write(")".getBytes());
	            fos.close();
	            System.out.println("题目反馈已经创建在桌面的Grade.txt文件中！");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
