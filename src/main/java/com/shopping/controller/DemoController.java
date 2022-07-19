package com.shopping.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.json.JsonResult;
import com.shopping.model.Customer;
import com.shopping.model.Favorites;
import com.shopping.model.Order;
import com.shopping.model.Products;
import com.shopping.model.Productsize;
import com.shopping.repository.CustomerRepository;
import com.shopping.repository.FavoritesRepository;
import com.shopping.repository.OrderRepository;
import com.shopping.repository.ProductsRepository;
import com.shopping.repository.SellerRepository;
import com.shopping.repository.ShippingRepository;
import com.shopping.service.ExrateService;

@RestController
public class DemoController {
	@Autowired
	ExrateService exrateService;
	
	@Autowired
	CustomerRepository Repository;
	
	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	ProductsRepository productRepository;
	
	@Autowired
	ShippingRepository shippingRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	FavoritesRepository favoritesRepository;
    @PostMapping("/addfav")
    public JsonResult addfav(@RequestParam MultiValueMap<String,Object> fav) throws Exception{
    	
    	Long uid=Long.valueOf(fav.getFirst("uid").toString());
    	Long pid=Long.valueOf(fav.getFirst("produceid").toString());
    	Customer c=Repository.getById(uid);
    	Set <Favorites> fset=c.getFavorites();
    	
    	for(Favorites f:fset){ Integer fid=f.getProductId();
    		if(fid.compareTo(pid.intValue())==0) {
    			favoritesRepository.delete(f);
    			return new JsonResult("Add To Favorites");}}
		
    	Favorites f=new Favorites();
    	f.setCustomer(c);
    	f.setProductId(pid.intValue());
    	favoritesRepository.save(f);
		return new JsonResult("Remove from Favorites");// @RequestBody
	}  
	@PostMapping("/addcart")
    public JsonResult addcart(@RequestParam MultiValueMap<String,Object> cart) throws Exception{
		Productsize psize=null;
		Long uid=Long.valueOf(cart.getFirst("uid").toString());
		Long pid=Long.valueOf(cart.getFirst("Productid").toString());
		int num=Integer.valueOf(cart.getFirst("number").toString());
		int left=0;
		String size=cart.getFirst("size").toString();
		Products p=productRepository.getById(pid);
		Set<Productsize> psSet=p.getProductsizes();
		for(Productsize ps :psSet) {if(ps.getSize().equals(size)){psize=ps;left+=(ps.getLeft()-num);}}  
		Customer c=Repository.getById(uid);
		Set<Order> oset =c.getOrders();
		for(Order o :oset){if(o.getProductsize().getId()==psize.getId() && o.getState().equals("cart")){o.setAmount(o.getAmount()+num);orderRepository.save(o);return new JsonResult(left,"");}}
		Order od=new Order();
		od.setCoins(0);
		od.setCouponid(0l);
		od.setCreateTime(null);
		od.setCreateTime(null);
		od.setState("cart");
		od.setAmount(num);
		od.setCustomer(c);
		od.setProductprice(p.getProductprice());
		od.setProductsize(psize);
        od.setAmount(num);
        orderRepository.save(od);
		return new JsonResult(left,"");
	}
	
