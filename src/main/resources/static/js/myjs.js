window.onload = nocache();
    function nocache() {
        
    var nods = document.getElementsByClassName('NO-CACHE');
    for (var i = 0; i < nods.length; i++)
    {    
    var aaa="?a=" + Math.random();
        nods[i].setAttribute("src",nods[i].getAttribute("src")+aaa);
    }
    }
        $(function() {
            var selectedClass = "";
            $("p").click(function(){
            selectedClass = $(this).attr("data-rel");
            $("#portfolio").fadeTo(50, 0.1);
                $("#portfolio div").not("."+selectedClass).fadeOut();
            setTimeout(function() {
              $("."+selectedClass).fadeIn();
              $("#portfolio").fadeTo(50, 1);
            }, 500);
                
            });
        });
        setInterval(function () {}, 1000);
        
        $(".btn").click(function(event){
        	
        	var pid=event.target.parentElement.nextElementSibling;
        	if(pid==null){pid=event.target.parentElement.parentElement.nextElementSibling;}
        	if(pid==null){pid=event.target.parentElement.parentElement.parentElement.nextElementSibling;}
        	if(pid==null){pid=event.target.parentElement.parentElement.parentElement.parentElement.nextElementSibling;}
        	
      	var fav ={'produceid':pid.innerHTML,'uid':document.getElementById("uid").innerText}; 
        	$.ajax({
        		type:"POST",
        		url:"addfav",
        		data: fav,
        	    dataType:"json",
        		success:function (result){
        			if(result.s=='Add To Favorites'){event.target.classList.remove("fa-heart");event.target.classList.add("fa-heart-o");}
        			else{event.target.classList.remove("fa-heart-o");event.target.classList.add("fa-heart");};
        			
        		}
        	});         
        	
        })
        //////////////click
          $(".exrate").click(function(event){
        	var rate;
         rate=event.target.innerText;
          exrate ={'rate':rate}; 
        	$.ajax({
        		type:"POST",
        		url:"exrate",
        		data:  exrate,
        	    dataType:"json",
        		success:function (result){	location.reload();
        		}
        	});    
        	
        })   
            $("#langchange1").click(function(event){
        	        var  exlan ={'lan':'en'}; 
        	$.ajax({
        		type:"POST",
        		url:"exlan",
        		data:  exlan,
        	    dataType:"json",
        		success:function (result){
        			
        			  window.location.href=window.location.href+"?lang=en_US"
        		}
        	});    
        	
        })   
         $("#langchange2").click(function(event){
                    var  exlan ={'lan':'tw'}; 
        	$.ajax({
        		type:"POST",
        		url:"exlan",
        		data:  exlan,
        	    dataType:"json",
        		success:function (result){
        		  window.location.href=window.location.href+"?lang=zh_TW"
        			
        		}
        	});    
        	
        })   
         $("#langchange3").click(function(event){
        	  var  exlan ={'lan':'cn'}; 
        	$.ajax({
        		type:"POST",
        		url:"exlan",
        		data: exlan,
        	    dataType:"json",
        		success:function (result){
        			  window.location.href=window.location.href+"?lang=zh_CN"
        			
        		}
        	});    
        	
        })   
        
        
           $(".shopingcartt").hover(function(event){
	if(document.getElementById("shopingcarttSuccess").innerHTML.length==101){
	 nan ={'nan':false}; 
        	$.ajax({
        		type:"POST",
        		url:"getcart",
        		data: nan,
        	    dataType:"json",
        		success:function (result){
for(var n=0;n<result.sarr.length;n++){document.getElementById("shopingcarttSuccess").innerHTML+="<li><a href='shopping_cart'>"+result.sarr[n]+"</a></li>";}
}
        	});    
        	}
        })
        
          window.onload = nocache();
      function nocache() {
          
      var nods = document.getElementsByClassName('NO-CACHE');
      for (var i = 0; i < nods.length; i++)
      {    
      var aaa="?a=" + Math.random();
          nods[i].setAttribute("src",nods[i].getAttribute("src")+aaa);
      }
      }
           
     