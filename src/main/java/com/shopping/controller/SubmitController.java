package com.shopping.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.model.Customer;
import com.shopping.model.Products;
import com.shopping.model.Productsize;
import com.shopping.model.Seller;
import com.shopping.model.Shipping;
import com.shopping.repository.CustomerRepository;
import com.shopping.repository.ProductsRepository;
import com.shopping.repository.ProductsizeRepository;
import com.shopping.repository.SellerRepository;
import com.shopping.repository.ShippingRepository;
import com.shopping.service.FileService;



@Controller
public class SubmitController {
	@Autowired
	CustomerRepository Repository;
	
	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	ProductsRepository productRepository;
	
	@Autowired
	ShippingRepository shippingRepository;
	
	@Autowired
	ProductsizeRepository productsizeRepository;
	
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	FileService fileService;
	
	@PostMapping("/submit")
	public String register(@RequestParam(value="userID", required=true) String userID, 
			@RequestParam(value="username", required=true,defaultValue = "none") String username,
			@RequestParam(value="telephone", required=true,defaultValue = "none") String telephone,
			@RequestParam(value="pass", required=true) String password,
			@RequestParam(value="email", required=true) String email,
			@RequestParam(value="backupemail", required=true,defaultValue = "none") String backupemail,
			@RequestParam(value="birth") String birth,
			Model model,HttpSession session) throws ParseException, IOException 
		    { 
		      List<Customer> list1=Repository.findByName(userID);
	          if(!list1.isEmpty()) {model.addAttribute("error","name already exists"); return "register";}
	          List<Customer> list2=Repository.findByEmail(email);
	          if(!list2.isEmpty()) {model.addAttribute("error","email already exists");return "register";}
	          List<Customer> list3=Repository.findByBackupemail(email);
	          if(!list3.isEmpty()) {model.addAttribute("error","email already exists");return "register";}
	          if(!backupemail.equals("none")) {System.out.print(backupemail+"start validate backupemail");
	        	  List<Customer> list5=Repository.findByEmail(backupemail);
		          if(!list5.isEmpty()) {model.addAttribute("error","backupemail already exists");return "register";}
		          List<Customer> list6=Repository.findByBackupemail(backupemail);
		          if(!list6.isEmpty()) {model.addAttribute("error","backupemail already exists");return "register";}
	          }
	        
	          if(!telephone.equals("none")) {           
	          if(telephone.length()==9) {telephone="0"+telephone;}
	          if (!telephone.matches("\\d{10}") || !telephone.startsWith("0")) {model.addAttribute("error","phone number format error");return "register";}
	          List<Customer> list4=Repository.findByPhonenumber(telephone);
	          if(!list4.isEmpty()) {model.addAttribute("error","phone number already exists");return "register";}
	        	  }
              
	          Customer customer=new Customer();
		      customer.setBackupemail(backupemail);
              if(birth.length()>0) {SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
              Date date = simpleDateFormat.parse(birth); Date datenow=new Date();
              if(date.after(datenow)) {model.addAttribute("error","birthday cannot be in the futre");return "register";}
               customer.setBirthday(date);
		      }
		      else { customer.setBirthday(null);}
		      customer.setEmail(email);
		      customer.setName(userID);
		      customer.setNickname(username);
		      customer.setPhonenumber(telephone);
		      customer.setPassword(password);
		      Repository.save(customer);
		      session.setAttribute("uid", customer.getId());
		      session.setAttribute("name", customer.getName());
		    
              return "redirect:/";
		       } 
	
	        @PostMapping("/signIn")
	        public String signIn(@RequestParam(value="username", required=true) String userID, 
	        		@RequestParam(value="pass", required=true) String pass, 
			Model model,HttpSession session) throws ParseException, IOException 
		    { 
	         List<Customer> list1=Repository.findByName(userID);
	         if(list1.isEmpty()) {model.addAttribute("error","username doesn't exist"); return "/sign_in";}
	         Customer customer=list1.get(0);
	         if(!customer.getPassword().equals(pass)){model.addAttribute("error","password incorrect"); return "/sign_in";}
	         session.setAttribute("uid", customer.getId());  
	         session.setAttribute("name", customer.getName());
	         if(customer.getHasImg())
		      {session.setAttribute("userpicnumb",customer.getPicnum()+customer.getExtension());}
	         
	         return "redirect:/";
		    }
	        
	        @RequestMapping("/logout")
	        public String signIn(HttpSession session,HttpServletRequest request) throws ParseException, IOException 
		    {   session.invalidate();
		    HttpSession newsession = request.getSession();
		    newsession.setAttribute("uid", -1l);  
             return "redirect:/";
		    }
	        
