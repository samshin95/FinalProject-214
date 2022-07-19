package com.shopping.model;

import java.io.Serializable;
import java.sql.Date;

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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "coins" )
@DynamicInsert( true )
@EntityListeners(AuditingEntityListener.class)
 public  class Coins implements Serializable{

    private  static  final  long serialVersionUID = 1L ;
    /**
     * 主鍵
     */
    @Id
    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private  long id;
    
    @Column(name = "plusorminus" )
     private  int plusorminus;
    //更新时间
    @CreatedDate
    @Column(name = "createTime")
    private Date createTime;
    
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "customer")
    private Customer customer;

	public int getPlusorminus() {
		return plusorminus;
	}

	public void setPlusorminus(int plusorminus) {
		this.plusorminus = plusorminus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    
}