package com.example.zuul.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class PreFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(PreFilter.class);

	//Se ejecuta el filtro al hacer una solicitud http
	@Override
	public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		 return 1;
	}

	@Override
	public String filterType() {
		// Establecemos un pre filter
		 return "pre";
	}



}
