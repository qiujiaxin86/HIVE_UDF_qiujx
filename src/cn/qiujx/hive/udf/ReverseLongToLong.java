package cn.qiujx.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class ReverseLongToLong extends UDF {
	public static String longToIP(long longIp){
        StringBuffer sb = new StringBuffer("");  
        sb.append(String.valueOf((longIp & 0x000000FF)));
        sb.append("."); 
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");  
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");      
        sb.append(String.valueOf((longIp >>> 24)));
        return sb.toString();
    }
	
	public static long ToBigLittle (int longIp){                
        byte[] data = new byte[8];
        for (int i = 0; i < data.length; i++) {
         data[i] = (byte) (longIp >> (8 * i));
        
        }
        // step 2
        int index = 0;
        int firstByte = (0x00FF & ((int) data[index]));
        int secondByte = (0x00FF & ((int) data[index + 1]));
        int thirdByte = (0x00FF & ((int) data[index + 2]));
        int fourthByte = (0x00FF & ((int) data[index + 3]));

        long unsignedLong = ((long) (fourthByte | thirdByte << 8 | secondByte << 16 | firstByte << 24)) & 0xFFFFFFFFL;
        return unsignedLong;
    }
	
	public static byte[] long_2byte(long length) {            
        byte[] targets = new byte[8];  
        for (int i = 0; i<8 ; i++) {  
            int offset = (targets.length - 1 - i) * 8;  
            targets[i] = (byte) ((length >>> offset) & 0xff);  
        }    
        return targets;  
    }
	
	public static byte[] reverse(byte[] b){
        int len = b.length;
        byte[] r = new byte[len];
        for (int i = 0; i < r.length; i++) {
        r[i] = b[len-1-i];
        }
        return r;
    }
	
	public static int byte2Int(byte[] b) {
        // 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
        byte[] res = reverse(b);
        int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或
        | ((res[2] << 24) >>> 8) | (res[3] << 24);
        return targets;
    }
	
	public long ipToLong3(String ipAddress) {  
	    long result = 0;  
	    String[] ipAddressInArray = ipAddress.split("\\.");   
	    for (int i = 3; i >= 0; i--) {  
	        long ip = Long.parseLong(ipAddressInArray[i]);  
	        // left shifting 24,16,8,0 and bitwise OR  
	        // 1. 192 << 24  
	        // 1. 168 << 16  
	        // 1. 1 << 8  
	        // 1. 2 << 0  
	        result |= ip << (i * 8);  
	    }    
	    return result;  
	}
	
	public Long evaluate(long nIP) throws Exception {
		try {
			byte[] longtobyte = long_2byte(nIP);
			int bytetoint = byte2Int(longtobyte);
	        long nRightIP = ToBigLittle(bytetoint);
	        String strRightIP = longToIP(nRightIP);
	        Long internetip = ipToLong3(strRightIP);
			return internetip;
		} catch (Exception e) {
			return null;
		}
	}
}