	@PostMapping("/exrate")
    public JsonResult exrate(@RequestParam MultiValueMap<String,Object> exrate,HttpSession session) throws Exception{
	    Map<String,Double> exmap=exrateService.getexrate();
	    if(session.getAttribute("usd")==null) {session.setAttribute("usd", exmap.get("USDTWD"));}
	    if(session.getAttribute("jpy")==null) {session.setAttribute("jpy", exmap.get("USDTWD")/exmap.get("USDJPY"));}
	    if(session.getAttribute("cny")==null) {session.setAttribute("cny", exmap.get("USDTWD")/exmap.get("USDCNY"));}
		String rate=exrate.getFirst("rate").toString();
		if(rate.startsWith("USD")) {session.setAttribute("currentex",session.getAttribute("usd"));session.setAttribute("currentexR","USD");}
		if(rate.startsWith("JPY")) {session.setAttribute("currentex",session.getAttribute("jpy"));session.setAttribute("currentexR","JPY");}
		if(rate.startsWith("CNY")) {session.setAttribute("currentex",session.getAttribute("cny"));session.setAttribute("currentexR","CNY");}
		if(rate.startsWith("TWD")) {session.setAttribute("currentex",1.0);session.setAttribute("currentexR","TWD");}
        return new JsonResult(1,"success");
	}
	@PostMapping("/getcart")
    public JsonResult getcart(HttpSession session) throws Exception{
		Long uid=-1l;
		if(session.getAttribute("uid")!=null)
	   {uid =Long.valueOf(session.getAttribute("uid").toString());}
	   ArrayList<String> sarr=new ArrayList();
	   if(uid<0l || session.getAttribute("uid")==null) { 
		   switch(session.getAttribute("language").toString()) {
		   case "tw":
			   sarr.add("請先登入");
           	break; 
		   case "cn":
			   sarr.add("请先登入");
	           	break; 
		   case "en":
			   sarr.add("please login first");
	           	break; 
		   
		   }return new JsonResult(1,"success",sarr.toArray(new String[0]));
	   }
	
	Set<Order> osett=Repository.getById(uid).getOrders();
    ArrayList<Order> oset = new ArrayList<>();
    for (Order o:osett) {if(o.getState().equals("cart")) {oset.add(o);}} 
	  if(oset.size()==0) { 
		   switch(session.getAttribute("language").toString()) {
		   case "tw":
			   sarr.add("空空如也");
          	break; 
		   case "cn":
			   sarr.add("空空如也");
	           	break; 
		   case "en":
			   sarr.add("Empty cart");
	           	break; 
		   
		   }return new JsonResult(1,"success",sarr.toArray(new String[0]));
	}
	
	  for(Order o:oset){sarr.add(o.getProductsize().getProducts().getProductName()+" "+o.getProductsize().getSize()+"*"+o.getAmount());}
	   return new JsonResult(1,"success",sarr.toArray(new String[0]));
	}
	@PostMapping("/shopincart")
    public JsonResult shopincart(@RequestParam MultiValueMap<String,Object> valu) throws Exception{
		Long l=Long.valueOf(valu.getFirst("val").toString());  
		String s=valu.getFirst("option").toString();
		Order o=orderRepository.getById(l);
		switch(s)
		{   
		  case "add":
			  o.setAmount(o.getAmount()+1);
			  orderRepository.save(o);
         	    break; 
		   case "minus":
			   o.setAmount(o.getAmount()-1);
			   orderRepository.save(o);
	           	break; 
		   case "del":
			   orderRepository.deleteById(l);
	           	break; }
				
      return new JsonResult(1,"success");
		
	}
	@PostMapping("/sendorder")
    public JsonResult sendorder(@RequestParam MultiValueMap<String,Object> orderz ) throws Exception{
	String orderid =orderz.getFirst("orderid").toString();  System.out.println(orderid);    String[] orderidArr=orderid.split(";");
	String numz =orderz.getFirst("numz").toString();     System.out.println(numz);    String[] numzArr=numz.split(";");
	String total =orderz.getFirst("total").toString();    System.out.println(total);  int Total=Integer.valueOf(total);
	String coinz =orderz.getFirst("coinz").toString();    System.out.println(coinz);  int coin =Integer.valueOf(coinz);
	String fee =orderz.getFirst("fee").toString();    System.out.println(fee);  String[] feeArr=fee.split(";");
	for(int n=0;n<Integer.valueOf(Total);n++){
	if(Integer.valueOf(numzArr[n])<=0) {break;}
	 int orderfee=0;
	Order o	= orderRepository.getById(Long.valueOf(orderidArr[n]));
	int totalprice=o.getProductsize().getProducts().getProductprice()*Integer.valueOf(numzArr[n]);
	String[] feeArr2 =feeArr[n].split(",");  
	if(Integer.valueOf(feeArr2[2]) <0 || Integer.valueOf(feeArr2[2])>totalprice){
    totalprice += Integer.valueOf(feeArr2[1]);}
	int minuscoins=0;	
	while(coin >0){if(totalprice==0){break;} coin--;totalprice--;minuscoins++;}
	o.setFee(orderfee);
	o.setState("create");
	o.setShipping(shippingRepository.getById(Long.valueOf(feeArr2[n])).getHowto());
	o.setCoins(minuscoins);
	o.setProductprice(totalprice);
	o.setAmount(Integer.valueOf(numzArr[n]));
	o.setCreateTime(new Date());
	orderRepository.save(o);
	}	
		 return new JsonResult(1,"success");
	}
	public JsonResult exlan(@RequestParam MultiValueMap<String,Object> exlan ,HttpSession session) throws Exception{
	    Map<String,Double> exmap=exrateService.getexrate();
	    
		String rate=exlan.getFirst("lan").toString();
		switch(rate)
		{   
		  case "en":
			  session.setAttribute("language","en");    
         	    break; 
		   case "tw":
			   session.setAttribute("language","tw");    
	           	break; 
		   case "cn":
			   session.setAttribute("language","cn");    ;
	           	break; }
        return new JsonResult(1,"success");
	}














}

