$(function(){$(".product-add").click(function(){var a=$(this).prev().val();var b=parseInt(a)+1;if(b==99){return}$(this).prev().val(b);TotalPrice()});$(".product-jian").click(function(){var a=$(this).next().val();var b=parseInt(a)-1;if(b==0){return}$(this).next().val(b);TotalPrice()});$(".product-ckb").click(function(){$(this).children("em").toggleClass("product-xz");TotalPrice();productxz()});$(".product-al").click(function(){var a=$(".product-em");var b=$(".product-all em");b.toggleClass("product-all-on");if($(this).find(".product-all em").is(".product-all-on")){a.addClass("product-xz")}else{a.removeClass("product-xz")}TotalPrice();shuliang()});$(".product-del").click(function(){if(confirm("您确定要删除当前商品？")){$(this).closest(".product-box").remove()}koncat();TotalPrice()});TotalPrice();shuliang();koncat()});function productxz(){var a=$(".product-em");var b=$(".product-xz");if(b.length==a.length){$(".product-all em").addClass("product-all-on")}else{$(".product-all em").removeClass("product-all-on")}shuliang();TotalPrice()}function TotalPrice(){var a=0;$(".product-em").each(function(){if($(this).is(".product-xz")){var c=parseInt($(this).parents(".product-ckb").siblings().find(".price").text());var d=parseInt($(this).parents(".product-ckb").siblings().find(".product-num").val());var b=c*d;a+=b}$(".all-price").text(a.toFixed(2))})}function shuliang(){$(".product-all-sl").text("");var a=$(".product-xz").length;$(".product-all-sl").text(a);if(a>0){$(".product-all-qx").text("已选");$(".all-sl").css("display","inline-block");$(".product-sett").removeClass("product-sett-a")}else{$(".product-all-qx").text("全选");$(".all-sl").css("display","none");$(".product-sett").addClass("product-sett-a")}}function koncat(){var a=$(".product-box").length;if(a<=0){$(".all-price").text("0.00");$(".product-all-qx").text("全选");$(".all-sl").css("display","none");$(".product-sett").addClass("product-sett-a");$(".product-all em").removeClass("product-all-on");$(".kon-cat").css("display","block")}else{$(".kon-cat").css("display","none")}};