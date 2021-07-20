package duong.dev.service;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest request;
	
	public String getString (String name, String defaultValue) {
		String value = request.getParameter(name);
		return value != null? value: defaultValue;
	}
	
	public int getInt(String name, int defaultValue){
		String value = getString(name, String.valueOf(defaultValue));
		return Integer.parseInt(value);
	}
	public double getDouble(String name, double defaultValue) {
		String value = getString(name, String.valueOf(defaultValue));
		return Double.parseDouble(value);
	}
	public boolean getBoolean(String name, boolean defaultValue) {
		String value = getString(name, String.valueOf(defaultValue));
		return Boolean.parseBoolean(value);
	}
	public Date getDate(String name, String pattern) {
		String value =getString(name, "");
		try {
			return new SimpleDateFormat(pattern).parse(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean emailValidate(String email) {
	    Matcher matcher = Pattern.compile("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$", Pattern.CASE_INSENSITIVE).matcher(email);

	    return matcher.find();
	}
	
	public File uploadFile(
			MultipartFile uploadedFile
	) {
		String folderPath = "C:\\Users\\User\\eclipse-workspace\\SpringVsReacjs\\src\\main\\webapp\\WEB-INF\\views\\public\\img";
		 File myUploadFolder = new File(folderPath);
		 
		 if(!myUploadFolder.exists()) {
			 myUploadFolder.mkdirs();
		 }
		 File savedFile = null;
		 try {
//			 String uuid = UUID.randomUUID().toString().substring(3);
//			 String fileName =uuid + "_" + uploadedFile.getOriginalFilename();
			 String fileName =uploadedFile.getOriginalFilename();
			  savedFile = new File(myUploadFolder, fileName);
			 uploadedFile.transferTo(savedFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return savedFile;
	}
	
}
