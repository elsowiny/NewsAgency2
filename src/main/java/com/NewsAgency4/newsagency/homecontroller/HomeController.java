package com.NewsAgency4.newsagency.homecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/home")
	public String viewHome() {
		
		//theModel.addAttribute("theDate", new java.util.Date());
		
		return "home";
	}
	
	@GetMapping("/")
	public String viewHome1() {
		
		
		return "home";
	}
	
	@GetMapping("/user")
	public String viewUserPage() {
		
		
		return "user";
	}
	
	@GetMapping("/agency")
	public String viewAgencyPage() {
		
		
		return "agency";
	}
	

}
