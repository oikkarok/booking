package booking.rest.security;

import java.util.List;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);
    private static final String issuer = "booking.rest";

    /**
     * Create jwt string.
     *
     * @param subject       the subject
     * @param payloadClaims the payload claims
     * @return the JWT string
     */
    public static String createJwt(String subject, List<Entry> payloadClaims) {
       
	JWTCreator.Builder builder =  JWT.create()
                .withSubject(subject)
                .withIssuer(issuer)
                .withIssuedAt(DateTime.now().toDate())
                .withExpiresAt(DateTime.now().plusMinutes(10).toDate());

        if (payloadClaims != null && !payloadClaims.isEmpty()) {
            
            for (Entry entry : payloadClaims) {
                builder.withClaim(entry.getKey().toString(), entry.getValue().toString());
            }
            
        } else {
            log.warn("You are building a JWT without header claims!");
        }
        
        return builder.sign(Algorithm.HMAC256(SecurityConfig.secret));
    }

    /**
     * Verify jwt decoded.
     *
     * @param jwt the JWT string
     * @return the decoded JWT
     */
    public static DecodedJWT verifyJwt(String jwt) {
        return JWT.require(Algorithm.HMAC256(SecurityConfig.secret)).build().verify(jwt);
    }

    private JwtProvider() {}
}
