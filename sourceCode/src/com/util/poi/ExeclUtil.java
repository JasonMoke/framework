/**
 * @Title: ExeclUtil.java   
 * @Copyright 2010 -2013 CreativeWise
 * @Package com.util.poi   
 * @Description: 
 * @author guangchao    
 * @date 2014年3月28日 下午12:42:26   
 * @version V1.0 
 */

package com.util.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.core.excel.ExcelGeneral;

/**
 * @ClassName: ExeclUtil
 * @Description:
 * @author guangchao
 * @date 2014年3月28日 下午12:42:26
 * 
 */

public class ExeclUtil implements ExeclUtilInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.util.poi.ExeclUtilInterface#create2007Excel()
	 */
	@Override
	public void create2007Excel() throws FileNotFoundException,IOException {
		// HSSFWorkbook workBook = new HSSFWorkbook();// 创建 一个excel文档对象
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet();// 创建一个工作薄对象

		sheet.setColumnWidth(1, 10000);// 设置第二列的宽度为

		XSSFRow row = sheet.createRow(1);// 创建一个行对象

		row.setHeightInPoints(23);// 设置行高23像素

		XSSFCellStyle style = workBook.createCellStyle();// 创建样式对象

		// 设置字体

		XSSFFont font = workBook.createFont();// 创建字体对象

		font.setFontHeightInPoints((short) 15);// 设置字体大小

		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体

		font.setFontName("黑体");// 设置为黑体字

		style.setFont(font);// 将字体加入到样式对象

		// 设置对齐方式

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中

		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中

		// 设置边框

		style.setBorderTop(HSSFCellStyle.BORDER_THICK);// 顶部边框粗线

		style.setTopBorderColor(HSSFColor.RED.index);// 设置为红色

		style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);// 底部边框双线

		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左边边框

		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右边边框

		// 格式化日期

		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

		XSSFCell cell = row.createCell(1);// 创建单元格

		cell.setCellValue(new Date());// 写入当前日期

		cell.setCellStyle(style);// 应用样式对象

		// 文件输出流

		FileOutputStream os = new FileOutputStream("D:/style_2007.xlsx");

		workBook.write(os);// 将文档对象写入文件输出流

		os.close();// 关闭文件输出流
		System.out.println("创建成功 office 2007 excel");
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.util.poi.ExeclUtilInterface#create2003Excel()
	 */
	@Override
	public void create2003Excel() throws FileNotFoundException,IOException {
		HSSFWorkbook workBook = new HSSFWorkbook();// 创建 一个excel文档对象

		HSSFSheet sheet = workBook.createSheet();// 创建一个工作薄对象

		sheet.setColumnWidth(1, 10000);// 设置第二列的宽度为

		HSSFRow row = sheet.createRow(1);// 创建一个行对象

		row.setHeightInPoints(23);// 设置行高23像素

		HSSFCellStyle style = workBook.createCellStyle();// 创建样式对象

		// 设置字体

		HSSFFont font = workBook.createFont();// 创建字体对象

		font.setFontHeightInPoints((short) 15);// 设置字体大小

		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体

		font.setFontName("黑体");// 设置为黑体字

		style.setFont(font);// 将字体加入到样式对象

		// 设置对齐方式

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中

		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中

		// 设置边框

		style.setBorderTop(HSSFCellStyle.BORDER_THICK);// 顶部边框粗线

		style.setTopBorderColor(HSSFColor.RED.index);// 设置为红色

		style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);// 底部边框双线

		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左边边框

		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右边边框

		// 格式化日期

		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

		HSSFCell cell = row.createCell(1);// 创建单元格

		cell.setCellValue(new Date());// 写入当前日期

		cell.setCellStyle(style);// 应用样式对象

		// 文件输出流

		FileOutputStream os = new FileOutputStream("D:/style_2003.xls");

		workBook.write(os);// 将文档对象写入文件输出流

		os.close();// 关闭文件输出流
		System.out.println("创建成功 office 2003 excel");
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.util.poi.ExeclUtilInterface#readExcel(java.io.File)
	 */
	@Override
	public Map<String,List<Object>> readExcel(File file,String[] tables) throws Exception {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
				.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file,tables);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file,tables);
		} else {
			throw new IOException("不支持的文件类型");
		}
	}
	public List<List<Object>> readExcel(File file) throws IOException {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
				.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file);
		} else {
			throw new IOException("涓嶆敮鎸佺殑鏂囦欢绫诲瀷");
		}
	}
	public List<List<Object>> read2007Excel(File file) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		// String path = System.getProperty("user.dir") +
		// System.getProperty("file.separator")+"dd.xlsx";
		// System.out.println("璺緞锛�+path);
		// 鏋勯� XSSFWorkbook 瀵硅薄锛宻trPath 浼犲叆鏂囦欢璺緞
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));

		// 璇诲彇绗竴绔犺〃鏍煎唴瀹�
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
//		System.out.println("璇诲彇office 2007 excel鍐呭濡備笅锛�);
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					linked.add("");
//					System.out.print("##");
					continue;
					
				}
				DecimalFormat df = new DecimalFormat("0");// 鏍煎紡鍖�number String
				// 瀛楃
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");// 鏍煎紡鍖栨棩鏈熷瓧绗︿覆
				DecimalFormat nf = new DecimalFormat("0");// 鏍煎紡鍖栨暟瀛�

				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					// System.out.println(i + "琛� + j + " 鍒�is String type");
					value = cell.getStringCellValue();
