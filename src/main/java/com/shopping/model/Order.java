package com.shopping.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "orderr" )
@DynamicInsert( true )
@EntityListeners(AuditingEntityListener.class)
public class Order implements Serializable{

    private  static  final  long serialVersionUID = 1L ;

    @Id
    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    
    @Column(name = "state" )
    private String state;
    
    @Column(name = "amount" )
    private Integer amount; 
    
    public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
    
	@Column(name = "couponid" )
    private Long couponid;
    
    @Column(name = "shipping" )
    private String shipping;
    
    @Column(name = "productprice" )
    private int productprice;
    
    @Column(name = "discount" )
    private int dicsount;
    
    @Column(name = "coins" )
    private int coins;
    
    public Long getCouponid() {
		return couponid;
	}

	public void setCouponid(Long couponid) {
		this.couponid = couponid;
	}

	public int getProductprice() {
		return productprice;
	}

	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}

	public int getDicsount() {
		return dicsount;
	}

	public void setDicsount(int dicsount) {
		this.dicsount = dicsount;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}




	@Column(name = "fee" )
    private int fee;
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}
	
	

	public void setState(String state) {
		this.state = state;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

    public Date getCreateTime() {
		return createTime;
	}

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "customer")
    private Customer customer;
	
	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "productsize")
    private Productsize productsize;
    
    //更新时间
    //@LastModifiedDate
    //@Column(name = "updateTime",nullable = false)
    //private Long updateTime;
    
     // 创建时间
    // @CreatedDate
     //@Column(name = "createTime",updatable = false, nullable = false)
     //private Date createTime;
    
     
     public Productsize getProductsize() {
		return productsize;
	}

	public void setProductsize(Productsize productsize) {
		this.productsize = productsize;
	}

	@Column(name = "createTime")
     private Date createTime;
     
     
     @Column(name = "orderfinishtime",updatable = false)
     private Date orderFinishTime;
     
     @Column(name = "ordercanceltime",updatable = false)
     private Date orderCancelTime;
     
     @Column(name = "shippingfinishtime",updatable = false)
     private Date shippingFinishTime;

     
     
     public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public Date getOrderFinishTime() {
		return orderFinishTime;
	}

	public void setOrderFinishTime(Date orderFinishTime) {
		this.orderFinishTime = orderFinishTime;
	}

	public Date getOrderCancelTime() {
		return orderCancelTime;
	}

	public void setOrderCancelTime(Date orderCancelTime) {
		this.orderCancelTime = orderCancelTime;
	}

	public Date getShippingFinishTime() {
		return shippingFinishTime;
	}

	public void setShippingFinishTime(Date shippingFinishTime) {
		this.shippingFinishTime = shippingFinishTime;
	}

	public Date getShippingStartTime() {
		return shippingStartTime;
	}

	public void setShippingStartTime(Date shippingStartTime) {
		this.shippingStartTime = shippingStartTime;
	}

    @Column(name = "shippingstarttime",updatable = false)
     private Date shippingStartTime;
     
}
