package com.example.demo.util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.MercorChallengeApplication;
import com.example.demo.entity.Medicine;
import com.example.demo.repo.MedicineRepo;
import com.twilio.rest.api.v2010.account.Message;

@Component
public class UsingSQL {

	@Autowired
	private MedicineRepo medicineRepo;
	
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
				
				while(true) {
					
					LocalDate date=LocalDate.now();
					String dow=dayMap.get(date.getDayOfWeek().toString());
					
					List<Medicine> medicines=medicineRepo.findByDow(dow);
					System.out.println(medicines.size());
					
					// Hash map for combining same phone numbers and sending a common message for all medicines which needs to be taken on the same day
					HashMap<String, String> combinedNumberHashMap=new HashMap<>();
					
					for(int i=0;i<medicines.size();i++) {
						
						String phoneNumber=medicines.get(i).getPhoneNumber();
						if(combinedNumberHashMap.containsKey(phoneNumber)) {
							combinedNumberHashMap.put(phoneNumber, combinedNumberHashMap.get(phoneNumber)+" and "+medicines.get(i).getQuantity()+" of "+medicines.get(i).getMedicineName());
						}else {
							String mes="Remember to take "+medicines.get(i).getQuantity()+" of "+medicines.get(i).getMedicineName();
							combinedNumberHashMap.put(phoneNumber, mes);
						}
						
					}
					
					for (String key : combinedNumberHashMap.keySet()) {
						String mes=combinedNumberHashMap.get(key)+" today!";
//						System.out.println(key+" "+mes);
						 Message message = Message.creator( 
					                new com.twilio.type.PhoneNumber(MercorChallengeApplication.RECEIVER_NUMBER),  
					                "MG7550c8e9945f902dfb35ca9241230395", 
					               mes)      
					            .create();
					}
					
					try {
						Thread.sleep(86400000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
			}
		}).start();
		
	}
	
}