//					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					// System.out.println(i + "琛� + j
					// + " 鍒�is Number type ; DateFormt:"
					// + cell.getCellStyle().getDataFormatString());
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());

					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
//					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					// System.out.println(i + "琛� + j + " 鍒�is Boolean type");
					value = cell.getBooleanCellValue();
//					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					// System.out.println(i + "琛� + j + " 鍒�is Blank type");
					value = "";
//					System.out.print("@@@");
					// System.out.println(value);
					break;
				default:
					// System.out.println(i + "琛� + j + " 鍒�is default type");
					value = cell.toString();
//					System.out.print("  " + value + "  ");
				}
				if (value == null || "".equals(value)) {
					value="";
					//System.out.println("  " + value + "  ");
					//continue;
				}
				linked.add(value);
			}
//			System.out.println("");
			list.add(linked);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.util.poi.ExeclUtilInterface#read2007Excel(java.io.File)
	 */
	@Override
	public Map<String,List<Object>> read2007Excel(File file,String[] tables) throws Exception {
		Map<String,List<Object>> list = new HashMap<String,List<Object>>();
		Workbook xwb = ExcelGeneral.CreateWorkbook(file);
		for (int s = 0; s < tables.length; s++) {
		// 读取第一章表格内容
			Sheet sheet = xwb.getSheet(tables[s]);
			List<Object> link = new ArrayList<Object>();
			String value = null;
			Row row = null;
			Cell cell = null;
			for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				List<Object> linked = new ArrayList<Object>();
				for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					if (cell == null) {
						linked.add("");
						continue;
						
					}
					value = ExcelGeneral.getCellValue(cell);
					linked.add(value);
				}
				link.add(linked);
			}
			list.put(tables[s],link);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.util.poi.ExeclUtilInterface#read2003Excel(java.io.File)
	 */
	@Override
	public Map<String,List<Object>> read2003Excel(File file,String[] tables) throws Exception {
		Map<String,List<Object>> list = new HashMap<String,List<Object>>();
		Workbook hwb = ExcelGeneral.CreateWorkbook(file);
		for (int s = 0; s < tables.length; s++) {
			Sheet sheet = hwb.getSheet(tables[s]);
			List<Object> link = new ArrayList<Object>();
			String value = null;
			Row row = null;
			Cell cell = null;
			for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				List<Object> linked = new ArrayList<Object>();
				for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					if (cell == null) {
						linked.add("");
						continue;
					}
					value = ExcelGeneral.getCellValue(cell);
					linked.add(value);

				}
				link.add(linked);
			}
			list.put(tables[s],link);
		}
		

		return list;
	}
	public List<List<Object>> read2003Excel(File file) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
//		System.out.println("璇诲彇office 2003 excel鍐呭濡備笅锛�);
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					linked.add("");
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 鏍煎紡鍖�number String
				// 瀛楃
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");// 鏍煎紡鍖栨棩鏈熷瓧绗︿覆
				DecimalFormat nf = new DecimalFormat("0");// 鏍煎紡鍖栨暟瀛�
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					// System.out.println(i + "琛� + j + " 鍒�is String type");
					value = cell.getStringCellValue();
