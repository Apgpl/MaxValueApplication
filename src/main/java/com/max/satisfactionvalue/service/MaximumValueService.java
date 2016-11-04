package com.max.satisfactionvalue.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



public class MaximumValueService {	
	
	
	Properties prop = new Properties();	
	
	public String  parseDataFileElements(){
		// Parse elements 
		BufferedReader br = null;			
		InputStream input = null;		
		String result="";		
		String propertyfilename = "resourcebundle.properties";		
		try {
			String sCurrentLine;			
			List<Integer> valTimeList = new ArrayList<>();
			List<Integer> wtMenuList = new ArrayList<>();
		    int  kweight =  500;
		    int numItem = 0;			
			input = getClass().getClassLoader().getResourceAsStream(propertyfilename);			
			prop.load(input);			
			br = new BufferedReader(new FileReader(prop.getProperty("max.general.datafile")));
			while ((sCurrentLine = br.readLine()) != null) {
				String[] datasplit = sCurrentLine.split(prop.getProperty("max.general.spacesplit"));				
				if(datasplit.length>1){						    
					valTimeList.add(Integer.parseInt(datasplit[0]));
					wtMenuList.add(Integer.parseInt(datasplit[1]));					
			
				}		
				
			}
			
			numItem = wtMenuList.size();			
			int[] valTime = valTimeList.stream().mapToInt(i -> i).toArray();			
			int[] wtMenu = wtMenuList.stream().mapToInt(i -> i).toArray();				
			result = prop.getProperty("max.general.result")  + maximumVal(kweight,wtMenu,valTime,numItem);			
			System.out.println(result);			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {			
				System.out.println(prop.getProperty("max.general.end"));
				if (br != null)br.close();				 			 
			    }catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return result;	
		
	}
	    
   // Returns the maximum value that can be put in a knapsack of capacity W
     int maximumVal(int W, int wt[], int val[], int n)
    {
         int i, w;
     int K[][] = new int[n+1][W+1];
      
     // Build table K[][] in bottom up manner
     for (i = 0; i <= n; i++)
     {
         for (w = 0; w <= W; w++)
         {
             if (i==0 || w==0)
                  K[i][w] = 0;
             else if (wt[i-1] <= w)
                   K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
             else
                   K[i][w] = K[i-1][w];
         }
      }
      
      return K[n][W];
    }
     
	   //returns maximum of two integers
     int max(int a, int b) { return (a > b)? a : b; }  
     
	
	}
