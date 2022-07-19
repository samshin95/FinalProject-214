package com.shopping.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.repository.CustomerRepository;
import com.shopping.repository.SellerRepository;

@Service
public class TranslateService {
	@Autowired
	CustomerRepository Repository;
	
	@Autowired
	SellerRepository sellerRepository;
	
	public String fee(String lan,double currency,String[] st,int[] in)
	{    ArrayList<String> Lan=new ArrayList<String>();
	     ArrayList<String> Lan2=new ArrayList<String>();
	     String fee="";
	if(lan=="tw") {Lan.add("7-11");Lan.add("全家");Lan.add("萊爾富");Lan.add("OK");Lan.add("郵局");Lan.add("黑貓");fee="運費";}
	if(lan=="cn") {Lan.add("7-11");Lan.add("全家");Lan.add("莱尔富");Lan.add("OK");Lan.add("邮局");Lan.add("黑猫");fee="运费";}
	if(lan=="en") {Lan.add("7-11");Lan.add("FamilyMart");Lan.add("Hi-Life");Lan.add("OK");Lan.add("Post office");Lan.add("t-cat");fee="shipping fee";}
	
	if(lan=="tw") {Lan2.add("滿1000免運");Lan2.add("滿1000免運");Lan2.add("滿199免運");Lan2.add("滿99免運");Lan2.add("滿80免運");Lan2.add("");}
	if(lan=="cn") {Lan2.add("满1000免运");Lan2.add("满1000免运");Lan2.add("满199免运");Lan2.add("满99免运");Lan2.add("满80免运");Lan2.add("");}
	if(lan=="en") {Lan2.add("Spend 1000 and Get Free Shipping");Lan2.add("Spend $1000 and Get Free Shipping");
	Lan2.add("Spend 199 and Get Free Shipping");Lan2.add("Spend 99 and Get Free Shipping");Lan2.add("Spend 80 and Get Free Shipping");Lan2.add("");}
	
	        StringBuffer sBuffer = new StringBuffer("");
			for(int n=0;n<st.length;n++) {    
				 switch(st[n]) {  
		            case "seven":
		            	sBuffer.append(Lan.get(n)+" "+fee+" $"+Math.round(in[n]*currency)+" "+Lan2.get(n)+"<br>");
		            	break; 
		            case "family":
		            	sBuffer.append(Lan.get(n)+" "+fee+" $"+Math.round(in[n]*currency)+" "+Lan2.get(n)+"<br>");
		            	break; 
		            case "hi-life":
		            	sBuffer.append(Lan.get(n)+" "+fee+" $"+Math.round(in[n]*currency)+" "+Lan2.get(n)+"<br>");
		            	break; 
		            case "ok":
		            	sBuffer.append(Lan.get(n)+" "+fee+" $"+Math.round(in[n]*currency)+" "+Lan2.get(n)+"<br>");
		            	break; 
		            case "post":
		            	sBuffer.append(Lan.get(n)+" "+fee+" $"+Math.round(in[n]*currency)+" "+Lan2.get(n)+"<br>");
		            	break; 
		            case "cat":
		            	sBuffer.append(Lan.get(n)+" "+fee+" $"+Math.round(in[n]*currency)+"<br>");
		            	break; 
		            default: 
		            System.out.println("error!");
		              }
			}
         return sBuffer.toString();
		
		
	}
	public String title(String lan,String category,String sellerid,String userid,String search)
	{   StringBuffer sBuffer = new StringBuffer("");  
	    String h1="<h1><Strong>"; String h2="</Strong></h1>";
		if(!userid.equals("none") && category.equals("seller")) {	 String name=Repository.getById(Long.valueOf(userid)).getName();
			switch(lan) {
			 case "tw":
		        	sBuffer.append(h1+"您的賣場"+h2+"<br>"+name+"歡迎回來");
		        	break; 
			 case "cn":
				    sBuffer.append(h1+"您的卖场"+h2+"<br>"+name+"欢迎回来");
		        	break; 
			 case "en":
				     sBuffer.append(h1+"Your store"+h2+"<br>"+name+"Welcome back");
		        	break; 
		}	return sBuffer.toString();
       
        	}
		if(!sellerid.equals("none")  && category.equals("seller")) {
			String name=Repository.getById(sellerRepository.getById(Long.valueOf(sellerid)).getCustomerId()).getName();
			String intro =sellerRepository.getById(Long.valueOf(sellerid)).getIntroduce();
		switch(lan) {    
		 case "tw":
	        	sBuffer.append(h1+name+"的賣場"+h2+"<br>"+intro);
	        	break; 
		 case "cn":
			    sBuffer.append(h1+name+"的卖场"+h2+"<br>"+intro);
	        	break; 
		 case "en":
			     sBuffer.append(h1+name+"'s store"+h2+"<br>"+name+intro);
	        	break; 
	}	return sBuffer.toString();
   
    	}
		
		if(category.equals("all")) { switch(lan) {    
		 case "tw":
	        	sBuffer.append(h1+"所有商品"+h2+"<br>歡迎光臨~");
	        	break; 
		 case "cn":
			    sBuffer.append(h1+"所有商品"+h2+"<br>欢迎光临~");
	        	break; 
		 case "en":
			     sBuffer.append(h1+"All Products"+h2+"<br>Welcome~");
	        	break; 
	}	return sBuffer.toString();}
		if(category.equals("men_top")) { switch(lan) {    
		 case "tw":
	        	sBuffer.append(h1+"男生上衣"+h2+"<br>歡迎光臨~");
	        	break; 
		 case "cn":
			    sBuffer.append(h1+"女生"+h2+"<br>欢迎光临~");
	        	break; 
		 case "en":
			     sBuffer.append(h1+"Men Tops"+h2+"<br>Welcome~");
	        	break; 
	}	return sBuffer.toString();}
		if(category.equals("women_top")) { switch(lan) {    
		 case "tw":
	        	sBuffer.append(h1+"女生上衣"+h2+"<br>歡迎光臨~");
	        	break; 
		 case "cn":
			    sBuffer.append(h1+"女生上衣"+h2+"<br>欢迎光临~");
	        	break; 
		 case "en":
			     sBuffer.append(h1+"Women Tops"+h2+"<br>Welcome~");
	        	break; 
	}	return sBuffer.toString();}
		if(category.equals("pants")) { switch(lan) {    
		 case "tw":
	        	sBuffer.append(h1+"褲子"+h2+"<br>歡迎光臨~");
	        	break; 
		 case "cn":
			    sBuffer.append(h1+"裤子"+h2+"<br>欢迎光临~");
	        	break; 
		 case "en":
			     sBuffer.append(h1+"Pants"+h2+"<br>Welcome~");
	        	break; 
	}	return sBuffer.toString();}
		if(category.equals("dress")) { switch(lan) {    
		 case "tw":
	        	sBuffer.append(h1+"裙子"+h2+"<br>歡迎光臨~");
	        	break; 
		 case "cn":
			    sBuffer.append(h1+"裙子"+h2+"<br>欢迎光临~");
	        	break; 
		 case "en":
			     sBuffer.append(h1+"Dresses"+h2+"<br>Welcome~");
	        	break; 
	}	return sBuffer.toString();}
		
		if(category.equals("men_shoe")) { switch(lan) {    
		 case "tw":
	        	sBuffer.append(h1+"男生鞋子"+h2+"<br>歡迎光臨~");
	        	break; 
		 case "cn":
			    sBuffer.append(h1+"男生鞋子"+h2+"<br>欢迎光临~");
	        	break; 
		 case "en":
			     sBuffer.append(h1+"Men Shoes"+h2+"<br>Welcome~");
	        	break; 
	}	return sBuffer.toString();}
		
		if(category.equals("women_shoe")) { switch(lan) {    
		 case "tw":
	        	sBuffer.append(h1+"女生鞋子"+h2+"<br>歡迎光臨~");
	        	break; 
		 case "cn":
			    sBuffer.append(h1+"女生鞋子"+h2+"<br>欢迎光临~");
	        	break; 
		 case "en":
			     sBuffer.append(h1+"Women Shoes"+h2+"<br>Welcome~");
	        	break; 
	}	return sBuffer.toString();}
		
		if(category.equals("hat")) { switch(lan) {    
		 case "tw":
	        	sBuffer.append(h1+"帽子"+h2+"<br>歡迎光臨~");
	        	break; 
		 case "cn":
			    sBuffer.append(h1+"帽子"+h2+"<br>欢迎光临~");
	        	break; 
		 case "en":
			     sBuffer.append(h1+"Hats"+h2+"<br>Welcome~");
	        	break; 
	}	return sBuffer.toString();}
		
		if(category.equals("bag")) { switch(lan) {    
		 case "tw":
	        	sBuffer.append(h1+"背包"+h2+"<br>歡迎光臨~");
	        	break; 
		 case "cn":
			    sBuffer.append(h1+"背包"+h2+"<br>欢迎光临~");
	        	break; 
		 case "en":
			     sBuffer.append(h1+"Bags"+h2+"<br>Welcome~");
	        	break; 
	}	return sBuffer.toString();}
		
		if(category.equals("search")) { switch(lan) {    
		 case "tw":
	        	sBuffer.append(h1+search+"的搜尋結果"+h2+"<br>歡迎光臨~");
	        	break; 
		 case "cn":
			    sBuffer.append(h1+search+"的搜尋結果"+h2+"<br>欢迎光临~");
	        	break; 
		 case "en":
			     sBuffer.append(h1+"Search results for"+search+h2+"<br>Welcome~");
	        	break; 
	}	return sBuffer.toString();}

		return sBuffer.toString();
		}
}
