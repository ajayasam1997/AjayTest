package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
    String path;

	// Constructor
	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String xlFile, String xlSheet) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();

		return rowCount;
	}

	public int getCellCount(String xlFile, String xlSheet, int roNum) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(roNum);

		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();

		return cellCount;
	}

	public String getCellData(String xlFile, String xlSheet, int roNum, int cellNum) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(roNum);

		cell = row.getCell(cellNum);

		String data;
		try {

			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);
			return data;

		} catch (Exception e) {
			// data is equal to nothing if anything is not there in cell
			data = "";
		}
		wb.close();
		fi.close();

		return data;
	}

	public static void setCellData(String xlFile, String xlSheet, int roNum, int cellNum, String data)
			throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(roNum);
		cell = row.createCell(cellNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlFile);

		wb.write(fo);

		wb.close();
		fi.close();
		fo.close();

	}

	public static void fillGreenColor(String xlFile, String xlSheet, int roNum, int cellNum) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(roNum);

		cell = row.getCell(cellNum);

		style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		fo = new FileOutputStream(xlFile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	public static void fillRedColor(String xlFile, String xlSheet, int roNum, int cellNum) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(roNum);

		cell = row.getCell(cellNum);

		style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		fo = new FileOutputStream(xlFile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
}
