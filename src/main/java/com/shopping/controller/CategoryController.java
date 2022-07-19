package com.shopping.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.model.Products;
import com.shopping.model.Productsize;
import com.shopping.repository.CustomerRepository;
import com.shopping.repository.ProductsRepository;
import com.shopping.repository.SellerRepository;
import com.shopping.service.FavoritesService;
import com.shopping.service.TranslateService;

import antlr.StringUtils;

@Controller
public class CategoryController {
	@Autowired
	CustomerRepository Repository;
	
	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	ProductsRepository productsRepository;
	
	@Autowired
	FavoritesService favoritesService;
	
	@Autowired
	TranslateService translateService;
	@RequestMapping("/product")
	public String product(@RequestParam(value="page", required=false, defaultValue="1") String page, 
			            @RequestParam(value="options", required=false, defaultValue="0") String options ,
			            @RequestParam(value="category", required=false, defaultValue="all") String category,
			            @RequestParam(value="sellerid", required=false, defaultValue="none") String sellerid,
			            @RequestParam(value="userid", required=false, defaultValue="none") String userid,
			            @RequestParam(value="search", required=false, defaultValue="none") String search,
			            Model model,HttpSession session) 
		    {

		  boolean notseller=true;         
		  if(!userid.equals("none"))
		  {sellerid=String.valueOf(sellerRepository.findByCustomerId(Long.valueOf(userid)).get(0).getId());notseller=false;}
		  Long l=-1l;
		  if(session.getAttribute("uid")!=null)
		  {l=Long.valueOf(session.getAttribute("uid").toString());}
         
          List<Products> plist=null;
          List<Products> plistout=new ArrayList<Products>();
          List<Products> plistout2=new ArrayList<Products>();
          switch(options) { 
          case "0"://new
        	  plist=productsRepository.findByOrderByCreateTimeDesc();
          	break; 
          case "1"://best sell
              Comparator<Products> compareBysold = new Comparator<Products>() {
        			@Override
        			public int compare(Products p1,Products p2) {
        			   Integer int1=0;Integer int2 =0;Set<Productsize> ps1=p1.getProductsizes();Set<Productsize> ps2=p2.getProductsizes();
        				for(Productsize ps:ps1) {int1+=ps.getSold();};for(Productsize ps:ps2) {int2+=ps.getSold();};
                        return int1.compareTo(int2);
        			}
        		};
        	plist=productsRepository.findAll(); Collections.sort(plist,compareBysold);
          	break; 
          case "2"://price highest
        	  plist=productsRepository.findByOrderByProductprice();
          	break; 
          case "3"://price lowest
        	  plist=productsRepository.findByOrderByProductpriceDesc();
          	break; 
          default: 
          System.out.println("error!");
            }
       
        if(l>0 && notseller){ List<Integer> pidlist=new ArrayList<Integer>();
        	for(int n=0;n<plist.size();n++) {if(plist.get(n).getSeller().getCustomerId().toString().equals(l.toString())){pidlist.add(n);}}
        	Collections.reverse(pidlist);for(int n:pidlist) {plist.remove(n);}
       }
        ArrayList<Products> plista= (ArrayList)plist; 
        for(Products p:plista){  
        if(!category.equals("all") && !category.equals("seller") && !category.equals("search")) {if(p.getCategory().equals(category)) {plistout.add(p);}}
        else if(category.equals("seller")) {if(String.valueOf(p.getSeller().getId()).equals(sellerid)) {plistout.add(p);}}    ////判斷/////////
        else if(category.equals("all")) {plistout.add(p);}
        else if(category.equals("search")) {if(p.getProductName().toUpperCase().contains(search.toUpperCase())) {plistout.add(p);}}
        } 
        int pages=plistout.size()/9+1;     
        for(int n=0;n<(Integer.valueOf(page)-1)*9;n++){plistout.remove(0);}          
        int max=plistout.size()>=9? 9:plistout.size();
        for(int n=0;n<max;n++){plistout2.add(plistout.remove(0));}             
        if(l>0 ) {model.addAttribute("list1",favoritesService.getfavorites(plistout2, l));}
        List<Integer> pagelist=new ArrayList<Integer>();for(int n=1;n<=pages;n++) {pagelist.add(n);}
        model.addAttribute("narr",pagelist); 
    	model.addAttribute("page",Integer.valueOf(page).intValue()); 
    	model.addAttribute("pages",pages);  
		model.addAttribute("plist",plistout2);                               
		model.addAttribute("options", options );
		model.addAttribute("category", category );
		model.addAttribute("sellerid", sellerid );
		model.addAttribute("userid", userid );
		model.addAttribute("notseller", notseller );
		model.addAttribute("title",translateService.title("tw",category, sellerid, userid,search));
		model.addAttribute("sellerid", sellerid );
		model.addAttribute("seller",plistout2.size()>0?plistout2.get(0).getSeller().getHasImg()?plistout2.get(0).getSeller():null:null);
		return "product";
		        }
		    
}
