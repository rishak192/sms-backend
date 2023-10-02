package com.example.demo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Medicine;
import com.example.demo.entity.Patient;
import com.example.demo.repo.MedicineRepo;
import com.example.demo.repo.PatientRepo;

// Util class for converting csv to sql

@Component
public class Convert {
	
	@Autowired
	private MedicineRepo medicineRepo;
	
	@Autowired
	private PatientRepo patientRepo;
	
	public void Convert() throws Exception {
		
		BufferedReader reader = new BufferedReader(new FileReader("PatientData.csv"));
		String line;

		 while ((line = reader.readLine()) != null) {
		 
			 String[] patient=line.split(",");
			 
			 String pName=patient[0];
			 String pNumber=patient[1];
			 String pMedicine=patient[2];
			 String pQuantity=patient[3];
			 String pDayString=patient[4];
			 
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
				 
				 Medicine medicine=new Medicine();
				 medicine.setDow(currDay);
				 medicine.setMedicineName(pMedicine);
				 medicine.setPhoneNumber(pNumber);
				 medicine.setQuantity(pQuantity);
				 medicineRepo.save(medicine);
				 
			 }
			 
			 Patient p=new Patient();
			 p.setName(pName);
			 p.setPhone(pNumber);
			 patientRepo.save(p);
			 
		 }
		
	}
	
}
