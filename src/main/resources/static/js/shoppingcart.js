$(function() {
	$(".product-add").click(function(){      var num1=$(this).closest('.product-box').find('.pnumb').val(); 
var	valu ={'val': $(this).closest('.product-box').find('.orderid').val(),'option':'add'};               
		var a = $(this).prev().val(); var b = parseInt(a) + 1; if (b ==  $(this).closest('.product-box').find('.maxnum').val()) { return } $(this).prev().val(b); TotalPrice();
         var num2=$(this).closest('.product-box').find('.pnumb').val();
minusthing();
if(num1!=num2){
	$.ajax({
        		type:"POST",    
        		url:"shopincart",
        		data: valu,
        	    dataType:"json",
        		success:function (result){}
        	}); 
		}
		
		 });
	$(".product-jian").click(function() {
		var num1=$(this).closest('.product-box').find('.pnumb').val();
	var	valu ={'val': $(this).closest('.product-box').find('.orderid').val(),'option':'minus'}; 
		var a = $(this).next().val(); var b = parseInt(a) - 1; if (b == -1) { return } $(this).next().val(b); TotalPrice();
		var num2=$(this).closest('.product-box').find('.pnumb').val();
		addthing();
		if(num1!=num2){	$.ajax({
        		type:"POST",    
        		url:"shopincart",
        		data: valu,
        	    dataType:"json",
        		success:function (result){}
        	}); 
		}
		
		 });

	//減 (a  b=a-1 if b==0 傳回原本value)
	$(".product-ckb").click(function() { $(this).children("em").toggleClass("product-xz"); TotalPrice(); productxz() });
	//check box                                   (移除    em id          用product-xz取代) 觸發 TotalPrice(); productxz()
	$(".product-al").click(function() { var a = $(".product-em"); var b = $(".product-all em"); b.toggleClass("product-all-on"); if ($(this).find(".product-all em").is(".product-all-on")) { a.addClass("product-xz") } else { a.removeClass("product-xz") } TotalPrice(); shuliang() });
	//按下後選取全部product-box表格                                                             var b 移除取代  product-all-on ;    (if         找到的第一個.product-all em  是 .product-all-on   添加一個 product-xz類別       否則 移除所有 a值的 "product-xz" 類別)  觸發TotalPrice(); shuliang()方法
	$(".product-del").click(function() {valu ={'val': $(this).closest('.product-box').find('.orderid').val(),'option':'del'}; 
		 $(this).closest(".product-box").remove(); koncat(); TotalPrice();
		
		if(true){	$.ajax({
        		type:"POST",    
        		url:"shopincart",
        		data: valu,
        	    dataType:"json",
        		success:function (result){}
        	}); 
		}
		 
		  }); TotalPrice(); shuliang(); koncat()
}); //                                   按下後顯示確認刪除視窗                      確認後關閉product-box表格 和 刪除         觸發koncat(); TotalPrice()    觸發TotalPrice(); shuliang(); koncat()方法

function productxz() { var a = $(".product-em"); var b = $(".product-xz"); if (b.length == a.length) { $(".product-all em").addClass("product-all-on") } else { $(".product-all em").removeClass("product-all-on") } shuliang(); TotalPrice() }
//                                                                      (if  b長度==a長度              向product-all em  增加        product-all-on   否則    移除所有 product-all em 的   product-all-on類別)     觸發 shuliang(); TotalPrice()方法           
function TotalPrice() { var a = 0;a-=parseInt($("#coinz").val());

 $(".product-em").each(function() { 
	if ($(this).is(".product-xz")) { 
	var c = parseInt($(this).parents(".product-ckb").siblings().find(".realprice").text()); 
	var d = parseInt($(this).parents(".product-ckb").siblings().find(".product-num").val());
	var numbz=$(this).closest('.product-box').find('.product-fee').val().split(',');   
	
	var b = c * d; a += b;    if((a<numbz[2] || numbz[2]<0 )&& d!=0){a+= parseInt(numbz[1]);}
	}
	}) 
	$(".all-price").text(a.toFixed(2)) 
	 }
//function TotalPrice() {var a = 0; a-=parseInt($("#coinz").val()); $(".product-em").each(function() { if ($(this).is(".product-xz")) { var c = parseInt($(this).parents(".product-ckb").siblings().find(".realprice").text()); var d = parseInt($(this).parents(".product-ckb").siblings().find(".product-num").val()); var b = c * d; a += b } $(".all-price").text(a.toFixed(2)) }) }
//                                輸出每個.product-em             (if (this)is .product-xz )                         查找所有product-ckb父元素、相同類別、搜尋所有price的元素 .設置所有文本               查找所有product-ckb父元素、相同類別、尋找.product-num類別 、設置()的值        b值=c*d;a=a+b                            取a小數第二位的值                                    
function shuliang() {
	$(".product-all-sl").text(""); var a = $(".product-xz").length; $(".product-all-sl").text(a);
	//                  設置所有 product-all-sl 元素的內容 ;  a= product-xz長度      ; 設置所有 product-all-sl 元素    
	if (a > 0) { $(".product-all-qx").text("已選"); $(".all-sl").css("display", "inline-block"); $(".product-sett").removeClass("product-sett-a") }
	//if 顯示已選 (選擇數量)  設置所有 product-all-qx 元素的內容 ;                                   向 product-sett 移除 product-sett-a 類別
	else { $(".product-all-qx").text("全選"); $(".all-sl").css("display", "none"); $(".product-sett").addClass("product-sett-a") }
	//else 顯示全選 (表格數量)  設置所有 product-all-qx元素內容;                         向product-sett增加product-sett-a類別

}
function koncat() {
	var a = $(".product-box").length;
	//          product-box個數
	if (a <= 0) { $(".all-price").text("0.00"); $(".product-all-qx").text("全選"); $(".all-sl").css("display", "none"); $(".product-sett").addClass("product-sett-a"); $(".product-all em").removeClass("product-all-on"); $(".kon-cat").css("display", "block") }
	//   a<=0         所有all-price 設置0.00        所有product-all-qx  設置全選       all-sl 消失                         向product-sett增加product-sett-a類別            向product-all em移除 product-all-on類別              kon-cat 出現                                                                                                                                                     
	else { $(".kon-cat").css("display", "none") }
}   
/*$('select').on('change', function() {
  alert( this.value );
});*/
