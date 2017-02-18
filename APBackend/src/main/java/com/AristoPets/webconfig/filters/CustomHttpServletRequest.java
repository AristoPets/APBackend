package com.AristoPets.webconfig.filters;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;


final class CustomHttpServletRequest extends HttpServletRequestWrapper {

    private final Map<String,String> customHeaders;

    public CustomHttpServletRequest(HttpServletRequest request) {
        super(request);
        customHeaders = new HashMap<String,String>();
    }

    public void putHeader(String key, String value){
        this.customHeaders.put(key,value);
    }

    public String getHeader(String key){
        String header = customHeaders.get(key);
        if(header != null){
            return header;
        }

        return ((HttpServletRequest)getRequest()).getHeader(key);
    }
}
