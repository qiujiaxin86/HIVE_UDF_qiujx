package cn.qiujx.hive.udf;

import java.util.ArrayList;
import org.apache.hadoop.hive.ql.exec.UDF;

public class NumberBetweenTwoInteger extends UDF{
	public ArrayList<Integer> evaluate(Object obj1, Object obj2){
		ArrayList<Integer> array = new ArrayList<Integer>();
		if(obj1 == null && obj2 == null){
			return null;
		}else if(obj2 == null && obj1 != null){
			int int1 = Integer.parseInt(String.valueOf(obj1));
			array.add(int1);
		}else if(obj1 == null && obj2 != null){
			int int2 = Integer.parseInt(String.valueOf(obj2));
			array.add(int2);
		}else{
			int int1 = Integer.parseInt(String.valueOf(obj1));
			int int2 = Integer.parseInt(String.valueOf(obj2));
			for(int i=int1; i<=int2; i++){
				array.add(i);
			}
		}
		return array;
	}
}
