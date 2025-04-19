package com.nt.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nt.entity.UserEntity;
import com.nt.repository.UserRepo;

@Service
public class UserService {
	
	 @Autowired
	 private UserRepo exelRepo;
	 
	 @Autowired
	 private JavaMailSender javaMailSender;
	
	 public void saveUsersFromExcel(InputStream is) throws IOException {
	        Workbook workbook = new XSSFWorkbook(is);
	        Sheet sheet = workbook.getSheetAt(0);

	        for (Row row : sheet) {
	            if (row.getRowNum() == 0) continue; // skip header

	           UserEntity user =new UserEntity();
	            user.setId((int) row.getCell(0).getNumericCellValue());
	            user.setName(row.getCell(1).getStringCellValue());
	            user.setEmail(row.getCell(2).getStringCellValue());
	            user.setCountry(row.getCell(3).getStringCellValue());

	            exelRepo.save(user);
	        }

	        workbook.close();
	 }
	 
	 public void sendMailtoUsers() {
		 List<UserEntity>list=exelRepo.findAll();
		 
		 for(UserEntity  u:list) {
			 SimpleMailMessage simple=new SimpleMailMessage();
			 
			 simple.setTo(u.getEmail());
			 simple.setSubject("Test Spring Boot Email Function");
			 simple.setText("Hi User, This is test Application functionality with Spring boot");
			 
			 javaMailSender.send(simple);
		 }
	 }
	 
	 public void sendMailByAttachingFile() {
		 
	 }
	 
	 

}
