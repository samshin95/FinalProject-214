package com.shopping.service;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	private String filePathcustomer="src\\main\\resources\\static\\assets\\images\\customer\\";
	private String filePathproduct="src\\main\\resources\\static\\assets\\images\\product\\";
	private String filePathstore="src\\main\\resources\\static\\assets\\images\\store\\";
	//private String filePathcustomer="C:\\images\\customer\\";
	//private String filePathproduct="C:\\images\\product\\";
	//private String filePathstore="C:\\images\\store\\";
	//private String iconPath="C://images//icon//";
	public String getpath(int n) {
		String pathname=null;
		switch(n)
		{case 1:pathname="src\\\\main\\\\resources\\\\static\\\\assets\\\\images\\\\customer\\\\";
			break;
		case 2:pathname="src\\\\main\\\\resources\\\\static\\\\assets\\\\images\\\\product\\\\";
			break;
		case 3:pathname="src\\\\main\\\\resources\\\\static\\\\assets\\\\images\\\\store\\\\";
			break;
		}
		return pathname;
    }
	
	public boolean option(MultipartFile file,String filename,int n) {
        try {
            if(n==1) {uploadFile(file.getBytes(),filePathcustomer,filename);}
          	if(n==4) {uploadFile(file.getBytes(),filePathproduct,filename);}
            if(n==5) {uploadFile(file.getBytes(),filePathstore,filename);}
            if(n==6) {deleteFile(filePathproduct,filename);}
            if(n==6) {deleteFile(filePathcustomer,filename);}
            if(n==6) {deleteFile(filePathstore,filename);}
            if(n==7) {createIcon(file.getBytes(),filePathproduct,filename);}
            if(n==8) {createIcon(file.getBytes(),filePathcustomer,filename);}
            if(n==9) {createIcon(file.getBytes(),filePathstore,filename);}
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("檔案上傳失敗!");
            return false;
        }
        System.out.println("檔案上傳成功!");
        return true;
    }
	
	public void  uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();         // System.out.println(filePath+fileName);//for test
    }
	public void  deleteFile(String filePath, String fileName) throws Exception { 
		String result=listFile(filePath,new String[]{".jpg",".png"} ,fileName);
		if(result!="error") {
        File targetFile = new File(result);  
        if(targetFile.exists()){    
            targetFile.delete();    
        }       
        System.out.println("delete successful!");}
		else{ System.out.println("delete fail!");}
    }
	
	public void createIcon(byte[] file,String Path,String name) {
        try {
            
            File foSmall = new File(Path+name); // 將要轉換出的小圖檔案
            AffineTransform transform = new AffineTransform();
            //讀取圖片

            InputStream is = new ByteArrayInputStream(file);
            BufferedImage bis = ImageIO.read(is);
           
            //獲得圖片原來的高寬
            int w = bis.getWidth();
            int h = bis.getHeight();
            //double scale = (double) w / h;//等比例縮放　
            int nowWidth = 51;
	        int nowHeight = (nowWidth * h) / w;
	        if (nowHeight > 51) {
	            nowHeight = 51;
	            nowWidth = (nowHeight * w) / h;
	        }

        double sx = (double) nowWidth / w;
        double sy = (double) nowHeight / h;

        transform.setToScale(sx, sy);

        AffineTransformOp ato = new AffineTransformOp(transform, null);
        BufferedImage bid = new BufferedImage(nowWidth, nowHeight,
                BufferedImage.TYPE_3BYTE_BGR);
        ato.filter(bis, bid);
        
        ImageIO.write(bid, "png", foSmall);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public String listFile(String folder, String[] ext ,String filename) {
      
        File dir = new File(folder);

        if(dir.isDirectory()==false){
            System.out.println("Directory does not exists " );
            return "error";
        }

        
        for(String st:ext) {
            GenericExtFilter filter = new GenericExtFilter(st);
            String[] list = dir.list(filter);
            
            if (list.length != 0) {
            	 for (String file : list) {
            		 int n=file.lastIndexOf(".");
            		 if(file.substring(0, n-1).equals(filename)) {
            		String temp = new StringBuffer(folder).append(File.separator)
                             .append(file).toString();
                     System.out.println("file : " + temp);
                 
                    return temp;
            		 }
                  
                 }
            }
          }// list out all the file name and filter by the extension
      
        return "error";
    }
}
class GenericExtFilter implements FilenameFilter {

    private String ext;

    public GenericExtFilter(String ext) {
        this.ext = ext;
    }

    public boolean accept(File dir, String name) {
        return (name.endsWith(ext));
    }
}
