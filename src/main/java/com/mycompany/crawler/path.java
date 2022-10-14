/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author shish
 */
public class path {
    String pathP=null;
    path() throws IOException{
 
   //initial creation
          File spDir=new File(System.getProperty("user.home")+"\\Attendance");
          File spDir1=new File(System.getProperty("user.home")+"\\Attendance\\batch");
            
          spDir.mkdir();
          spDir1.mkdir();
            
      FileWriter fw=new FileWriter(System.getProperty("user.home")+"\\Attendance\\batch.txt",true);  
  
    }
    
    String[] getDate(String batch,String course) throws FileNotFoundException, IOException{
    
           String result[] =new String[2];
        
           
           String ID;
        try (BufferedReader id_rdr = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Attendance\\batch\\"+batch+"\\id.txt"))) {
            ID = id_rdr.readLine();
        }
    
           if(ID!=null){
           
               ArrayList<String> dates;
               try (BufferedReader date_rdr = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Attendance\\batch\\"+batch+"\\"+course+"\\student\\"+ID+".txt"))) {
                   dates = new ArrayList();
                   String date;
                   while((date=date_rdr.readLine())!=null)
                   {
                       dates.add(date);
                   }   }
           
           
           if(dates.size()>=2){
           
           result[0]= dates.get(dates.size()-2).substring(0, 9);  
           result[1]= dates.get(dates.size()-1).substring(0, 9);
           
           }
           else if(dates.size()==1){
           result[0]= "date1";
           result[1]= dates.get(dates.size()-1).substring(0, 9);
           }
           else{
           result[0]="date1";
           result[1]="date2";
           }
           
           
          
           return result;
           }
           else{
           result[0]="date1";
           result[1]="date2";
           return result;
           }
           
           
    }
    
    DefaultMutableTreeNode tree(){
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("IIT");
        DefaultMutableTreeNode course = new DefaultMutableTreeNode("Course");
        DefaultMutableTreeNode student = new DefaultMutableTreeNode("Student");
 
       
        course.add(student);
        
        root.add(course);
        root.add(student);
         
        //create the tree by passing in the root node
        return root;
    }
    
    String[] generateReportId(String batch,String course,String id) throws IOException{
    File f=new File(System.getProperty("user.home")+"\\Attendance\\batch\\"+batch+"\\id.txt");
    File name=new File(System.getProperty("user.home")+"\\Attendance\\batch\\"+batch+"\\name.txt");
    BufferedReader br=new BufferedReader(new FileReader(f));
    BufferedReader nameBr=new BufferedReader(new FileReader(name));
    int count=0;
    String res[]=new String[4];
            
        if(f.exists()){
            String getID;
            int i=0;
            while((getID=br.readLine())!=null){
                
                if(getID.equals(id)){
                count=++i;
                break;
                }
                else{
                i++;
                }
              
            }
        }
        
        //get name
         String getName=null;
           
           for(int i=0;i<count;i++){
              getName=nameBr.readLine();
           }
          
           
        res[0]=getName;
        res[1]=id;
    return res;
    }
    
    int[] totalClass(String batch,String course,String id) throws FileNotFoundException, IOException{
        int result[]=new int[3];
        int total=0;
        int present=0;
        BufferedReader id1=new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Attendance\\batch\\"+batch+"\\id.txt"));
           String idC=id1.readLine()+".txt";
           id1.close();
           
            BufferedReader present1=new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Attendance\\batch\\"+batch+"\\"+course+"\\student\\"+idC));
            String pre;
            while((pre=present1.readLine())!=null){
                total++;
           }
            present1.close();
            
            BufferedReader presentTemp=new BufferedReader(new FileReader(System.getProperty("user.home")+"\\Attendance\\batch\\"+batch+"\\"+course+"\\student\\"+id+".txt"));
            String preT;
            while((preT=presentTemp.readLine())!=null){
                if(preT.substring(11).equals("P")){
                    present++;
                }
           }
            
             
            
           
           result[0]=total;
           result[1]=present;
       
           
        result[2]=result[0]-result[1];
        
          
        
        return result;
    }
    void createCourse(String batch,String course) throws IOException{
   
        
          pathP=System.getProperty("user.home")+"\\Attendance\\batch\\";
        
     File spDir2=new File(pathP+batch+File.separator+course);
     spDir2.mkdir();
    
     File spDir3=new File(spDir2+File.separator+"student");
     spDir3.mkdir();

    File remark=new File(spDir2+File.separator+"remark.txt");
    File result=new File(spDir2+File.separator+"result.txt");
    
    
    if(remark.exists()){
        
    }
    else{
         FileWriter remarkWR=new FileWriter(spDir2+File.separator+"remark.txt");
    }
    
    //result
       if(result.exists()){
    }
    else{
         FileWriter resultWR=new FileWriter(spDir2+File.separator+"result.txt");
    }       
       
        
       
    }
    void createBatch(String bt) throws IOException{
        
     pathP=System.getProperty("user.home")+"\\Attendance\\batch\\";
     File spDir1=new File(pathP+bt);
     spDir1.mkdir();
     
    FileWriter coursefw=new FileWriter(pathP+bt+File.separator+"course.txt",true);
    FileWriter nameWR=new FileWriter(pathP+bt+File.separator+"name.txt",true);
    FileWriter idWR=new FileWriter(pathP+bt+File.separator+"id.txt",true);
     
    
  
    }
    
}
