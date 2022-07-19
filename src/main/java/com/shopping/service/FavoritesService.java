package com.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.model.Customer;
import com.shopping.model.Favorites;
import com.shopping.model.Products;
import com.shopping.repository.CustomerRepository;
import com.shopping.repository.ProductsRepository;
import com.shopping.repository.SellerRepository;
import com.shopping.repository.ShippingRepository;

@Service
public class FavoritesService {
	@Autowired
	CustomerRepository Repository;
	
	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	ProductsRepository productsRepository;
	
	@Autowired
	ShippingRepository shippingRepository;
	
	public boolean[] getfavorites(List<Products> plist,long uid) {
		Customer c=Repository.getById(uid);
		 Set<Favorites> fset =c.getFavorites();
		 ArrayList<Integer> fid=new ArrayList<Integer>();
		 for(Favorites f:fset) {fid.add(f.getProductId());}
		boolean[] barr = new boolean[plist.size()];
		for (int n = 0; n < barr.length; n++) {barr[n]=false;}
		for (int n = 0; n < plist.size(); n++) {
			Products p = plist.get(n);
			long lon=p.getId();
            if(fid.contains(Integer.valueOf((int)lon))) {barr[n]=true;}   
		}
		return barr;
	}
	
	public boolean[] getseller(List<Products> plist,long uid) {
        
		boolean[] barr = new boolean[plist.size()];
		for (int n = 0; n < barr.length; n++) {barr[n]=false;}
		for (int n = 0; n < barr.length; n++) 
		{ 	
		Products p = plist.get(n);
		long lon=p.getSeller().getCustomerId();
        if(lon ==uid) {barr[n]=true;}
		}

		return barr;
	}
	
}
