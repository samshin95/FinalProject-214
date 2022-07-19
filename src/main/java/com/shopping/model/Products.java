package com.shopping.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "products" )
@DynamicInsert( true )
public class Products implements Serializable{

    private  static  final  long serialVersionUID = 1L ;

    @Id
    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    
	@Column(name = "extension" )
    private String extension;
    
    public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public long getId() {
		return id;
	}
    
	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Set<Shipping> getShipping() {
		return shipping;
	}

	public void setShipping(Set<Shipping> shipping) {
		this.shipping = shipping;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<Productsize> getProductsizes() {
		return productsizes;
	}

	public void setProductsizes(Set<Productsize> productsizes) {
		this.productsizes = productsizes;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	@Column(name = "productname" )
     private String productName;
	
	@Column(name = "stars" )
    private Integer stars=0;
	
	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public Integer getRatingP() {
		return ratingP;
	}

	public void setRatingP(Integer ratingP) {
		this.ratingP = ratingP;
	}

	public Boolean getHasImg() {
		return hasImg;
	}

	@Column(name = "ratingP" )
    private Integer ratingP=0;
    
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Set <Shipping> shipping;

	// 创建时间
     @CreatedDate
     @Column(name = "createTime",updatable = false, nullable = false)
     private Date createTime;
     
     @Column(name = "description" )
     private String description;
     
     @Column(name = "category" )
     private String category;
     
     @Column(name = "productprice" )
     private Integer  productprice;
     
     @Column(name = "hasimg" )
     private Boolean  hasImg=false;
    
    public Boolean isHasImg() {
 		return hasImg;
 	}

 	public void setHasImg(Boolean hasImg) {
 		this.hasImg = hasImg;
 	}

	public Integer getProductprice() {
		return productprice;
	}

	public void setProductprice(Integer productprice) {
		this.productprice = productprice;
	}

	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
     private Set <Productsize> productsizes;
     
     @ManyToOne(cascade = {CascadeType.ALL})
     @JoinColumn(name = "seller")
     private Seller seller;
     
 	public Integer getPicnum() {
		return picnum;
	}

	public void setPicnum(Integer picnum) {
		this.picnum = picnum;
	}

	@Column(name = "picnum" )
    private  Integer picnum=-1;
 
}
