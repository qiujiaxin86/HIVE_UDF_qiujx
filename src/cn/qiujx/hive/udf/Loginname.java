package cn.qiujx.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class Loginname extends UDF {
	public Integer evaluate(String str1){
		if (str1 == null){
			return 0;
		}
		String reg1 = "[0-9]{11}";
		String reg2 = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		String reg3 = "wxjj[0-9]{9}";
		if(str1.matches(reg1) == true){
			return 1;
		}else if(str1.matches(reg2)==true){
			return 2;
		}else if(str1.matches(reg3)==true){
			return 3;
		}else{
			return -1;
		}
	}
}
