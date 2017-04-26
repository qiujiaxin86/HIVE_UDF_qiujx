package cn.qiujx.hive.udf;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.hadoop.hive.ql.exec.UDF;

public class MacConverse extends UDF{
    
    public static String mactohex(long input){
        double addresult = Math.pow(16, 16) + input;
        BigDecimal db = new BigDecimal(addresult);
        BigInteger mac_plainstring = new BigInteger(db.toPlainString());
        String mac_hex = mac_plainstring.toString(16);
        return mac_hex;
    }
    
    public static String macsplit(String hex){
    	char[] hex_char = hex.toCharArray();
    	char[] newlist = new char[17];
    	int j = 0;
    	for(int i=0; i<12; i++){
    		if(i%2 != 0 && i != 11){
    			newlist[j++] = hex_char[i];
    			newlist[j++] = ':';
    		} else{
    			newlist[j++] = hex_char[i];
    		}
    	}
    	return String.valueOf(newlist);
    }

    public String evaluate(long mac) throws Exception {
		try {
			String hex = mactohex(mac);
			String result = macsplit(hex);
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}
