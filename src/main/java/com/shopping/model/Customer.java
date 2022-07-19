package com.shopping.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "customer" )
@DynamicInsert( true )
 public  class Customer implements Serializable{

    private  static  final  long serialVersionUID = 1L ;

    @Id
    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    
    @Column(name = "name" )
    private String name="";
    
    
    
    @Column(name = "nickname" )
    private String nickname;
    
	@Column(name = "extension" )
    private String extension;
    
    public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Boolean getHasImg() {
		return hasImg;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

    @Column(name = "password" )
    private String password;
    
    @Column(name = "email" )
    private String email;
    
    @Column(name = "backupemail" )
    private String backupemail="";
    
    @Column(name = "address" )
    private String address="none";
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBackupemail() {
		return backupemail;
	}

	public void setBackupemail(String backupemail) {
		this.backupemail = backupemail;
	}



	@Column(name = "phonenumber" )
    private String phonenumber;
    

    

	
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	@Column(name = "birthday" )
    private Date birthday;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game" )
    private Game game;
    
    public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Boolean getSeller() {
		return seller;
	}

	public void setSeller(Boolean seller) {
		this.seller = seller;
	}

	
	
    public Set<Coins> getCoins() {
		return coins;
	}

	public void setCoins(Set<Coins> coins) {
		this.coins = coins;
	}

	public Set<Coupons> getCoupons() {
		return coupons;
	}

	public void setCoupons(Set<Coupons> coupons) {
		this.coupons = coupons;
	}

	public Set<Favorites> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<Favorites> favorites) {
		this.favorites = favorites;
	}

	public Integer getPicnum() {
		return picnum;
	}

	public void setPicnum(Integer picnum) {
		this.picnum = picnum;
	}

	@Column(name = "picnum" )
    private  Integer picnum=1;

	@Column(name = "seller" )
    private Boolean seller=false;


    @OneToMany(mappedBy = "customer",fetch= FetchType.LAZY)
     private Set <Coupons> coupons;
    
    @OneToMany(mappedBy = "customer",fetch= FetchType.LAZY)
    private Set <Favorites> favorites;
    
    @OneToMany(mappedBy = "customer",fetch= FetchType.LAZY)
    private Set <Order> orders;
    
    @OneToMany(mappedBy = "customer",fetch= FetchType.LAZY)
    private Set <Messages> messages;
    
    @OneToMany(mappedBy = "customer",fetch= FetchType.LAZY)
    private Set <Coins> coins;
    
    @Column(name = "hasimg" )
    private Boolean  hasImg=false;
   

  
  
  
    public Boolean isHasImg() {
		return hasImg;
	}

	public void setHasImg(Boolean hasImg) {
		this.hasImg = hasImg;
	}

	public Set<Messages> getMessages() {
		return messages;
	}

	public void setMessages(Set<Messages> messages) {
		this.messages = messages;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public  long getId() {
         return id;
    }

    public  void setId( long id) {
         this.id = id;
    }

    public String getName() {
         return name;
    }

    public  void setName(String name) {
         this.name = name;
    }
    
}