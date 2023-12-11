package com.oio.compositionservice.module;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class Decoder {

    public String decode(HttpServletRequest request){
        String token = request.getHeader("access-token");
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey("example_token")
                .parseClaimsJws(token);

        Claims claims = claimsJws.getBody();
        String nickname = claims.getSubject();

        return nickname;
    }

}
