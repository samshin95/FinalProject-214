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
@Table(name = "seller" )
@DynamicInsert( true )
public class Seller implements Serializable{
	
	private  static  final  long serialVersionUID = 1L ;
	@Id
    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    
    @Column(name = "customerId" )
     private long customerId;
    
    @Column(name = "introduce" )
    private String introduce="";
    
    public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Boolean getHasImg() {
		return hasImg;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "extension" )
    private String extension;
    
    public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Set<Products> getProducts() {
		return products;
	}
	
    @Column(name = "hasimg" )
    private Boolean  hasImg=false;
   
    public Boolean isHasImg() {
		return hasImg;
	}

	public void setHasImg(Boolean hasImg) {
		this.hasImg = hasImg;
	}

	public void setProducts(Set<Products> products) {
		this.products = products;
	}

    
    @OneToMany(mappedBy = "seller",fetch= FetchType.LAZY)
    private Set <Products> products;
    
	public Integer getPicnum() {
		return picnum;
	}

	public void setPicnum(Integer picnum) {
		this.picnum = picnum;
	}

	@Column(name = "picnum" )
    private  Integer picnum=-1;
   
}
