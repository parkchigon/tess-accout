package com.vup.tess.global.util;

import java.util.Properties;

public class KeyGenerator {
	private static int commonShardDbSetId;

	private static final String INSTANCE_ID = "01";
	//System.getProperty("INSTANCE_ID");
	
	public static void setProperties(Properties properties) {
		try {
			
			
		} catch(Exception e) {
			
		}

	}
	
	public static String createCommonShardKey() throws Exception{
		return String.valueOf(FGOID.getGUID(commonShardDbSetId, getInstanceId(), 0));
	}
	
	public static String createCommonShardKey(int instanceId) throws Exception{
		return String.valueOf(FGOID.getGUID(commonShardDbSetId, instanceId, 0));
	}
	
	/**
	 * 서버 인스턴스 아이디
	 * @return
	 */
	public static int getInstanceId(){
		return Integer.parseInt(INSTANCE_ID);
	}
	
	public static void main(String[] args) throws Exception {
		
		for(int i=0; i<10; i++) {
			System.out.println(KeyGenerator.createCommonShardKey());
		}
	}
}
