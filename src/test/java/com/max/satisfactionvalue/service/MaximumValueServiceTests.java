package com.max.satisfactionvalue.service;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

public class MaximumValueServiceTests {
	
	Properties prop = new Properties();
	InputStream input = null;
	String propertyfilename = "resourcebundle.properties";
	MaximumValueService mvs = new MaximumValueService();
	
	@Test
	public void testDataFileExists() {
		
		try {			
			input = getClass().getClassLoader().getResourceAsStream(propertyfilename);
			prop.load(input);			
			File dataFile = new File(prop.getProperty("max.general.datafile"));
			
			assertTrue(dataFile.exists());			
		}catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	
	@Test
	public void testDataFileName() {
		
		try {			
			input = getClass().getClassLoader().getResourceAsStream(propertyfilename);
			prop.load(input);			
			File dataFile = new File(prop.getProperty("max.general.datafile"));			
			assertEquals("data.txt", dataFile.getName());				
		}catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	
	@Test
	public void testDataFile() {
		
		try {			
			input = getClass().getClassLoader().getResourceAsStream(propertyfilename);
			prop.load(input);			
			File dataFile = new File(prop.getProperty("max.general.datafile"));	
			
			assertTrue(dataFile.isFile());			
			assertFalse(dataFile.isDirectory());		
						
		}catch (IOException e) {
			e.printStackTrace();
		} 	
	}
		
	@Test
	public void testDataFileAbsHid() {
		
		try {			
			input = getClass().getClassLoader().getResourceAsStream(propertyfilename);
			prop.load(input);			
			File dataFile = new File(prop.getProperty("max.general.datafile"));			
			assertFalse(dataFile.isAbsolute());			
			assertFalse(dataFile.isHidden());					
		}catch (IOException e) {
			e.printStackTrace();
		} 	
	}	
	@Test
	public void testDataFilePermission() {
		
		try {			
			input = getClass().getClassLoader().getResourceAsStream(propertyfilename);
			prop.load(input);			
			File dataFile = new File(prop.getProperty("max.general.datafile"));	
			
			assertTrue(dataFile.canRead());			
			assertTrue(dataFile.canWrite());			
			assertTrue(dataFile.canExecute());			
			
		}catch (IOException e) {
			e.printStackTrace();
	    } 	
	}
	
	@Test
	public void testDataFileIsEmpty() {
		
		try {			
			input = getClass().getClassLoader().getResourceAsStream(propertyfilename);
			prop.load(input);			
			File dataFile = new File(prop.getProperty("max.general.datafile"));			
			assertTrue(dataFile.length()>0);			
		}catch (IOException e) {
			e.printStackTrace();
	    } 	
	}
	

	@Test
	public void testDataTimeValueResult() {		
		BufferedReader br = null;
		
		try {
			String sCurrentLine;			
			List<Integer> valTimeList = new ArrayList<>();
			List<Integer> wtMenuList = new ArrayList<>();
			
			input = getClass().getClassLoader().getResourceAsStream(propertyfilename);			
			prop.load(input);			
			br = new BufferedReader(new FileReader(prop.getProperty("max.general.datafile")));
			while ((sCurrentLine = br.readLine()) != null) {
				String[] datasplit = sCurrentLine.split(prop.getProperty("max.general.spacesplit"));				
				assertEquals(2, datasplit.length);				
				if(datasplit.length>1){						    
					valTimeList.add(Integer.parseInt(datasplit[0]));
					wtMenuList.add(Integer.parseInt(datasplit[1]));					
				}				
				assertTrue(valTimeList.size()>0);				
				assertTrue(wtMenuList.size()>0);				
			}
			
			
			
			int[] valTime = valTimeList.stream().mapToInt(i -> i).toArray();			
			int[] wtMenu = wtMenuList.stream().mapToInt(i -> i).toArray();		
			
			
			
			assertTrue( mvs.maximumVal(500,wtMenu,valTime,wtMenuList.size()) > 0);		
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {			
				if (br != null)br.close();				 			 
			    }catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}

	@Test
	public void testDataTimeMax() {			
		assertEquals(5, mvs.max(3, 5));		
		assertEquals(6, mvs.max(6, 1));
		assertEquals(4, mvs.max(4, 4));
		
	}	
	
}
