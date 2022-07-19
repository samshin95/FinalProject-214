package com.shopping.controller;

/*<option value="men_top">男性上衣</option>
<option value="women_top">女性上衣</option>
<option value="pants">褲子</option>
<option value="dress">裙子</option>
<option value="men_shoe">男鞋</option>
<option value="women_shoe">女鞋</option>
<option value="hat">帽子</option>
<option value="bag">背包</option>*/

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.model.Coins;
import com.shopping.model.Customer;
import com.shopping.model.Favorites;
import com.shopping.model.Order;
import com.shopping.model.Products;
import com.shopping.model.Productsize;
import com.shopping.model.Seller;
import com.shopping.model.Shipping;
import com.shopping.repository.CouponRepository;
import com.shopping.repository.CustomerRepository;
import com.shopping.repository.OrderRepository;
import com.shopping.repository.ProductsRepository;
import com.shopping.repository.SellerRepository;
import com.shopping.repository.ShippingRepository;
import com.shopping.service.ExrateService;
import com.shopping.service.FavoritesService;
import com.shopping.service.GetsoldService;
import com.shopping.service.TranslateService;
@Controller
public class MainController {
	@Autowired
	CustomerRepository Repository;
	
	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	ProductsRepository productsRepository;
	
	@Autowired
	CouponRepository couponRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ShippingRepository shippingRepository;
	
	@Autowired
	TranslateService translateService;
	
	@Autowired
	FavoritesService favoritesService;
	
	@Autowired
	GetsoldService getsoldService;
	
	@Autowired
	ExrateService exrateService;
	
