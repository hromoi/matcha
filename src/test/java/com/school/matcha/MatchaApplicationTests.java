package com.school.matcha;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MatchaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void extractUsername() {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey("natalia").parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjExNjUzMDUwfQ.Yr5akjofsxRVFXsUufIho8ghG-1ANe6lW-QLgSo_YzA");
		String username = claimsJws.getBody().getSubject();
		String signature = claimsJws.getSignature();
		JwsHeader header = claimsJws.getHeader();
	}

}
