package com.example.demo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.example.demo.MercorChallengeApplication;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Component
public class UsingCSV {
	
	public void Execute() {
		
		HashMap<String, String> dayMap=new HashMap<>();
		dayMap.put("SUNDAY","S");
		dayMap.put("MONDAY","M");
		dayMap.put("TUESDAY","T");
		dayMap.put("WEDNESDAY","W");
		dayMap.put("THURSDAY","Th");
		dayMap.put("FRIDAY","F");
		dayMap.put("SATURDAY","Sa");

		new Thread(new Runnable() {
			public void run() {
				
				 try {
					 while(true) {

						BufferedReader reader = new BufferedReader(new FileReader("PatientData.csv"));
						String line;

						 while ((line = reader.readLine()) != null) {
							 
							 String[] patient=line.split(",");
							 
							 String pName=patient[0];
							 String pNumber=patient[1];
							 String pMedicine=patient[2];
							 String pQuantity=patient[3];
							 String pDayString=patient[4];
							 
							 
							 LocalDate date=LocalDate.now();
							 String dow=dayMap.get(date.getDayOfWeek().toString());
							 
//							 System.out.println(dow+" "+pName+" "+pNumber+" "+pMedicine+" "+pQuantity+" "+pDayString);
							 
							 boolean sendMes=false;
							 int n=pDayString.length();
							 for(int i=0;i<pDayString.length();) {
								 String currDay="";
								 if(i+1<n && (pDayString.charAt(i+1)=='h' || pDayString.charAt(i+1)=='a')) {
									 
									 currDay=""+pDayString.charAt(i)+pDayString.charAt(i+1);
									 i+=2;
									 
								 }else {
									 
									 currDay=""+pDayString.charAt(i);
									 i+=1;
									 
								 }
								 								 
								 if(currDay.equals(dow)) {
									 sendMes=true;
									 break;
								 }
								 
							 }
							 
							 if(sendMes) {
								 String mes="Remember to take "+pQuantity+" of "+pMedicine+" today!";
								 Message message = Message.creator( 
							                new com.twilio.type.PhoneNumber(MercorChallengeApplication.RECEIVER_NUMBER),  
							                "MG7550c8e9945f902dfb35ca9241230395", 
							               mes)      
							            .create();
							 }
						 }
						Thread.sleep(86400000);  //After 24 hrs reminder will be send
					 }

				} catch (Exception e) {
					e.printStackTrace();
				}
				 					
			}
		}).start();		
	}
	
}
