package com.example.zuul.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.zuul.domain.User;
import com.example.zuul.jwt.JwtTokenUtil;
import com.example.zuul.service.UserService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class PreFilter extends ZuulFilter {
	
		@Autowired UserService userService;
	
	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;
	   
	    private String tokenHeader="Authorization";
	
	private static Logger log = LoggerFactory.getLogger(PreFilter.class);

	//Se ejecuta el filtro al hacer una solicitud http
	@Override
	public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        String authToken=request.getHeader(tokenHeader);//Interceptamos el token del header de la solicitud http
        String username = jwtTokenUtil.getUsernameFromToken(authToken); //verificamos el usuario
        log.info(authToken);
        log.info(username);
      //Si el token no es válido entonces pasa por este filtro
       if (username==null) {
            ctx.set("error.status_code", HttpStatus.UNAUTHORIZED.value());
            throw new RuntimeException("Not Authorized");
       }
       User user=userService.findByUserName(username); //en algún momento deberás refactorizar para cumpli con DRY (dont repeat yourself)
      /*Considera ver el tema de la expiración de los tokens!
       if(user==null || !jwtTokenUtil.validateToken(authToken, user)) {
           ctx.set("error.status_code", HttpStatus.UNAUTHORIZED.value());
           throw new RuntimeException("Not Authorized");
       }*/
       if(user==null) {
           ctx.set("error.status_code", HttpStatus.UNAUTHORIZED.value());
           throw new RuntimeException("Not Authorized");}
  
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
