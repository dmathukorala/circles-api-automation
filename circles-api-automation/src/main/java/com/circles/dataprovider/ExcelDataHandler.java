package com.circles.dataprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.circles.utils.data.ExcelUtils;

public final class ExcelDataHandler {

	public HashMap<String, HashMap<String, HashMap<String, String>>> getData(String file, String sheet) throws Exception {

		List<List<String>> sheetData = loadDataFromUtil(file, sheet);
		return generateHashMap(sheetData);

	}

	protected List<List<String>> loadDataFromUtil(String file, String sheet) throws Exception{

		// call utill and get data as List<List<String>>
		return new ExcelUtils(file, sheet).readExcelData();

	}

	protected HashMap<String, HashMap<String, HashMap<String, String>>> generateHashMap(List<List<String>> sheetData) {
		
		HashMap<String, HashMap<String, HashMap<String, String>>> testMap;
		testMap = generateMultiScenarioHashMap(sheetData);
		return testMap;
	}

	private HashMap<String, HashMap<String, HashMap<String, String>>> generateSingleScenarioHashMap(List<List<String>> sheetData) {

		HashMap<String, HashMap<String, HashMap<String, String>>> testMap = new HashMap<String, HashMap<String, HashMap<String, String>>>();
		HashMap<String, HashMap<String, String>> senarioMap;
		HashMap<String, String> dataMap;

		for (List<String> row : sheetData) {

			String testCase = row.remove(0);
			String senario = row.remove(0);

			dataMap = new HashMap<String, String>();

			for (String dataSet : row) {
				dataMap.put(getKey(dataSet), getValue(dataSet));
			}

			if (testMap.containsKey(testCase)) {

				senarioMap = testMap.get(testCase);
				senarioMap.put(senario, dataMap);

			} else {

				senarioMap = new HashMap<String, HashMap<String, String>>();
				senarioMap.put(senario, dataMap);
				testMap.put(testCase, senarioMap);

			}

		}

		return testMap;

	}

	private String getKey(String dataSet) {

		return dataSet.split("=")[0];

	}

	private String getValue(String dataSet) {

		return dataSet.split("=")[1];
	}

	private HashMap<String, HashMap<String, HashMap<String, String>>> generateMultiScenarioHashMap(List<List<String>> sheetData) {

		HashMap<String, HashMap<String, HashMap<String, String>>> testMap = new HashMap<String, HashMap<String, HashMap<String, String>>>();
		HashMap<String, HashMap<String, String>> senarioMap;
		HashMap<String, String> dataMap;
		
		String testCase = null;
		List<String> keySet = new ArrayList<String>();
		List<String> valueSet = new ArrayList<String>();
		String senario;
		
		for (List<String> row : sheetData) {
			
			if(row.get(0).startsWith("#")){
				
				testCase = row.remove(0);
				testCase = testCase.replaceFirst("#", "");
				keySet.clear();
				for (String key : row) {
					keySet.add(key);
				}
			}
			else { 
				senario = row.remove(0);
				valueSet.clear();
				for (String value : row) {
					valueSet.add(value);
				}
				dataMap = mixKeysAndValues(keySet, valueSet);
				
				if (testMap.containsKey(testCase)) {

					senarioMap = testMap.get(testCase);
					senarioMap.put(senario, dataMap);

				} else {

					senarioMap = new HashMap<String, HashMap<String, String>>();
					senarioMap.put(senario, dataMap);
					testMap.put(testCase, senarioMap);

				}
			}
		}

		return testMap;

	}
	
	private HashMap<String, String> mixKeysAndValues(List<String> keySet, List<String> valueSet) {

		HashMap<String, String> dataMap = new HashMap<String, String>();
		for (String key : keySet) {
			dataMap.put(key, valueSet.get(keySet.indexOf(key)));
		}
		
		return dataMap;
		
	}

}
