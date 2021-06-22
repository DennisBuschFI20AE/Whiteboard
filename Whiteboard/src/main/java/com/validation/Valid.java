package com.validation;

import java.util.regex.Pattern;


public class Valid {
	
	public static final boolean isNameValid(String name) 
	{
		return Pattern.matches("[\\p{L}A-Za-z-]{2,40}", name.trim());
	}
	
	public static final boolean isPasswordValid(String password) 
	{
		return 	Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", password.trim() ); 
	}
	
}
