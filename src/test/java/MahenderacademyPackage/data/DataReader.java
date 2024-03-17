//package MahenderacademyPackage.data;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class DataReader {
//	
//	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
//	{
//		// read json to string
//		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\MahenderacademyPackage\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);//StandardCharsets.UTF_8 = is encoding on how to convert into string
//		
//		//string to HashMap  conversion using dependency "Jackson Databind" in the form of List. List form b'cpz we have two HashMaps i.e. two index (i.e. two data sets)
//		ObjectMapper mapper = new ObjectMapper(); //ObjectMapper is from "Jackson Databind" dependency
//		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
//		return data;
//		
//		//{map}, {map2}
//		
//	}
//
//}
