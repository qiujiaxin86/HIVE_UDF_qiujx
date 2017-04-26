package cn.qiujx.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class StringContains extends UDF{
	public Integer evaluate (String str1, int int2){
		String str2 = String.valueOf(int2);
		if (str1 == null || str2 == null){
			return 0;
		}
		char[] chas1 = str1.toCharArray();
		char[] chas2 = str2.toCharArray();
		for(int i = 0; i<chas1.length; i++){
			int j = 0;
			while(j<chas2.length && i<chas1.length){
				if(chas1[i] == chas2[j]){
					i++;
					j++;
				}else{
					break;
				}
			}
			if(j==chas2.length){
				return 1;
			}
		}
		return 0;
	}
}
