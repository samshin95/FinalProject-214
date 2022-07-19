package com.shopping.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.shopping.json.JsonResult3;




@Service
public class DemoService{
	

	
	   public JsonResult3 rungame(int starter,int lines,int divide,int cut) {  

//		    cut = 4;// one block cut in how many pieces
//		    lines = 9; // canvas cut in how many pieces
//		    divide = 4;//points divide by how many
//		    starter = 2;//points started at
		    
		      int line = lines - 1; //how many vertical lines created
		      int dline = line - 1; //how many lines  vertical used to decide horizontal lines drawn
		      int points = (lines - 2) * (cut - 1) + (lines - 3); //how many points or dots each line has
		      double pdivide=(double)points / divide;
		      int pdivideup =(int)Math.ceil(pdivide);
		      int pdividedown =(int)Math.floor(pdivide);//how many points each line has , if not integer round up or round down
		      boolean pointsOdd = true;//point is a odd number or not
		      if (points % divide == 0) { pointsOdd = false ;}
		      //System.out.println(line+" "+dline +" "+ points+" "+ pdivide+" "+ pdivideup+" "+pdividedown+" "+pointsOdd);
		      
		      List<List<Integer>> darray= new ArrayList<List<Integer>>() ;
		      for (int n=0;n<dline;n++){darray.add(new ArrayList<Integer>());}
		      for (int n=0;n<dline;n++){for (int m=1;m<=points;m++){darray.get(n).add(m);}}
		      for(List l:darray ){Collections.shuffle(l);}
		      for (int  n = 0;n < dline; n++){
		      if(flip()){while (darray.get(n).size() != (pdivideup)) { darray.get(n).remove(darray.get(n).size()-1) ; }  }
		      else{while (darray.get(n).size() != (pdividedown)) { darray.get(n).remove(darray.get(n).size()-1) ; }}
		      if(n<(dline-1)){ darray.get(n+1).removeAll(darray.get(n));}
		      }         
		      
		      List<Integer> outputarray = new ArrayList<Integer>();
		      for (int n=0;n<dline;n++){
		           Object[] objects1 = darray.get(n).toArray();
		           for(Object ob:objects1){outputarray.add((Integer) ob);}
		           outputarray.add(-1);
		      }
		      int[] output = new int[outputarray.size()];
		      for (int n=0;n<outputarray.size();n++){output[n]=outputarray.get(n);}
		     
		      
		      List<List<Integer>> finalarray= new ArrayList<List<Integer>>() ;
		      for (int n=0;n<line;n++){finalarray.add(new ArrayList<Integer>());}
		      for (int n=1;n<dline;n++){
		           Object[] objects1 = darray.get(n).toArray();
		           Object[] objects2 = darray.get(n-1).toArray();
		           for(Object ob:objects1){finalarray.get(n).add((Integer) ob);}
		           for(Object ob:objects2){finalarray.get(n).add((Integer) ob);}
		      }
		       Object[] objects3 = darray.get(0).toArray();
		          for(Object ob:objects3){finalarray.get(0).add((Integer) ob);}
		        Object[] objects4 = darray.get( darray.size()-1).toArray();
		          for(Object ob:objects4){finalarray.get(finalarray.size()-1).add((Integer) ob);}
		      
		       // System.out.println("darray start");
		      //for (List l:darray){System.out.print(l);System.out.println(",");}
		        // System.out.println("darray end");
		         for (List l:finalarray){	l.add(points+1); }   
		      for (List l:finalarray){	Collections.sort(l); }
		      //for (List l:finalarray){System.out.println(l);}
		      

		     
		      List<Integer> record = new ArrayList<Integer>();
		     
		      int[] currentat={starter,0};
		      record.add(currentat[0]);record.add(currentat[1]);                   
		      
		      while(true){
		      if( getNext(currentat[1] , finalarray.get(currentat[0])) !=-1 ){currentat[1]=getNext(currentat[1] , finalarray.get(currentat[0]));
		      record.add(currentat[0]);record.add(currentat[1]);                        //System.out.println("up"+getNext(currentat[1] , finalarray.get(currentat[0]))+"!");
		      }
		      else{break;}    
		      if (currentat[1]==points+1) break;
		       if(currentat[0]==0){currentat[0]+=1;record.add(currentat[0]);record.add(currentat[1]);               }//System.out.println("turn right!");    }
		        else if(currentat[0]==(line - 1)){currentat[0]-=1;record.add(currentat[0]);record.add(currentat[1]);}  //System.out.println("turn left!");     }
		        else{  
		            if(Includes(currentat[1], darray.get(currentat[0]-1))){
		                                                      //System.out.println("turn left!");
		            currentat[0]-=1; record.add(currentat[0]);record.add(currentat[1]);}
		           else{
		               currentat[0]+=1; record.add(currentat[0]);record.add(currentat[1]);}                   //System.out.println("turn right!");}
		        }
		       }
		      
		        //System.out.println(currentat[0]+" "+currentat[1]);
		         Object[] objects = record.toArray();
		         int[] array3= new int [record.size()];
		         
		           for(int i=0; i<record.size(); i++){
		        	   
		           array3[i] = (Integer)objects[i];}
		           
		           JsonResult3 JsonResult1 = new JsonResult3() ;
		           JsonResult1.setArray1(output);
		           JsonResult1.setArray2(array3);
		           return JsonResult1;           
	   	}
		    
		 static public boolean flip(){
		   if (Math.random() < 0.5){
		return true;
		}else{
		return false;
		}
		  }
		 static public int getRandomNumber(int min, int max) {
		    return (int) ((Math.random() * (max - min)) + min);
		}
		 static public int getNext(int n, List<Integer> l) {
		   for(int num:l){if(num>n){return num;}}
		   return -1;
		}
		 public static IntStream baz(List<Integer> list)
		{
		    return list.stream().mapToInt(Integer::intValue);
		}
		static public boolean Includes(int n, List<Integer> l) {
		   for(int num:l){if(num==n){return true;}}
		   return false;
		}
		
		
	}