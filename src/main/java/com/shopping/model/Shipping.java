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
@Table(name = "shipping" )
@DynamicInsert( true )
 public  class Shipping implements Serializable{

	private static final long serialVersionUID = 1L;
	
	      @Id
	      @Column(name = "id" )
	      @GeneratedValue(strategy = GenerationType.IDENTITY)
	      private  long id;
	      
	      @Column(name = "howto" )
	      private String howto;
	      
	      public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getHowto() {
			return howto;
		}

		public void setHowto(String howto) {
			this.howto = howto;
		}

		public int getFee() {
			return fee;
		}

		public void setFee(int fee) {
			this.fee = fee;
		}

		public Products getProducts() {
			return products;
		}

		public void setProducts(Products products) {
			this.products = products;
		}

		@Column(name = "fee" )
	      private int fee;
	      
	      @ManyToOne (fetch= FetchType.LAZY)
	      @JoinColumn(name = "products")
	      private Products products;
	
}


