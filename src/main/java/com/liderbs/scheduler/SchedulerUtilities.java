package com.liderbs.scheduler;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.liderbs.modelo.Specialclass;
import com.liderbs.presentation.businessDelegate.IBusinessDelegatorView;

public class SchedulerUtilities {

	private static Logger logger = Logger.getLogger(SchedulerUtilities.class.getName());

	@Autowired
	private IBusinessDelegatorView businessDelegatorView;

	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	SimpleDateFormat formatEspecial = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat formatComparador = new SimpleDateFormat("yyyy-MM-dd");

	public void verificarClasesJob() throws Exception {
		
		Date current = new Date();
		
		Calendar currentDate = Calendar.getInstance();
		Calendar classDate = Calendar.getInstance();
		
		//Buscar las clases fijas
		List<Specialclass> list = businessDelegatorView.findByCriteriaInSpecialclass(new Object[]{"dayWeek",false, 0, "!=",},
																					 null,
																					 null);
		
		int weekDay = 0;
		
		for(Specialclass clase:list){
			
			//Si la clase ya se vencio
			if(clase.getFecha().before(current)){
				
				//Asignar la fecha de la clase a un dia
				classDate.setTime(clase.getFecha());
				//Incrementar a la siguiente semana
				classDate.add(Calendar.DAY_OF_YEAR, 7);
				//Buscar la clase a actualizar
			    Specialclass specClass = businessDelegatorView.getSpecialclass(clase.getIdspecialclass());
			    //Actualizar la fecha
			    specClass.setFecha(classDate.getTime());
			    //Actualizar la clase
			    businessDelegatorView.updateSpecialclass(specClass);
			    
			}
		}//end for
		
	}
	
	public String convertTime(long time) {
		Date date = new Date(time);
		Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
		return format.format(date);
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

}
