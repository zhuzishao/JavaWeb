package utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    public  static <T> T copyParamToBean(Map value, T bean) throws InvocationTargetException, IllegalAccessException {
        System.out.println("注入之前"+bean);
        BeanUtils.populate(bean,value);
        System.out.println("注入之后"+bean);
        return bean;
    }

    public static  int parseInt(String strInt,int defaultValue){
//        return Integer.parseInt(strInt);
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
     }
}
