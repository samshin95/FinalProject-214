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
        		success:function (result){
        		location.reload();
        			
        		}
        	});    
        	
        })   
        
          $("#searches").click(function(event){
        	  window.location.href='product?category=search&&search='+document.getElementById('search').value;
        })  
         $(".shopingcartt").hover(function(event){ 
	 if(event.target.classList.contains("Usedf")==false ){ 
        var nothing ={'nothing':nothing};  alert(document.getElementById("shopingcarttSuccess").innerHTML.length);"
      $.ajax({
        		type:"POST",
        		url:"getcart",
        		data: nothing,
        	    dataType:"json",
        		success:function (result){    //   <li><a href="product?category=pants">褲子</a></li>
	var sarr=result.sarr;
   document.getElementById("shopingcarttSuccess").innerHTML='';
 for(var n=0;n<sarr.length;n++){var temps="<li><a href='shopping_cart'>"+sarr[n]+"</a></li>";document.getElementById("shopingcarttSuccess").innerHTML+=temps;}
      event.target.classList.add("Usedf");$(".shopingcartt").removeClass("shopingcartt");
        		}
        	});   }
 })
        
        
     