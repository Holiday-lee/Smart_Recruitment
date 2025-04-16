/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 *
 * @author jiaki
 */
public class JwtUtil {
    
    public static String generateToken(String clientId) {
        return Jwts.builder()
                .setSubject(clientId)
                .signWith(SignatureAlgorithm.HS256, Constants.JWT_SIGNING_KEY)
                .compact();
    }
}