//					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					// System.out.println(i + "琛� + j
					// + " 鍒�is Number type ; DateFormt:"
					// + cell.getCellStyle().getDataFormatString());
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
//					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					// System.out.println(i + "琛� + j + " 鍒�is Boolean type");
					value = cell.getBooleanCellValue();
//					System.out.print("  " + value + "  ");
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					// System.out.println(i + "琛� + j + " 鍒�is Blank type");
					value = "";
//					System.out.print("  " + value + "  ");
					break;
				default:
					// System.out.println(i + "琛� + j + " 鍒�is default type");
					value = cell.toString();
//					System.out.print("  " + value + "  ");
				}
				if (value == null || "".equals(value)) {
					value="";
//					System.out.print("  " + value + "  ");
					//continue;
				}
				linked.add(value);

			}
//			System.out.println("");
			list.add(linked);
		}

		return list;
	}

	@Override
	public void export07() throws FileNotFoundException, IOException {
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet();// 创建一个工作薄对象

		sheet.setColumnWidth(0, 5000);// 设置第二列的宽度为
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10, 3000);
		sheet.setColumnWidth(11, 3000);
		sheet.setColumnWidth(12, 3000);
		sheet.setColumnWidth(13, 3000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 3000);
		sheet.setColumnWidth(16, 3000);
		sheet.setColumnWidth(17, 3000);
		sheet.setColumnWidth(18, 3000);
		sheet.setColumnWidth(19, 3000);
		sheet.setColumnWidth(20, 3000);
		sheet.setColumnWidth(21, 3000);
		sheet.setColumnWidth(22, 3000);
		sheet.setColumnWidth(23, 3000);
		sheet.setColumnWidth(24, 3000);
		sheet.setColumnWidth(25, 3000);
		sheet.setColumnWidth(26, 3000);
		sheet.setColumnWidth(27, 3000);
		sheet.setColumnWidth(28, 3000);
		sheet.setColumnWidth(29, 3000);
		sheet.setColumnWidth(30, 3000);
		sheet.setColumnWidth(31, 3000);
		sheet.setColumnWidth(32, 3000);
		sheet.setColumnWidth(33, 3000);
		XSSFRow row = sheet.createRow(0);// 创建一个行对象

		row.setHeightInPoints(63);// 设置行高23像素

		XSSFCellStyle style = workBook.createCellStyle();// 创建样式对象

		// 设置字体

		XSSFFont font = workBook.createFont();// 创建字体对象

		font.setFontHeightInPoints((short) 12);// 设置字体大小

		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体

		font.setFontName("黑体");// 设置为黑体字

		style.setFont(font);// 将字体加入到样式对象

		// 设置对齐方式

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中

		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中

		// 设置边框

		style.setBorderTop(HSSFCellStyle.BORDER_THICK);// 顶部边框粗线

		style.setTopBorderColor(HSSFColor.RED.index);// 设置为红色

		style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);// 底部边框双线

		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左边边框

		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右边边框

		// 格式化日期

		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

		XSSFCell cell = row.createCell(0);// 创建单元格
		cell.setCellValue("Channel");// 写入当前日期
		cell.setCellStyle(style);// 应用样式对象
		XSSFCell cell1 = row.createCell(1);
		cell1.setCellValue("Publisher");
		cell1.setCellStyle(style);
		XSSFCell cell2 = row.createCell(2);
		cell2.setCellValue("Title");
		cell2.setCellStyle(style);
		XSSFCell cell3 = row.createCell(3);
		cell3.setCellValue("Author");
		cell3.setCellStyle(style);
		XSSFCell cell4 = row.createCell(4);
		cell4.setCellValue("eISBN");
		cell4.setCellStyle(style);
		XSSFCell cell5 = row.createCell(5);
		cell5.setCellValue("Product Format");
		cell5.setCellStyle(style);
		XSSFCell cell6 = row.createCell(6);
		cell6.setCellValue("City");
		cell6.setCellStyle(style);
		XSSFCell cell7 = row.createCell(7);
		cell7.setCellValue("PO Country (Code)");
		cell7.setCellStyle(style);
		XSSFCell cell8 = row.createCell(8);
		cell8.setCellValue("State/Region");
		cell8.setCellStyle(style);
		XSSFCell cell9 = row.createCell(9);
		cell9.setCellValue("Postal Code");
		cell9.setCellStyle(style);
		XSSFCell cell10 = row.createCell(10);
		cell10.setCellValue("PO Date");
		cell10.setCellStyle(style);
		XSSFCell cell11 = row.createCell(11);
		cell11.setCellValue("Units Sold");
		cell11.setCellStyle(style);
		XSSFCell cell12 = row.createCell(12);
		cell12.setCellValue("Unites Returned");
		cell12.setCellStyle(style);
		XSSFCell cell13 = row.createCell(13);
		cell13.setCellValue("Returned Zip Code");
		cell13.setCellStyle(style);
		XSSFCell cell14 = row.createCell(14);
		cell14.setCellValue("Net Sold Units");
		cell14.setCellStyle(style);
		XSSFCell cell15 = row.createCell(15);
		cell15.setCellValue("List Price");
		cell15.setCellStyle(style);
		XSSFCell cell16 = row.createCell(16);
		cell16.setCellValue("List Price Currency");
		cell16.setCellStyle(style);
		XSSFCell cell17 = row.createCell(17);
		cell17.setCellValue("Foreign Exchange Rate to Payable Currency");
		cell17.setCellStyle(style);
		XSSFCell cell18 = row.createCell(18);
		cell18.setCellValue("Unit Approved Promo Discount");
		cell18.setCellStyle(style);
		XSSFCell cell19 = row.createCell(19);
		cell19.setCellValue("Actual Customer  Unit Price");
		cell19.setCellStyle(style);
		XSSFCell cell20 = row.createCell(20);
		cell20.setCellValue("Actual Customer Price Currency");
		cell20.setCellStyle(style);
		XSSFCell cell21 = row.createCell(21);
		cell21.setCellValue("Sales Tax");
		cell21.setCellStyle(style);
		XSSFCell cell22 = row.createCell(22);
		cell22.setCellValue("Marketing Purpose");
		cell22.setCellStyle(style);
		XSSFCell cell23 = row.createCell(23);
		cell23.setCellValue("Total Approved Promo Discount");
		cell23.setCellStyle(style);
		XSSFCell cell24 = row.createCell(24);
		cell24.setCellValue("Total Payment");
		cell24.setCellStyle(style);
		XSSFCell cell25 = row.createCell(25);
		cell25.setCellValue("Payment Currency");
		cell25.setCellStyle(style);
		XSSFCell cell26 = row.createCell(26);
		cell26.setCellValue("COGS %");
		cell26.setCellStyle(style);
		XSSFCell cell27 = row.createCell(27);
		cell27.setCellValue("Partner Share with Channel");
		cell27.setCellStyle(style);
		XSSFCell cell28 = row.createCell(28);
		cell28.setCellValue("Revenue to Trajectory");
		cell28.setCellStyle(style);
		XSSFCell cell29 = row.createCell(29);
		cell29.setCellValue("Revenue to Trajectory Currency");
		cell29.setCellStyle(style);
		XSSFCell cell30 = row.createCell(30);
		cell30.setCellValue("Revenue to Trajectory in USD");
		cell30.setCellStyle(style);
		XSSFCell cell31 = row.createCell(31);
		cell31.setCellValue("Partner Share with Publisher");
		cell31.setCellStyle(style);
		XSSFCell cell32 = row.createCell(32);
		cell32.setCellValue("Revenue to Publisher in USD");
		cell32.setCellStyle(style);
		XSSFCell cell33 = row.createCell(33);
		cell33.setCellValue("Sale Month");
		cell33.setCellStyle(style);

		/*
		 * for (int i = 0; i < listSalesdetails.size(); i++) { //row =
		 * sheet.createRow((int) i + 1); Salesdetails salesdetails =
		 * (Salesdetails) listSalesdetails.get(i); // 第四步，创建单元格，并设置值
		 * row.createCell((short) i).setCellValue((double)
		 * salesdetails.getId()); row.createCell((short)
		 * i).setCellValue(salesdetails.getName()); row.createCell((short)
		 * i).setCellValue((double) salesdetails.getAge()); }
		 */
		// 文件输出流

		FileOutputStream os = new FileOutputStream("D:/style_2007.xlsx");

		workBook.write(os);// 将文档对象写入文件输出流

		os.close();// 关闭文件输出流
		System.out.println("创建成功 office 2007 excel");

	}

}
