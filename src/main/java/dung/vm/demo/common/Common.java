package dung.vm.demo.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
	public static Date stringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);
        Date dateReturn = new Date();
        try {
            dateReturn = dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateReturn;
    }
	
//	public static String getUsernameLogin() {
//        return SecurityContextHolder.getContext().getAuthentication().getName();
//    }

	public static Date getSystemDate() {
		return new Date();
	}
}
