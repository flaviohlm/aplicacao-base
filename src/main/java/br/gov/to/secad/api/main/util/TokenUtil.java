package br.gov.to.secad.api.main.util;

import br.gov.to.secad.api.main.config.MainTokenRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wandyer.silva
 */
@Component
public class TokenUtil {

    private final MainTokenRestClient mainTokenRestClient;

    @Autowired
    public TokenUtil(MainTokenRestClient mainTokenRestClient) {
        this.mainTokenRestClient = mainTokenRestClient;
    }

    public String requestToken(String hashKey) {
        return mainTokenRestClient.requestToken(hashKey);
    }

    public String validateToken(String token) {
        return mainTokenRestClient.validateToken(token);
    }
}