	        @PostMapping("/change")
	        public String change(@RequestParam(value="userID") String userID, 
	    			@RequestParam(value="username") String username,
	    			@RequestParam(value="telephone") String telephone,
	    			@RequestParam(value="pass") String password,
	    			@RequestParam(value="email") String email,
	    			@RequestParam(value="backupemail") String backupemail,
	    			@RequestParam(value="birthday") String birthday,
	    			@RequestParam(value="address") String address,
	    			@RequestParam(value="sell",required = false) String seller,@RequestParam("file") MultipartFile file,
	    			Model model,HttpSession session) throws ParseException, IOException 
                      {Date birth=null;            
	        	      if(userID!=null && userID.length()!=0) {            System.out.print("userID="+userID+userID.length());
	    		      List<Customer> list1=Repository.findByName(userID);
	    	          if(!list1.isEmpty()) {model.addAttribute("error","name already exists");  return "person-memberdata";}}
	    		     
	    		      if(telephone!=null && telephone.length()!=0) { if(telephone.length()==9) {telephone="0"+telephone;}   
	    	          if (!telephone.matches("\\d{10}") || !telephone.startsWith("0")) {model.addAttribute("error","phone number format error"); return "person-memberdata";}
	    	          List<Customer> list4=Repository.findByPhonenumber(telephone);
	    	          if(!list4.isEmpty()) {model.addAttribute("error","phone number already exists"); return "person-memberdata";}}
	    		      
	    		      if(email!=null && email.length()!=0) {List<Customer> list2=Repository.findByEmail(email);
	    	          if(!list2.isEmpty()) {model.addAttribute("error","email already exists"); return "person-memberdata";}
	    	          List<Customer> list3=Repository.findByBackupemail(email);
	    	          if(!list3.isEmpty()) {model.addAttribute("error","email already exists");return "person-memberdata";}}
	    		      
	    		      if(backupemail!=null && backupemail.length()!=0) {
    	        	  List<Customer> list5=Repository.findByEmail(backupemail);
    		          if(!list5.isEmpty()) {model.addAttribute("error","backupemail already exists"); return "person-memberdata";}
    		          List<Customer> list6=Repository.findByBackupemail(backupemail);
    		          if(!list6.isEmpty()) {model.addAttribute("error","backupemail already exists"); return "person-memberdata";}
    	              }
	    		      
	    		      if(birthday!=null && birthday.length()!=0) {SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");birth = simpleDateFormat.parse(birthday);
	                  Date datenow=new Date();
	                  if(birth.after(datenow)) {model.addAttribute("error","birthday cannot be in the future"); return "person-memberdata";}
	                  }
                      
	    		      Optional<Customer> customer=Repository.findById((long)session.getAttribute("uid"));
	    		      if(customer.isPresent()) {Customer c=customer.get();
	    		      
	    		      if(!file.isEmpty()) {
	    		    	   int picnum=-1;
	    				   int n=file.getOriginalFilename().lastIndexOf(".");
	    				   c.setExtension(".png");
	    				   List <Customer> customerlist =Repository.findFirstByOrderByPicnumDesc();
	    				   if(!customerlist.isEmpty() && customerlist.get(0).getPicnum()!=null) {picnum=customerlist.get(0).getPicnum()+1;}
	    				   fileService.option(file,picnum+".png",8);
	    				  c.setHasImg(true);
	    	              c.setPicnum(picnum);
session.setAttribute("userpicnumb",picnum+".png");
                    }else {System.out.println("not photo!");}
	    		      
	    		    
	    		      if(userID!=null && userID.length()!=0) {c.setName(userID);}
	    		      if(username!=null && username.length()!=0) {c.setNickname(username);}
	    		      if(password!=null && password.length()!=0) {c.setPassword(password);}
	    		      if(email!=null && email.length()!=0) {c.setEmail(email);}
	    		      if(address!=null && address.length()!=0) {c.setAddress(address);}
	    		      if(telephone!=null && telephone.length()!=0) {c.setPhonenumber(telephone);}
	    		      if(backupemail!=null &&  backupemail.length()!=0) {c.setBackupemail(backupemail);}
	    		      if(birth!=null) {c.setBirthday(birth);}
	    		      if(seller!=null &&  seller.length()>0) {if(seller.equals("yes")) {c.setSeller(true);
	    		      Seller sell=new Seller();sell.setCustomerId(c.getId());
	    		      sellerRepository.save(sell);}}
	    		      Repository.save(c);
                      return "redirect:/person-memberdata";}
	    		      return "redirect:/";
	    		       } 
	        @PostMapping("/marketIntroduce")
	        public String marketIntroduce(@RequestParam(value="introduce") String introduce,@RequestParam("file") MultipartFile file,
	    			Model model,HttpSession session) throws ParseException, IOException 
		    {List<Seller> list1=sellerRepository.findByCustomerId((long)session.getAttribute("uid"));
		    if(list1.isEmpty()) { return "redirect:/";}
		    
             Seller s=list1.get(0);model.addAttribute("intro",s.getIntroduce());
		    if(introduce==""){model.addAttribute("error","introduction is empty"); return "person-sell";}
		    s.setIntroduce(introduce);
		    
		    if(!file.isEmpty()) {
		    	   int picnum=-1;
				   int n=file.getOriginalFilename().lastIndexOf(".");
				   s.setExtension(file.getOriginalFilename().substring(n,file.getOriginalFilename().length()));
				   List <Seller> sellerlist =sellerRepository.findFirstByOrderByPicnumDesc();
				   if(!sellerlist.isEmpty() && sellerlist.get(0).getPicnum()!=null) {picnum=sellerlist.get(0).getPicnum()+1;}
				   fileService.option(file,picnum+file.getOriginalFilename().substring(n,file.getOriginalFilename().length()), 5);
				  s.setHasImg(true);
	              s.setPicnum(picnum);

    }else {System.out.println("not photo!");} 
		    
		    sellerRepository.save(s);return "redirect:/person-sell";
		    
		    }
	        
