package com.grumvalski.yogaLifebackend.security;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            String jwt=request.getHeader("Authorization").replace("Bearer","").trim();

            if(jwt.isEmpty())
                throw new Exception("Jwt Assente");

            if(!(JwtCreator.verifyJwt(jwt))) {
                throw new Exception();
            }
        }catch (Exception ex){
            throw new Exception(ex.getMessage());

        }

        return true;
    }
}
