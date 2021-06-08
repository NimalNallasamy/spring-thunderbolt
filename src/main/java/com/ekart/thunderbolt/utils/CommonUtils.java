package com.ekart.thunderbolt.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CommonUtils {

    public static boolean isStringEmpty(boolean includeNullStringAlso, String ... parameters){

        for(String param : parameters){
            if(param == null || param.length() <= 0){
                return false;
            }
            else if(includeNullStringAlso && "null".equals(param))
            {
                return false;
            }
        }

        return true;
    }

    public static Double roundOffDouble(Double rate){
        return roundOffDouble(rate, null);
    }
    public static Double roundOffDouble(Double rate, String field)
    {
        if(rate != null) {
            return (Math.round(rate * 100.0) / 100.0);
        }
        else if(field != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty Data provided for the field : "+field);
        }
        else{
            throw new  ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public static Object compareAndReturn(Object oldValue, Object newValue){

        if(newValue == null){
            return oldValue;
        }

        if(newValue instanceof String){
            if(CommonUtils.isStringEmpty(true, newValue.toString()) && !oldValue.equals(newValue)){
                return newValue.toString();
            }
            else{
                return oldValue.toString();
            }
        }

        if(newValue instanceof Double){
            if(((Double)newValue == 0.0) && oldValue != newValue){
                return oldValue;
            }
            else{
                return roundOffDouble((Double)newValue);
            }
        }

        return newValue;
    }

}
