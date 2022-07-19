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
@Table(name = "coupons" )
@DynamicInsert( true )
 public  class Coupons implements Serializable{

    private  static  final  long serialVersionUID = 1L ;

    /**
     * 主鍵
     */
    @Id

    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private  long id;
    
    @Column(name = "couponId" )
     private  int couponId;
    
    @Column(name = "count" )
    private  int count=0;
    

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "customer")
     private Customer customer;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "coupon")
     private Coupon coupon;
    
    
    
    public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
    public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public  long getId() {
         return id;
    }

    public  void setId( long id) {
         this.id = id;
    }

    
    }

