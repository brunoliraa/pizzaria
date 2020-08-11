package br.edu.exemploPizzaria.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PayPalConfig {

//    //setando os valores do properties
//    @Value("${paypal.client.id}")
//    private String clientId;
//    @Value("${paypal.client.secret}")
//    private String clientSecret;
//    @Value("${paypal.mode}")
//    private String mode;
//
//    //setando o mode do propeties
//    @Bean
//    public Map<String, String> paypalSdkConfig() {
//        Map<String, String> configMap = new HashMap<>();
//        configMap.put("mode", mode);
//        return configMap;
//    }
////o método paypalsdkconfig ta retornando o mode pro authtoken
//    @Bean
//    public OAuthTokenCredential oAuthTokenCredential() {
//        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
//    }
//
//    @Bean
//    public APIContext apiContext() throws PayPalRESTException {
//        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
//        context.setConfigurationMap(paypalSdkConfig());
//        return context;
//    }
//
//    //ApiContext is deprecated in latest version of paypal
//    //depois tem que ve a nova forma
}
