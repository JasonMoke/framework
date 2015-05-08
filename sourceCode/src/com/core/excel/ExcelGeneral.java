package com.core.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.util.Util;

/**
 * @ClassName: ExcelGeneral 
 * @Description: EXCEL通用类（适应2003和2007的EXCEL解决方案）
 * @author:  tanyi
 * @date: 2014-01-16 14:11:04
 */ 
public class ExcelGeneral {
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String fileName = "D:\\BISAC_Subject_Headings.xls";
		try {
			@SuppressWarnings("unused")
			//Map<String,List<String[]>> map = readExcel(fileName);
			Map<String,StringBuilder> map = excelToHtml(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//CreateWorkbook--------------------------------------------------------------------------------------------------------------
	
	/**
	 * 
     * @Title: CreateWorkbook
     * @param:fileName EXCEL文件名
     * @param:suffixName 返回文件的后缀名
     * @Description: 根据EXCEL文件名（自动适应OFFICE2003和2007以上的版本）创建EXCEL工作薄。
     * @return 返回EXCEL工作薄
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Workbook CreateWorkbook(File file) throws FileNotFoundException, IOException{
		return CreateWorkbook(file,new StringBuffer());
	}
	
	/**
     * @Title: CreateWorkbook
     * @param:fileName EXCEL文件名
     * @param:suffixName 返回文件的后缀名
     * @Description: 根据EXCEL文件名（自动适应OFFICE2003和2007以上的版本）创建EXCEL工作薄。
     * @return 返回EXCEL工作薄
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Workbook CreateWorkbook(File file,StringBuffer suffixName) throws FileNotFoundException, IOException{
		
		/*
		 * 定义Workbook，请在此包中引用：import org.apache.poi.ss.usermodel.Workbook;
		 * 定义Sheet，请在此包中引用：import org.apache.poi.ss.usermodel.Sheet;
		 * 定义Row，请在此包中引用：import org.apache.poi.ss.usermodel.Row;
		 * 定义Cell，请在此包中引用：import org.apache.poi.ss.usermodel.Cell;
		 * */
		
		String fileName = file.getName();
		if(fileName.endsWith(".xls")){
			suffixName.append(".xls");
			return new HSSFWorkbook(new FileInputStream(file));
		}else if(fileName.endsWith(".xlsx")){
			suffixName.append(".xlsx");
			return new XSSFWorkbook(new FileInputStream(file));
		}else{
			throw new IOException("不支持的文件类型");
		}
	}
	
	/**
     * @Title: CreateWorkbook
     * @param:fileName EXCEL文件名
     * @param:suffixName 返回文件的后缀名
     * @Description: 根据EXCEL文件名（自动适应OFFICE2003和2007以上的版本）创建EXCEL工作薄。
     * @return 返回EXCEL工作薄
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Workbook CreateWorkbook(String fileName) throws FileNotFoundException, IOException{
		return CreateWorkbook(fileName,new StringBuffer());
	}
	
	/**
     * @Title: CreateWorkbook
     * @param:fileName EXCEL文件名
     * @param:suffixName 返回文件的后缀名
     * @Description: 根据EXCEL文件名（自动适应OFFICE2003和2007以上的版本）创建EXCEL工作薄。
     * @return 返回EXCEL工作薄
     * @author tanyi
     * @date 2013-12-25 上午10:47:31
     * @throws
     */
	public static Workbook CreateWorkbook(String fileName,StringBuffer suffixName) throws FileNotFoundException, IOException{
		File file = new File(fileName);
		return CreateWorkbook(file, suffixName);
	}
	
	//readExcel--------------------------------------------------------------------------------------------------------------
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String,List<String[]>> readExcel(String fileName) throws FileNotFoundException, IOException, Exception{
		return readExcel(fileName, null, null, null);
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String,List<String[]>> readExcel(String fileName, String[] sheetNames) throws FileNotFoundException, IOException, Exception{
		return readExcel(fileName, sheetNames, null, null);
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String,List<String[]>> readExcel(String fileName, String[] sheetNames, int[] headerIndex) throws FileNotFoundException, IOException, Exception{
		return readExcel(fileName, sheetNames, headerIndex, null);
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String,List<String[]>> readExcel(String fileName, String[] sheetNames, int[] headerIndex, int[] beginIndex) throws FileNotFoundException, IOException, Exception{
		boolean isAutoCreateSheetNames = false;
		boolean isAutoCreateHeaderIndex = false;
		boolean isAutoCreateBginIndex = false;
		Workbook workbook = CreateWorkbook(fileName);
		int sheetsNum = workbook.getNumberOfSheets();
		
		if(sheetNames == null || sheetNames.length <= 0){
			isAutoCreateSheetNames = true;
			sheetNames = new String[sheetsNum];
		}
		if(headerIndex == null || headerIndex.length <= 0){
			isAutoCreateHeaderIndex = true;
			headerIndex = new int[sheetsNum]; 
		}
		if(beginIndex == null || beginIndex.length <= 0){
			isAutoCreateBginIndex = true;
			beginIndex = new int[sheetsNum]; 
		}
		for(int index = 0; index < sheetsNum; index++){
			if(isAutoCreateHeaderIndex) headerIndex[index] = 1;
			if(isAutoCreateBginIndex) beginIndex[index] = 2;
			if(isAutoCreateSheetNames) sheetNames[index] = workbook.getSheetName(index);
		}
		return readExcel(workbook, sheetNames, headerIndex, beginIndex);
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String,List<String[]>> readExcel(Workbook workbook, String[] sheetNames, int[] headerIndex, int[] beginIndex) 
			throws FileNotFoundException, IOException, Exception{
		
		int index = 0;
		Map<String,List<String[]>> sheets = new HashMap<String, List<String[]>>();
		
		for(String sheetName : sheetNames){
			Sheet sheet = workbook.getSheet(sheetName);
			if(sheet == null) {
				System.out.print(sheetName + "不存在！");
				continue;
			}
			Row headerRow = sheet.getRow(headerIndex[index]);
			
			//得到表头单元的数量
			int headerCellNum = headerRow.getLastCellNum();
			//得到excel的起始行
			int beginRowIndex = beginIndex[index];  
			//得到excel的结束行
			int endRowIndex = sheet.getLastRowNum();
			//存放excel表的数据
			List<String[]> table = new ArrayList<String[]>();
			
			for(int rowIndex = beginRowIndex; rowIndex <= endRowIndex; rowIndex++){
				Row currentRow = sheet.getRow(rowIndex);
				int nullCellValueNum = 0;
				int currentRowCellNum = currentRow.getLastCellNum();
				currentRowCellNum = currentRowCellNum > headerCellNum ? headerCellNum : currentRowCellNum;
				//用来保存行的数量
				String[] rowValues = new String[headerCellNum];
				
				for(int cellIndex = 0; cellIndex < currentRowCellNum; cellIndex++){
					Cell currentCell = currentRow.getCell(cellIndex);
					rowValues[cellIndex] = getCellValue(currentCell);
					if(rowValues[cellIndex].trim().length() <=0){
						nullCellValueNum ++;
					}
				}
				if(nullCellValueNum < currentRowCellNum){
					table.add(rowValues);
				}
			}
			sheets.put(sheetName, table);
			index++;
		}
		return sheets;
	}

	//excelToHtmlTable--------------------------------------------------------------------------------------------------------------
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String,StringBuilder> excelToHtml(String fileName) throws FileNotFoundException, IOException, Exception{
		return excelToHtml(fileName, null, null, null);
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String,StringBuilder> excelToHtml(String fileName, String[] sheetNames) throws FileNotFoundException, IOException, Exception{
		return excelToHtml(fileName, sheetNames, null, null);
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String,StringBuilder> excelToHtml(String fileName, String[] sheetNames, int[] headerIndex) throws FileNotFoundException, IOException, Exception{
		return excelToHtml(fileName, sheetNames, headerIndex, null);
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String,StringBuilder> excelToHtml(String fileName, String[] sheetNames, int[] headerIndex, int[] beginIndex) throws FileNotFoundException, IOException, Exception{
		boolean isAutoCreateSheetNames = false;
		boolean isAutoCreateHeaderIndex = false;
		boolean isAutoCreateBginIndex = false;
		Workbook workbook = CreateWorkbook(fileName);
		int sheetsNum = workbook.getNumberOfSheets();
		
		if(sheetNames == null || sheetNames.length <= 0){
			isAutoCreateSheetNames = true;
			sheetNames = new String[sheetsNum];
		}
		if(headerIndex == null || headerIndex.length <= 0){
			isAutoCreateHeaderIndex = true;
			headerIndex = new int[sheetsNum]; 
		}
		if(beginIndex == null || beginIndex.length <= 0){
			isAutoCreateBginIndex = true;
			beginIndex = new int[sheetsNum]; 
		}
		for(int index = 0; index < sheetsNum; index++){
			if(isAutoCreateHeaderIndex) headerIndex[index] = 1;
			if(isAutoCreateBginIndex) beginIndex[index] = 2;
			if(isAutoCreateSheetNames) sheetNames[index] = workbook.getSheetName(index);
		}
		return excelToHtml(workbook, sheetNames, headerIndex, beginIndex);
	}
	
	/**
	 * excelToHtmlTable
	 * @param workbook
	 * @param sheetNames
	 * @param headerIndex
	 * @param beginIndex
	 * @return Map<String,StringBuilder>
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public static Map<String,StringBuilder> excelToHtml(Workbook workbook, String[] sheetNames, int[] headerIndex, int[] beginIndex)
			throws FileNotFoundException, IOException, Exception{
		
		int index = 0;
		Map<String,StringBuilder> map = new HashMap<String,StringBuilder>();
		
		for(String sheetName : sheetNames){
			Sheet sheet = workbook.getSheet(sheetName);
			if(sheet == null) {
				System.out.print(sheetName + "不存在！");
				continue;
			}
			Row headerRow = sheet.getRow(headerIndex[index]);
			
			//得到表头单元的数量
			int headerCellNum = headerRow.getLastCellNum();
			//得到excel的起始行
			int beginRowIndex = beginIndex[index];  
			//得到excel的结束行
			int endRowIndex = sheet.getLastRowNum();
			//存放excel表的数据
			StringBuilder table = new StringBuilder("<table>");
			
			for(int rowIndex = beginRowIndex; rowIndex <= endRowIndex; rowIndex++){
				Row currentRow = sheet.getRow(rowIndex);
				int nullCellValueNum = 0;
				int currentRowCellNum = currentRow.getLastCellNum();
				currentRowCellNum = currentRowCellNum > headerCellNum ? headerCellNum : currentRowCellNum;
				StringBuffer tr = new StringBuffer("<tr>");
				int cellIndex = 0; 
				for(; cellIndex < currentRowCellNum; cellIndex++){
					tr.append("<td>");
					Cell currentCell = currentRow.getCell(cellIndex);
					String cellValue = getCellValue(currentCell);
					if(cellValue.trim().length() <=0){
						nullCellValueNum ++;
					}
					tr.append(cellValue);
					tr.append("</td>");
				}
				if(cellIndex != headerCellNum){
					for(; cellIndex < headerCellNum; cellIndex++){
						tr.append("<td></td>");
					}
				}
				tr.append("</tr>");
				if(nullCellValueNum < currentRowCellNum){
					table.append(tr);
				}
			}
			table.append("</table>");
			map.put(sheetName, table);
			index++;
		}
		return map;
	}
	
	/**
	 * @Title: getCellValue
	 * @param hssfCell (EXCEL单元格)
	 * @Description: 获得EXCEL单元格的值
	 * @return 返回EXCEL单元格的值
	 * @author
	 * @date 2013-12-25 上午10:47:31
	 * @throws
	 */
	public static String getCellValue(Cell cell) throws Exception {
		String cellValue = "";
		if (Util.isNull(cell)) {
			return cellValue;
		}
		switch (cell.getCellType()) {
		// 此行表示单元格的内容为字符类型
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;
		// 此行表示单元格的内容为数字类型
		case Cell.CELL_TYPE_NUMERIC:
			if (cell.getCellStyle().getDataFormatString().indexOf("%") != -1) {
				cellValue = cell.getNumericCellValue() + "";
				break;
			}
			// 如果是日期格式
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				cellValue = format.format(HSSFDateUtil.getJavaDate(cell
						.getNumericCellValue()));
			} else {
				DecimalFormat df = new DecimalFormat("0");// 使用DecimalFormat类对科学计数法格式的数字进行格式化
				cellValue = df.format(cell.getNumericCellValue());
			}
			break;
		// 此行表示单元格的内容为布尔类型zh
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		// 此行表示单元格的内容为空类型
		case Cell.CELL_TYPE_BLANK:
			return cellValue = "";
			// 此行表示单元格的内容为公式类型
		case Cell.CELL_TYPE_FORMULA:
			try {  
				cellValue = String.valueOf(cell.getNumericCellValue());  
			} catch (IllegalStateException e) {  
				cellValue = String.valueOf(cell.getStringCellValue());  
			}  
			break;
		// 此行表示单元格的内容为错误类型
		case Cell.CELL_TYPE_ERROR:
			cellValue = "";
			break;
		default:
			cellValue = "";
			break;
		}
		return cellValue = Util.isNull(cellValue) ? "" : cellValue;
	}
}
