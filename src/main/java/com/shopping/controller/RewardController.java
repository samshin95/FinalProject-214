package com.shopping.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.json.JsonResult3;
import com.shopping.repository.CouponsRepository;
import com.shopping.service.DemoService;

@RestController
public class RewardController {
	
//	@GetMapping("/ajaxget")
//	public  JsonResult ajax() {
//			int[] array3 = {0,0,5,4,3,8,66};
//			return new JsonResult(1,"test_123json ",array3);
//
//	}
	@Autowired 
	DemoService rungame2;
	@Autowired
	CouponsRepository couponsRepository;
	@PostMapping("/ajaxpost")
    public JsonResult3 demo11(@RequestParam MultiValueMap json1) throws Exception{
		Object linesobject = json1.getFirst("starter");
		int starter = Integer.valueOf((String) json1.getFirst("starter"));
		Object linesobject2 = json1.getFirst("cut");
		int cut = Integer.valueOf((String) json1.getFirst("cut"));
		Object linesobject3 = json1.getFirst("lines");
		int lines = Integer.valueOf((String) json1.getFirst("lines"));
		Object linesobject4 = json1.getFirst("divide");
		int divide = Integer.valueOf((String) json1.getFirst("divide"));
		
		Object linesobject5 = json1.getFirst("reward");
		int reward = Integer.valueOf((String) json1.getFirst("reward"));
         //System.out.println(starter);
		//System.out.println(cut);
		//System.out.println(lines);
		//System.out.println(divide);
		//System.out.println(reward);
		
		JsonResult3 js3= rungame2.rungame(starter, lines, divide, cut);
		return js3;
	}	

	
}
