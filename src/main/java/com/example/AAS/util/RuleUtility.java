package com.example.AAS.util;

/*Author: Poonam Gadekar*/


public class RuleUtility {

	public static String getResult(String condition, String ruleValue, String dataFieldValue) {
		
	
		
		switch(condition) {
		
		case "CONTAINS":
			return ruleValue.contains(dataFieldValue)? "PASS":"FAIL";
		
		case "EQUALS":
			return ruleValue.equals(dataFieldValue)? "PASS":"FAIL";
		
		case "LESSER":
			int i = Integer.parseInt(ruleValue);
			int j = Integer.parseInt(dataFieldValue);		
			return i<j ?"PASS": "FAIL";
			
		case "GREATER":
			int a = Integer.parseInt(ruleValue);
			int b = Integer.parseInt(dataFieldValue);
			return a>b ?"PASS": "FAIL";
			
		default : 
			return "NO DATA";
		  
		}
		
		
	}

}