	        @PostMapping("/addproduct")
	        public String addproduct(HttpSession session,Model model,RedirectAttributes redirectAttributes,
	        		@RequestParam(value="productname") String productname,
	        		@RequestParam(value="producttext") String producttext,
	        		@RequestParam(value="shipping") List<String> shipping,
	        		@RequestParam(value ="size") String[] size,
	        		@RequestParam(value ="sizenum") String[] sizenum,
	        		@RequestParam(value="category") String category,
	        		@RequestParam(value="productprice") String productprice,
	        		@RequestParam("file") MultipartFile file
	        		) throws ParseException, IOException 
	        {
           List<Seller> list1=sellerRepository.findByCustomerId((long)session.getAttribute("uid"));
	       if(list1.isEmpty()) { return "redirect:/";}
	       List<Products> list2=productRepository.findByProductName(productname);
	       Seller seller=list1.get(0);
	       Set<Products> productSet = seller.getProducts();
	       if(!productSet.isEmpty()) {
	    	   for(Products prod:productSet) {if(prod.getProductName().equals(productname))
	    	   { model.addAttribute("error","product name already exists");return "sell_management";}}}
           Products product= new Products();
	       product.setCreateTime(new Date());
	       product.setProductName(productname);
	       product.setDescription(producttext);
	       product.setCategory(category);
	       product.setProductprice(Integer.parseInt(productprice));
	       product.setSeller(seller);
	       if(!file.isEmpty()) {
	    	   int picnum=-1;
			   int n=file.getOriginalFilename().lastIndexOf(".");
			   product.setExtension(file.getOriginalFilename().substring(n,file.getOriginalFilename().length()));
			   List <Products> productslist =productRepository.findFirstByOrderByPicnumDesc();
			   if(!productslist.isEmpty() && productslist.get(0).getPicnum()!=null) {picnum=productslist.get(0).getPicnum()+1;System.out.print(picnum);}
			   fileService.option(file,picnum+file.getOriginalFilename().substring(n,file.getOriginalFilename().length()), 4);
			   product.setHasImg(true);
               product.setPicnum(picnum);
			 
		       }else {System.out.println("not photo!");}
		      
	       
	       productRepository.saveAndFlush(product);
            for(String s: shipping){
            Shipping ship=new Shipping();
            ship.setHowto(s);
            switch(s) { 
            case "post":
            ship.setFee(80);
            	break; 
            case "cat": 
            ship.setFee(90);    
                break; 
            case "ok": 
            ship.setFee(45);   
                break; 
            case "hi-life": 
            ship.setFee(50);   
                break; 
            case "seven": 
            ship.setFee(60);       
                break; 
            case "family": 
            ship.setFee(60);
                break; 
            default: 
                System.out.println("no shipping error");
              }ship.setProducts(product);
              shippingRepository.save(ship);
		    }
		    
		  

		    for(int m=0;m<size.length;m++){
		    	Productsize productsize=new Productsize();
                productsize.setSize(sizenum[m]); 
		    	productsize.setLeft(Integer.parseInt(size[m]));
		    	productsize.setProductId(product.getId());
		    	productsize.setProducts(product);
		    	productsizeRepository.save(productsize);
		    	
		    	}
	  
		    redirectAttributes.addAttribute("productid",product.getId());
		       return "redirect:/single_product";
		    }
	        
