package com.invictusdynamics.bookie.filter;

import java.io.IOException;

import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.invictusdynamics.bookie.utility.Constants;

/**
 * @author Amol K Golhar
 * @since 12 April 2018
 *
 */
public class CrossOrigin implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		/*if(logger.isDebugEnabled()){
			logger.debug("In CrossOrigin.doFilter Method");
		}*/
		
		HttpServletResponse r = (HttpServletResponse) response;
	  
		ResourceBundle rs = ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE_NAME);
		String originUrl1 =rs.getString("originUrl");
	    r.addHeader("Access-Control-Allow-Origin", originUrl1);

	    r.addHeader("Access-Control-Allow-Headers",
	            "Accept,Accept-Encoding,Accept-Language,Cache-Control,"
	            + "Connection,Content-Length,Content-Type," +
	            "Cookie,Host,Pragma,Referer,RemoteQueueID,User-Agent"
	    		);
	    r.addHeader("Access-Control-Allow-Credentials", "true");
	    r.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT");
	    chain.doFilter(request, response);
	    
	    /*if(logger.isDebugEnabled()){
			logger.debug("Out CrossOrigin.doFilter Method");
		}*/
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
