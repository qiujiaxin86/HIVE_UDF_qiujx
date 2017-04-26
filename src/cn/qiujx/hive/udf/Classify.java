package cn.qiujx.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class Classify extends UDF {
	public Integer evaluate (int user_id, String str2, String str3){
		String str1 = String.valueOf(user_id);
		if (str3 == null || str1 == null || str2 == null){
			return 0;
		}
		char[] chas3 = str3.toCharArray();
		char[] chas1 = str1.toCharArray();
	 	for(int i = 0; i<chas3.length; i++){
			int j = 0;
			while(j<chas1.length && i<chas3.length){
				if(chas3[i] == chas1[j]){
					i++;
					j++;
				}else{
					break;
				}
			}
			if(j==chas1.length){
				return 1;
			}
		}
	 	String reg1 = "^[a-z]{3}[0-9]{5}$";
		String reg2 = "^[a-z][0-9]{8}[a-z]$";
		String reg3 = "^[0-9]{1,2}[a-z][0-9]{8}[0-9]{1,2}[a-z]$";
		if(str2.matches(reg1) == true){
			return 2;
		}else if(str2.matches(reg2) == true){
			return 3;
		}else if(str2.matches(reg3) == true){
			return 4;
		}else{
			return 0;
		}
	}
}