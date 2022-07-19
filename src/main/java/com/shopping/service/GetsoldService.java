package com.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.shopping.model.Products;
import com.shopping.model.Productsize;
import com.shopping.model.Shipping;

@Service
public class GetsoldService {
	
	public int getsold(Products p)
	{int n=0;
	Set <Productsize> pset =p.getProductsizes();
	for(Productsize ps:pset) {n+=ps.getSold();}
	return n;
	}
	public int getleft(Products p)
	{int n=0;
	Set <Productsize> pset =p.getProductsizes();
	for(Productsize ps:pset) {n+=ps.getLeft();}
	return n;
	}
	public List getshipping1(List <Products> p)
	{
		ArrayList<ArrayList<Shipping>> arr = new ArrayList<>();
	for(int n=0;n<p.size();n++) { 
		ArrayList<Shipping> sarr = new ArrayList<>();
		Products pro=p.get(n); Set<Shipping> s= pro.getShipping(); for(Shipping ship:s) {sarr.add(ship);
		arr.add(sarr);
		
		
		}
		}
		return arr;
	}
	public List getshipping2(List <Products> p)
	{
		ArrayList<ArrayList<ArrayList<String>>> arr = new ArrayList<>();
	for(int n=0;n<p.size();n++) { 
		ArrayList<ArrayList<String>> sarr =new ArrayList<>();
		Products pro=p.get(n); Set<Shipping> s= pro.getShipping(); 
		for(Shipping ship:s){ArrayList<String> sarr2 =new ArrayList<>();
		  switch(ship.getHowto()) { 
          case "post":
          sarr2.add(String.valueOf(ship.getId())); sarr2.add(String.valueOf(ship.getFee())); sarr2.add("-1");sarr2.add("郵局:運費80");
          	break; 
          case "cat": 
          sarr2.add(String.valueOf(ship.getId())); sarr2.add(String.valueOf(ship.getFee())); sarr2.add("-1");sarr2.add("黑貓:運費90");
              break; 
          case "ok": 
          sarr2.add(String.valueOf(ship.getId()));sarr2.add(String.valueOf(ship.getFee())); sarr2.add("99");sarr2.add("OK便利商店:運費45(滿99免運)");
              break; 
          case "hi-life": 
          sarr2.add(String.valueOf(ship.getId()));sarr2.add(String.valueOf(ship.getFee()));  sarr2.add("199");sarr2.add("萊爾富:運費50(滿199免運)");
              break; 
          case "seven": 
          sarr2.add(String.valueOf(ship.getId()));sarr2.add(String.valueOf(ship.getFee()));  sarr2.add("1000");sarr2.add("7-11:運費60(滿1000免運)");     
              break; 
          case "family": 
          sarr2.add(String.valueOf(ship.getId()));sarr2.add(String.valueOf(ship.getFee()));  sarr2.add("1000");sarr2.add("全家:運費60(滿1000免運)");
              break; 
          default: 
              System.out.println("no shipping error");
            }sarr.add(sarr2);
		}
		arr.add(sarr);
		
		}
	
	return arr;
		}
		
	
}
