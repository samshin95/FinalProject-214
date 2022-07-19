package com.shopping.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;


@Entity
@Table(name = "coupon" )
@DynamicInsert( true )
 public  class Coupon implements Serializable{

    private  static  final  long serialVersionUID = 1L ;

    //主鍵
   
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	@Id
    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private  long id;
    
   
    
    @Column(name = "category" )
    private  String category;
    
    @Column(name = "discount" )
    private  float discount;
    
    @Column(name = "description" )
    private  String description;
    
    @OneToMany(mappedBy = "customer",fetch= FetchType.LAZY)
    private Set <Coupons> coupons;

}