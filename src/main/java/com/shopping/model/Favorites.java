package com.shopping.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "favorites" )
@DynamicInsert( true )
 public  class Favorites implements Serializable{

    private  static  final  long serialVersionUID = 1L ;

    /**
     * 主鍵
     */
    @Id

    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private  long id;
    
    @Column(name = "productid" )
     private  int productId;
    
   
    
    public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "customer")
     private Customer customer;

    
    public  long getId() {
         return id;
    }

    public  void setId( long id) {
         this.id = id;
    }

    
    }

