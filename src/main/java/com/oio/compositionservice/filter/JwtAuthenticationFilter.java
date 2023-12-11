//package com.oio.compositionservice.filter;
//
//import io.jsonwebtoken.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        // 헤더에서 토큰 추출
//        String header = request.getHeader("token");
//        if (header != null) {
//            try {
//                String token = header;
//                Jws<Claims> claimsJws = Jwts.parser()
//                        .setSigningKey("example_token")
//                        .parseClaimsJws(token);
//
//                Claims claims = claimsJws.getBody();
//                String username = claims.getSubject();
//
//
//                if (username != null) {
//                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, null);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//
//                }
//
//            }catch(ExpiredJwtException e){
//
//            }catch (SignatureException e) {
//                e.printStackTrace();
//                throw new SignatureException("유효하지 않은 토큰값입니다.");
//            }catch (Exception e) {
//                // 토큰이 유효하지 않은 경우 또는 예외 발생 시 처리
//                e.printStackTrace();
//
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//
//}
