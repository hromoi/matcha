package com.school.matcha;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

	@Test
	void test() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int num1 = Integer.parseInt(reader.readLine());
		int num2 = Integer.parseInt(reader.readLine());
		System.out.println(num1 + num2);
	}

}
