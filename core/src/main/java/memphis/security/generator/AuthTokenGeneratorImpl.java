package memphis.security.generator;

import memphis.security.pojo.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthTokenGeneratorImpl implements AuthTokenGenerator {

    private final JwtConfig jwtConfig;

    public AuthTokenGeneratorImpl(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public String generate() {
        Date currentDate = new Date();
        return Jwts.builder()
                .setIssuedAt(currentDate)
                .setExpiration(getTokenExpirationDate(currentDate))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret().getBytes())
                .compact();

    }

    public Date getTokenExpirationDate(Date currentDate) {
        return DateUtils.addSeconds(currentDate, jwtConfig.getExpiration());
    }

}
