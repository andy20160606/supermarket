package cn.youguang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtil {


	public static synchronized String getOrderNo() {
		String str = new SimpleDateFormat("yyyy-MMdd-HHmm").format(new Date());
		return str;

	}
	public static synchronized String getOrderNoHasSSS() {
		String str = new SimpleDateFormat("yyyy-MMdd-HHmm-ssSSS").format(new Date());
		return str;

	}

}
