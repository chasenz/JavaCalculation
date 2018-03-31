package trashcan;

import java.util.HashMap;
import java.util.Stack;

import com.cal.mold.FractionCal;
import com.cal.mold.FractionStack;

public class test2 {

	//运算符优先级
	private static HashMap<String,Integer> opLs;
	private FractionCal fc = new FractionCal();
	private String src;

	public test2(String src) {
		this.src = src;
		if(opLs==null)
		{
			opLs = new HashMap<String,Integer>(6);
			opLs.put("+",0);
			opLs.put("-",0);
			opLs.put("*",1);
			opLs.put("/",1);
			opLs.put("%",1);
			opLs.put(")",2);
		}
	}

	//将中缀表达式转化为后缀表达式
	public String toRpn()
	{
		String[] tmp = split(src);
		// 后缀栈
		Stack<String> rpn = new Stack<String>();
		// 临时栈
		Stack<String> tmpSta = new Stack<String>();

		for (String str : tmp) {
			if (isNum(str)) {
				//是操作数,直接压入结果栈
				rpn.push('('+str+')');
			}else{
				//是操作符号
				if(tmpSta.isEmpty())
				{//还没有符号
					tmpSta.push(str);
				}else{
				 //判读当前符号和临时栈栈顶符号的优先级
					if(isHigh(tmpSta.peek(),str))
					{
						if(!str.equals(")"))
						{
							do{
								//1在临时栈中找出小于当前优先级的操作符
								//2压入当前读入操作符
								rpn.push(tmpSta.peek());
								tmpSta.pop();
							}while(!tmpSta.isEmpty()&&(isHigh(tmpSta.peek(),str)));
							
							tmpSta.push(str);
						}else{
							//是)依次弹出临时栈的数据，直到(为止
							while(!tmpSta.isEmpty()&&!tmpSta.peek().equals("("))
							{
								rpn.push(tmpSta.pop());
							}
							if((!tmpSta.empty())&&(tmpSta.peek().equals("(")))
							{//弹出(
								tmpSta.pop();
							}
						}
					}else if(!isHigh(tmpSta.peek(),str)){
						tmpSta.push(str);
					}
				}
			}

		}
		while(!tmpSta.empty())
		{//把栈中剩余的操作符依次弹出
			rpn.push(tmpSta.pop());
		}
		StringBuilder st = new StringBuilder();
		for (String str : rpn) {
        		st.append(str);
		}
		rpn.clear();
		return st.toString();
	}

	//分割(56+4)3*6+2=>(,56,+,4,
	private String[] split(String src) {
		StringBuilder sb = new StringBuilder(src.length());
		for(char ch:src.toCharArray())
		{
			if(ch=='+'||ch=='-'||ch=='*'||ch=='*'||ch=='/'||ch=='('||ch==')'||ch=='%')
			{
				sb.append(",");
				sb.append(ch);
				sb.append(",");
			}else{
				sb.append(ch);
			}
		}
		String string = sb.toString().replaceAll(",{2,}", ",");
		return string.split(",");
	}

	//比较操作符的优先级
	private boolean isHigh(String pop, String str) {
		if(str.equals(")"))
			return true;
		if(opLs.get(pop)==null||opLs.get(str)==null)
		  return false;
		return opLs.get(pop)>=opLs.get(str);
			
	}

	//是否是数字
	public boolean isNum(String str) {
		for (char ch : str.toCharArray()) {
			if(ch=='+'||ch=='-'||ch=='*'||ch=='*'||ch=='/'||ch=='('||ch==')'||ch=='%')
				return false;
		}
		return true;
	}

	//得到结果
	public FractionStack getRes() {
		String rpn = toRpn();
		Stack<FractionStack> res = new Stack<FractionStack>();
		StringBuilder sb = new StringBuilder();
		for(char ch:rpn.toCharArray())
		{ 
			if(ch=='(')
			{
				continue;
			}else if(ch>='0'&&ch<='9'||ch=='.'){
				sb.append(ch);
			}else if(ch==')')
			{
            	FractionStack fs = new FractionStack();
            	fs.setNumerator(Integer.parseInt(sb.toString()));
            	fs.setDenominator(1);
				res.push(fs);
				sb = new StringBuilder();
			}else{
				 if(!res.empty())
				 {
					 FractionStack rear = res.pop();
					 FractionStack front = res.pop();
					 switch (ch) {
					case '+':
						 res.push(fc.fracAdd(front, rear)); 
						break;
					case '-':
						res.push(fc.fracSub(front, rear)); 
						break;
					case '*':
						res.push(fc.fracMul(front, rear)); 
						break;	
					case '%':
					case '/':	
						res.push(fc.fractDiv(front, rear)); 
						break;
					 }
			}
			}
		}
		FractionStack result = res.pop();
		res.clear();
		return result;
	}

	public static void main(String[] args) {
		String src2 = "9-3-2-1";
		test2 analyer = new test2(src2);
		System.out.println(src2+"="+analyer.getRes().getRes());
	}
}

