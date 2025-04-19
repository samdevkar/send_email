package com.nt.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nt.service.UserService;

@RestController
public class restController {
	
	   @Autowired
	    private UserService excelService;

	    @PostMapping("/upload")
	    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
	        if (!file.getOriginalFilename().endsWith(".xlsx")) {
	            return ResponseEntity.badRequest().body("Please upload a valid Excel file");
	        }

	        try {
	            excelService.saveUsersFromExcel(file.getInputStream());
	            return ResponseEntity.ok("Excel file uploaded and data saved successfully");
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Failed to process Excel file");
	        }
	    }
	    
	    @GetMapping("/email")
	    public ResponseEntity<String>sendEmails(){
	    	excelService.sendMailtoUsers();
	    	return new ResponseEntity<String>("Mail sent Success",HttpStatus.OK);
	    }

}
