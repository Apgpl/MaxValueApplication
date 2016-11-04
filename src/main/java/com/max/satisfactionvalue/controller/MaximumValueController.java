package com.max.satisfactionvalue.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.max.satisfactionvalue.service.MaximumValueService;


@RestController
public class MaximumValueController {
	
	@RequestMapping("/")
	
	public String getMaxSatisfactionValue() {			
		
		MaximumValueService mvs = new MaximumValueService();		
		return mvs.parseDataFileElements();
   }	
	
}