package com.example.demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.util.Convert;
import com.example.demo.util.UsingCSV;
import com.example.demo.util.UsingSQL;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.content.v1.Content;
import com.twilio.twiml.voice.Start;

@SpringBootApplication
public class MercorChallengeApplication {
	
	public static final String RECEIVER_NUMBER="+919580162152";
	private static final String ACCOUNT_SID="AC5a6cbd82af67cd2cc2b2bf333508b724";
	private static final String AUTH_TOKEN="433e3acdc147116247c3cdd3142de788";
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(MercorChallengeApplication.class, args);
//		System.out.println(ACCOUNT_SID+" "+AUTH_TOKEN);
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
		
		// To send sms using data in csv format
//		
//		UsingCSV usingCSV=context.getBean(UsingCSV.class);
//		usingCSV.Execute();
		
		// To convert csv to sql
		
      Convert convert=context.getBean(Convert.class);
		try {
			convert.Convert();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//To send sms using data in sql database
		
//		UsingSQL usingSQL=context.getBean(UsingSQL.class);
//		usingSQL.Execute();
		
	}

}
