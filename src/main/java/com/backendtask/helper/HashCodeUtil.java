package com.backendtask.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * Hash Code Util for hashing the password during the registeration
 * 
 * @author Belal
 *
 */
public class HashCodeUtil {

	public static String getHashPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

}
