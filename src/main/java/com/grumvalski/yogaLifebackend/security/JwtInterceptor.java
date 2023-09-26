package com.grumvalski.yogaLifebackend.security;


import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            String jwt=null;
            if(request.getHeader("Authorization")==null)
                 throw new Exception("Jwt Assente");

            jwt=request.getHeader("Authorization").replace("Bearer","").trim();



            if(!(JwtCreator.verifyJwt(jwt))) {
                throw new Exception();
            }
            String url= String.valueOf(request.getRequestURL());
            if(url.contains("/event/create") || url.contains("/event/delete")){
                JWTClaimsSet claim = JwtCreator.decodeJwt(jwt);
                String role= claim.getClaim("role").toString();
                if(!(role.equals("ADMIN"))){
                    throw new Exception("utente non autorizzato");
                }
                return true;
            }
        }catch (Exception ex){
            throw new Exception(ex.getMessage());

        }

        return true;
    }
}