	        @PostMapping("/alterproduct")
	        public String alterproduct(HttpSession session,Model model,RedirectAttributes redirectAttributes,
	        		@RequestParam(value="productid") String productid,
	        		@RequestParam(value="productname") String productname,
	        		@RequestParam(value="producttext") String producttext,
	        		@RequestParam(value="shipping") List<String> shipping,
	        		@RequestParam(value ="size") String[] size,
	        		@RequestParam(value ="sizenum") String[] sizenum,
	        		@RequestParam(value="category") String category,
	        		@RequestParam(value="productprice") String productprice,
	        		@RequestParam("file") MultipartFile file
	        		) throws ParseException, IOException 
	        {
	       if(session.getAttribute("uid")==""||session.getAttribute("uid")==null) {return "redirect:/";}	
           List<Seller> list1=sellerRepository.findByCustomerId((long)session.getAttribute("uid"));
           Seller seller=list1.get(0); 
	       if(list1.isEmpty()) { return "redirect:/";}
	       if(productname.length()>0 ) {Set<Products> list2=seller.getProducts();
	       for(Products prod:list2) {if(prod.getProductName().equals(productname))
	       {model.addAttribute("error","product name already exists");return "sell_management_alter";}}
	       }
	       Optional<Products> option1=productRepository.findById(Long.parseLong(productid));
	       Products product= option1.get();
	       if(productname.length()>0 ) {product.setProductName(productname);}
	       if(producttext.length()>0) {product.setDescription(producttext);}
	       if(category.length()>0) {product.setCategory(category);}
	       if(productprice.length()>0) {product.setProductprice(Integer.parseInt(productprice));}
	       if(!file.isEmpty()) {
	    	   int picnum=-1;
	    	   fileService.option(null,product.getPicnum().toString(), 6);
			   int n=file.getOriginalFilename().lastIndexOf(".");
			   product.setExtension(file.getOriginalFilename().substring(n,file.getOriginalFilename().length()));
			   List <Products> productslist =productRepository.findFirstByOrderByPicnumDesc();
			   if(!productslist.isEmpty() && productslist.get(0).getPicnum()!=null) {picnum=productslist.get(0).getPicnum()+1;}
			   fileService.option(file,picnum+file.getOriginalFilename().substring(n,file.getOriginalFilename().length()), 4);
			   product.setHasImg(true);
			   product.setPicnum(picnum);
             }else {System.out.println("not photo!");}
	      
            List <Shipping> shippinglist=shippingRepository.findByProducts(product);
            ArrayList<String> arrL = new ArrayList<String>(); 
            for(Shipping shipin:shippinglist) {
            	arrL.add(shipin.getHowto());
            	if(!shipping.contains(shipin.getHowto())) {shippingRepository.delete(shipin);System.out.println("shipin= "+shipin.getHowto());}}//sout test
            for(String s: shipping){
            if(!arrL.contains(s)) {
            Shipping ship=new Shipping();
            ship.setHowto(s);
            switch(s) { 
            case "post":
            ship.setFee(80);
            	break; 
            case "cat": 
            ship.setFee(90);    
                break; 
            case "ok": 
            ship.setFee(45);   
                break; 
            case "hi-life": 
            ship.setFee(50);   
                break; 
            case "seven": 
            ship.setFee(60);       
                break; 
            case "family": 
            ship.setFee(60);
                break; 
            default: 
                System.out.println("no shipping error");
              }ship.setProducts(product);
              shippingRepository.save(ship);
		    }
            }
		  
            List <Productsize> productsizelist=productsizeRepository.findByProducts(product);
            ArrayList<String> arrL2 = new ArrayList<String>(); 
            for( Productsize  productsiz:productsizelist) {
            	int n=Arrays.asList(sizenum).indexOf(productsiz.getSize());
            	if(n>-1) {productsiz.setLeft(Integer.valueOf(size[n]));productsizeRepository.save(productsiz);}
                arrL2.add( productsiz.getSize());
            }
		    for(int m=0;m<size.length;m++){
		    	if(!arrL2.contains(sizenum[m])) {
		    	Productsize productsize=new Productsize();
                productsize.setSize(sizenum[m]); 
		    	productsize.setLeft(Integer.parseInt(size[m]));
		    	productsize.setProductId(product.getId());
		    	productsize.setProducts(product);
		    	productsizeRepository.save(productsize);
		    	}
		    	}
		    productRepository.saveAndFlush(product);
		   
		   redirectAttributes.addAttribute("productid",product.getId());
	       return "redirect:/single_product";
		    }
	       
	        	
	        
	        	
	        
	       
             
		    
	       
	    		      
}

