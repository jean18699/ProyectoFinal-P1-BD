package Logico;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Statement;

import Connection.DBConnection;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class JasperTest {

	public static void main(String[] args) throws JRException, ClassNotFoundException, SQLException {
		
		/*JasperReport jasperReport = JasperCompileManager.compileReport(new File("").getAbsolutePath()+"\\src\\Logico\\ReporteContrato2.jrxml");
		
		JRDataSource jrDataSource = new JREmptyDataSource();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrDataSource);
				
		JasperExportManager.exportReportToPdfFile(jasperPrint,new File("").getAbsolutePath()+"\\src\\Logico\\simpleReport.pdf");*/
		
		/* JasperReport is the object
		that holds our compiled jrxml file */
		JasperReport jasperReport;


		/* JasperPrint is the object contains
		report after result filling process */
		JasperPrint jasperPrint;
		
		HashMap jasperParameter = new HashMap();
		
		jasperReport = JasperCompileManager.compileReport
				(new File("").getAbsolutePath()+"\\src\\Logico\\ReporteContrato2.jrxml");
		
		jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, DBConnection.getInstance().getConnectionn()); 
		
		JasperExportManager.exportReportToPdfFile(jasperPrint, new File("").getAbsolutePath()+"\\src\\Logico\\simpleReport.pdf");
		
		System.out.printf("Done");
		
	}

}