	@Autowired
	DataSource dataSource;
@RequestMapping("/")
public String index(Model model,HttpSession session)        
	    { session.setAttribute("language","tw");              
          if(session.getAttribute("currentex")==null) {session.setAttribute("currentex",1.0);}
          session.setAttribute("currentexR","TWD");
	      int showsize=4;
	      List<Products> plist=productsRepository.findByOrderByCreateTime();Collections.reverse(plist);
	      
	      List<Products> plist1=new ArrayList<Products>();
	      List<Products> plist2=new ArrayList<Products>();
	      List<Products> plist3=new ArrayList<Products>();
	      List<Products> plist4=new ArrayList<Products>();
	      List<Products> plist5=new ArrayList<Products>();
	      List<Products> plist6=new ArrayList<Products>();
	      List<Products> plist7=new ArrayList<Products>();
	      List<Products> plist8=new ArrayList<Products>();
	      for(Products p:plist) {if(p.getCategory().equals("men_top")) {plist1.add(p);}if(plist1.size()==showsize) {break;}} for(Products p:plist1) {System.out.println(p.getCreateTime());}
	      for(Products p:plist) {if(p.getCategory().equals("women_top")) {plist2.add(p);}if(plist2.size()==showsize) {break;}}
	      for(Products p:plist) {if(p.getCategory().equals("pants")) {plist3.add(p);}if(plist3.size()==showsize) {break;}}
	      for(Products p:plist) {if(p.getCategory().equals("dress")) {plist4.add(p);}if(plist4.size()==showsize) {break;}}
	      for(Products p:plist) {if(p.getCategory().equals("men_shoe")) {plist5.add(p);}if(plist5.size()==showsize) {break;}}
	      for(Products p:plist) {if(p.getCategory().equals("women_shoe")) {plist6.add(p);}if(plist6.size()==showsize) {break;}}
	      for(Products p:plist) {if(p.getCategory().equals("hat")) {plist7.add(p);}if(plist7.size()==showsize) {break;}}
	      for(Products p:plist) {if(p.getCategory().equals("bag")){plist8.add(p);}if(plist8.size()==showsize) {break;}}
	      model.addAttribute("plist1",plist1);model.addAttribute("plist2",plist2);model.addAttribute("plist3", plist3);model.addAttribute("plist4",plist4);
	      model.addAttribute("plist5",plist5);model.addAttribute("plist6",plist6);model.addAttribute("plist7",plist7);model.addAttribute("plist8",plist8);
	     Long uid=-1l;
	     if (session.getAttribute("uid")!=null) {uid=(Long) session.getAttribute("uid");}
	     if(uid>0) {
	     boolean[] list1=favoritesService.getfavorites(plist1, uid); boolean[] list5=favoritesService.getfavorites(plist5, uid);
	     boolean[] list2=favoritesService.getfavorites(plist2, uid); boolean[] list6=favoritesService.getfavorites(plist6, uid);
	     boolean[] list3=favoritesService.getfavorites(plist3, uid); boolean[] list7=favoritesService.getfavorites(plist7, uid);
	     boolean[] list4=favoritesService.getfavorites(plist4, uid); boolean[] list8=favoritesService.getfavorites(plist8, uid);
	     boolean[] lists1=favoritesService.getseller(plist1, uid); boolean[] lists2=favoritesService.getseller(plist2, uid);
	     boolean[] lists3=favoritesService.getseller(plist3, uid); boolean[] lists4=favoritesService.getseller(plist4, uid);
	     boolean[] lists5=favoritesService.getseller(plist5, uid); boolean[] lists6=favoritesService.getseller(plist6, uid);
	     boolean[] lists7=favoritesService.getseller(plist7, uid); boolean[] lists8=favoritesService.getseller(plist8, uid);
	     
	     model.addAttribute("list1",list1);model.addAttribute("list2",list2);model.addAttribute("list3", list3);model.addAttribute("list4",list4);
	     model.addAttribute("list5",list5);model.addAttribute("list6",list6);model.addAttribute("list7",list7);model.addAttribute("list8",list8);
	     model.addAttribute("lists1",lists1);model.addAttribute("lists2",lists2);model.addAttribute("lists3", list3);model.addAttribute("lists4",lists4);
	     model.addAttribute("lists5",lists5);model.addAttribute("lists6",lists6);model.addAttribute("lists7",lists7);model.addAttribute("lists8",lists8);
	     Customer customer =Repository.getById(uid);   
	     String name=customer.getName();
	     if(name.length()>7) {name=name.substring(0, 6)+"...";}    
		session.setAttribute("indexname", name);
		
	        return "index";}
	     model.addAttribute("name","none");
		 model.addAttribute("uid",uid); 
		 session.setAttribute("uid", -1l);
	        return "index";
	        }
@RequestMapping("/single_product")
public String singleproduct(@RequestParam(value="productid") String productid, Model model,HttpSession session) 
	    {
	    boolean isseller=false;Long l=-1l;
	    if(session.getAttribute("uid")!=null)
	    {l=Long.valueOf(session.getAttribute("uid").toString());}
	    boolean isfav=false;
	    if(l>0) {Customer c=Repository.getById(l);Set<Favorites> fset=c.getFavorites();for(Favorites f:fset){if(f.getCustomer().getId()==l){isfav=true;break;}}}
	    Long productId=Long.valueOf(productid);
	    Products p = productsRepository.getById(productId);
	    if(p.getSeller().getCustomerId().equals(l)){isseller=true;}
	    Set <Productsize> pset=p.getProductsizes();
	    for(Productsize ps:pset) {Set <Order> oset =ps.getOrders();
	    for(Order o:oset) {if(o.getCustomer().getId()==l){int Left=ps.getLeft()-o.getAmount();if(Left<0) {Left=0;}
	    ps.setLeft(Left);}}}
	    int sold=0;for(Productsize ps:pset) {sold+=ps.getSold();}
        List<Shipping> s= shippingRepository.findByProducts(p);
        String[] st=new String[s.size()];
        int[] in=new int[s.size()];
        for(int num=0;num<s.size();num++){st[num]=s.get(num).getHowto();in[num]=s.get(num).getFee();}
        int max=Arrays.stream(in).max().getAsInt();
        int min=Arrays.stream(in).min().getAsInt();
        if(max==min) {max=-1;}
        model.addAttribute("isfav", isfav);
        model.addAttribute("sold", sold);
        model.addAttribute("max", max);
        model.addAttribute("min", min);
        model.addAttribute("product", p);
	    model.addAttribute("productsizes", pset);
	    model.addAttribute("fee", translateService.fee("tw", 1.0,st, in));
	    model.addAttribute("isseller", isseller );
	    model.addAttribute("uid", l );
	    model.addAttribute("seller", p.getSeller() );
        return "single_product";
	        }
@RequestMapping("/forget_password")
public String forgetpassword(@RequestParam(value="title", required=false, defaultValue="xiao") String title, Model model) 
	    {
	        return "forget_password";
	        }
@RequestMapping("/person-buy")
public String personbuy(@RequestParam(value="title", required=false, defaultValue="xiao") String title, Model model) 
	    {
	        return "person-buy";
	        }
@RequestMapping("/person-memberdata")
public String memberdata(HttpSession session, Model model) 
	    {   if(session.getAttribute("uid")==null|| (long)session.getAttribute("uid")<=0) {return "redirect:/";}
	        long uid=(long)session.getAttribute("uid");
	        Customer customer =Repository.getById(uid);
	        if(customer.getBirthday()!=null) {String pattern = "yyyy-MM-dd";
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	        String date = simpleDateFormat.format(customer.getBirthday());
	        session.setAttribute("date", date);} else {session.setAttribute("date", "");}////bad
	        session.setAttribute("isSeller",customer.getSeller());
	        session.setAttribute("nickname",customer.getNickname());
	        session.setAttribute("email",customer.getEmail());
	        session.setAttribute("name",customer.getName());
	        session.setAttribute("phonenumber",customer.getPhonenumber());
	        session.setAttribute("backupemail",customer.getBackupemail());
	        session.setAttribute("address",customer.getAddress());
	        session.setAttribute("phonenumber",customer.getPhonenumber());               ////bad
	        return "person-memberdata";
	        }
@RequestMapping("/person-sell")
public String personsell(HttpSession session, Model model) 
	    {   List<Seller> list1=sellerRepository.findByCustomerId((long)session.getAttribute("uid"));
	        if(list1.isEmpty()) { return "redirect:/";}
	        Seller s=list1.get(0);
	        model.addAttribute("seller",s);
	        model.addAttribute("intro",s.getIntroduce());
	        return "person-sell"; }
@RequestMapping("/register")
public String register(Model model) 
	    {
	        return "register";
	        }
@RequestMapping("/shopping_cart")
public String shopping_cart(HttpSession session, Model model) 
	    {   
	 Long l=-1l;
     if(session.getAttribute("uid")!=null )
	    {l=Long.valueOf(session.getAttribute("uid").toString());}
	    else {return "redirect:/";}
     if(l<=0 ) {return "redirect:/";}
     Set<Order> oset=Repository.getById(l).getOrders();
     ArrayList<Order> oarr = new ArrayList<>();
     for (Order o:oset) {if(o.getState().equals("cart")) {oarr.add(o);}} 
     ArrayList<Productsize> psarr = new ArrayList<>();ArrayList<Products> parr = new ArrayList<>();
     if(oarr.size()==0) { System.out.print("shopping cart is empty!"); return "shopping_cart";}//////////////////////test
     for(Order o:oarr){ if(o.getProductsize().getLeft()<o.getAmount()){o.setAmount(o.getProductsize().getLeft());orderRepository.save(o);}}
     Set<Coins> coinset =Repository.getById(l).getCoins();
     double coins=0.0;int coin=0;
     for (Coins c: coinset) {coins+=c.getPlusorminus();}
     if(coins>0.0){coin=(int)(Math.floor(coins)); } coin+=10;
     for(Order o:oarr) {parr.add(o.getProductsize().getProducts());}///
     for(Order o:oarr) {psarr.add(o.getProductsize());}
     model.addAttribute("shiparr", getsoldService.getshipping1(parr));
     model.addAttribute("sarr", getsoldService.getshipping2(parr));
     model.addAttribute("oarr",oarr);System.out.println("oarr size ="+oarr.size());
     model.addAttribute("psarr", psarr);System.out.println("psarr size ="+psarr.size());
     model.addAttribute("parr", parr);System.out.println("parr size ="+parr.size());
     model.addAttribute("coin", coin);System.out.println("coin ="+coin);/////////////////test/////
     return "shopping_cart";
	        }
@RequestMapping("/sign_in")
public String sign_in(@RequestParam(value="title", required=false, defaultValue="xiao") String title, Model model) 
	    {
	        return "sign_in";
	        }
@RequestMapping("/sell_management") 
public String sell_management(Model model,HttpSession session) 
	    {if(session.getAttribute("uid")==null) {return "redirect:/";}
	    List<Seller> list1=sellerRepository.findByCustomerId((long)session.getAttribute("uid"));
        if(list1.isEmpty()) { return "redirect:/";}
        Seller s=list1.get(0);model.addAttribute("seller",s);
        List<Products> list2 =productsRepository.findBySeller(s);
        model.addAttribute("productlist", list2);
	    return "sell_management";
	        }
@RequestMapping("/sell_management_alter")  
public String sell_management_alter(@RequestParam(value="productid") int productinput,
		Model model,HttpSession session) 
	    { long productid=productinput; //test
	    if(session.getAttribute("uid")==null) {return "redirect:/";}
	    List<Seller> list1=sellerRepository.findByCustomerId((long)session.getAttribute("uid"));
        if(list1.isEmpty()) {return "redirect:/";}
        Seller s=list1.get(0);model.addAttribute("seller",s); 
        Set <Products> productset=s.getProducts();
        if(productset.isEmpty()) {System.out.println("no productset!");}    //test
        boolean ifcontain=false;
        for(Products p:productset) {  //test
        	if(p.getId()==productid) {ifcontain=true;}}
        if(ifcontain)
	    { Products passp=productsRepository.getById(productid);
	    model.addAttribute("alterp", passp);
        Set <Productsize> productsizeset= passp.getProductsizes();
        int z1=productsizeset.size()/3;
        if(productsizeset.size()%3!=0) {z1++;}
        int z2=productsizeset.size()/3;
        if(productsizeset.size()%3==2) {z2++;}
        int z3=productsizeset.size()/3;
       
        List<Productsize> productsizeList = new ArrayList<>(productsizeset);
        String[] productsizez1=new String[z1];
        String[] productsizez2=new String[z2];
        String[] productsizez3=new String[z3];
        int[] productsizeznumz1=new int[z1];
        int[] productsizeznumz2=new int[z2];
        int[] productsizeznumz3=new int[z3];
	    for(int n=0;n<productsizeset.size();n++) {
	    	 switch(n%3) { 
	            case 0:
	            	productsizez1[n/3]=productsizeList.get(n).getSize();
	            	productsizeznumz1[n/3]=productsizeList.get(n).getLeft();
	            	break; 
	            case 1: 
	            	productsizez2[n/3]=productsizeList.get(n).getSize();
	            	productsizeznumz2[n/3]=productsizeList.get(n).getLeft();
	                break; 
	            case 2: 
	            	productsizez3[n/3]=productsizeList.get(n).getSize();
	            	productsizeznumz3[n/3]=productsizeList.get(n).getLeft();
	                break; 
	            default: 
	                System.out.println("error1");
	              }
	    	
	    }
	    Set <Shipping> shippingset=passp.getShipping();
	    List<Shipping> shippingList = new ArrayList<>(shippingset);
	    boolean[] shippingg= new boolean[7];
	    for(int n=0;n<shippingList.size();n++) {shippingg[n]=false;}
	    for(int n=0;n<shippingList.size();n++) {
	    	 switch(shippingList.get(n).getHowto()) { 
	            case "seven":
	            	shippingg[0]=true;
	            	break; 
	            case "family":
	            	shippingg[1]=true;
	            	break; 
	            case "hi-life":
	            	shippingg[2]=true;
	            	break; 
	            case "ok":
	            	shippingg[3]=true;
	            	break; 
	            case "post":
	            	shippingg[4]=true;
	            	break; 
	            case "cat":
	            	shippingg[5]=true;
	            	break; 
	            default: 
	            System.out.println("error!");
	              }
	    	
	    }
	    model.addAttribute("info",shippingg);
	    model.addAttribute("productid",passp.getId());
	    model.addAttribute("sizez1",productsizez1);for(String ss:productsizez1) {System.out.println(ss);}
	    model.addAttribute("sizez2",productsizez2);for(String ss:productsizez2) {System.out.println(ss);}
	    model.addAttribute("sizez3",productsizez3);for(String ss:productsizez3) {System.out.println(ss);}
	    model.addAttribute("numz1",productsizeznumz1);for(int ss:productsizeznumz1) {System.out.println(ss);}
	    model.addAttribute("numz2",productsizeznumz2);for(int ss:productsizeznumz2) {System.out.println(ss);}
	    model.addAttribute("numz3",productsizeznumz3);for(int ss:productsizeznumz3) {System.out.println(ss);}
	    model.addAttribute("category",passp.getCategory());
	    model.addAttribute("name",passp.getProductName());
	    model.addAttribute("text",passp.getDescription());
	    model.addAttribute("price",passp.getProductprice());
	    model.addAttribute("hasImg",passp.isHasImg());
	    model.addAttribute("extension",passp.getExtension());
	    model.addAttribute("picnum",passp.getPicnum());
	    model.addAttribute("productid",productinput);
	    
        	return "sell_management_alter";}
        return "redirect:/";}

@RequestMapping("/shopping-finishorder")
public String finishorder(Model model,HttpSession session) 
	    {
	if(session.getAttribute("uid")==null || Long.valueOf(session.getAttribute("uid").toString())<-1l) {return "redirect:/";}
    Customer c=Repository.getById( Long.valueOf(session.getAttribute("uid").toString()));
     Set <Order> osett =c.getOrders();
 	ArrayList<Productsize> psarr = new ArrayList<>();ArrayList<Products> parr = new ArrayList<>();ArrayList<Order> oset = new ArrayList<>();
	for ( Order o :osett) {if(o.getState().equals("create") ) {oset.add(o);}}
	for ( Order o :oset) {
		Date d=new Date();
		  Calendar cal = Calendar.getInstance();
	        cal.setTime(d);
	        cal.add(Calendar.DATE, 1);
	        Date dt = cal.getTime();
		o.setShippingStartTime( dt);
		 cal.setTime(dt);
	        cal.add(Calendar.DATE, 1);
	        Date dt2 = cal.getTime();
		o.setShippingFinishTime(dt2);
		 cal.setTime(dt2);
	        cal.add(Calendar.DATE, 1);
	        Date dt3 = cal.getTime();
		o.setOrderFinishTime(dt3);   System.out.println(dt.toString()+" "+dt2.toString()+" "+dt3.toString());
		
		o.setState("finish");
		orderRepository.save(o);
		}
	for(Order o:oset) {parr.add(o.getProductsize().getProducts());}
    for(Order o:oset) {psarr.add(o.getProductsize());}

	model.addAttribute("oarr",oset);System.out.println("oset size ="+oset.size());
    model.addAttribute("psarr", psarr);System.out.println("psarr size ="+psarr.size());
    model.addAttribute("parr", parr);System.out.println("parr size ="+parr.size());
	    return "shopping-finishorder";
	        }
@RequestMapping("/person_collect")
public String person_collect(Model model,HttpSession session) 
	    {if(session.getAttribute("uid")==null ) {return "redirect:/";}
	    if(Integer.valueOf(session.getAttribute("uid").toString())<1) {return "redirect:/";}
	    long uid=(long)session.getAttribute("uid");
        Customer customer =Repository.getById(uid);
	    Set<Favorites> favset=customer.getFavorites();
        List<Products> plist= new ArrayList<>();
        List<String> plist2= new ArrayList<>();
        for(Favorites f:favset)
        {Products p=productsRepository.getById(Long.valueOf(f.getProductId()));
        plist.add(p);System.out.print(p.getProductName());String name=Repository.getById(p.getSeller().getCustomerId()).getName();
        plist2.add(name);
        }
        model.addAttribute("plist", plist);
        model.addAttribute("plist2", plist2);
	    return "person_collect";
	        }

@RequestMapping("/contact")
public String contact() {
	return "contact";
}
@RequestMapping("/person-memberdata-game")
public String ajax2() {
	return "person-memberdata-game";
}

}

