package com.ys.jwt.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * TokenProvider - 토큰의 생성, 토큰의 유효성 검증
 */

@Component
public class TokenProvider implements InitializingBean {
    // InitializingBean은 스프링 빈이 생성될때 호출(여기서는 TokenProvider가 생성될때)

    private static final String AUTHORITES_KEY = "oauth";

    private final String secret;

    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 빈 초기화시 코드 구현
        // 재정의한 이유 -> 빈이 생성되고 주입을 받은후에 secret값을 Base64로 Decode해서 key변수에 할당
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
}
