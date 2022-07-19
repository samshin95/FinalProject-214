package com.shopping.controller;


	import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.json.JsonResult;
import com.shopping.model.Customer;
import com.shopping.repository.CustomerRepository;
import com.shopping.service.ExrateService;
import com.shopping.service.FileService;



	@Controller
	public class TestController {
		//透過 @RequestMapping 指定從/會被對應到此addMemberPage()方法
//		@Autowired
//		MemberAccount memberAccount;
	//	
//		@Autowired
//		MemberService memberService;
	//	
		@Autowired
		CustomerRepository customerRepository;
		
		@Autowired
		DataSource dataSource;
		
		@Autowired
		FileService fileService;
		
		@Autowired
		ExrateService exrateService;

		@GetMapping("/add")
	    public String test() throws Exception{
	       JsonResult result=new JsonResult();
	       ObjectMapper mapper=new ObjectMapper();
	       String json=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
	       System.out.print(json);
	        
			
	        return "redirect:/register";
	    
	}
		@GetMapping("/soutfile")
	    public String soutfile() throws Exception{
			//List <Customer> customerlist =customerRepository.findFirstByOrderByPhonenumber();
			//System.out.print("length="+customerlist.size());
			//System.out.print("length="+customerlist.get(0).getNickname());
			//for(Customer c: customerlist ) {System.out.print("id="+c.getId());}
			//System.out.print(exrateService.getexrate().toString());
			
	        return "redirect:/";
	    
	}
		}
		