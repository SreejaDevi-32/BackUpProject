package com.virtusa.telecom.utility.commonlib;

import javax.crypto.SecretKey;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "hash.secret")
public class HashUtil {

    private static final String ALGO = "HmacSHA256";
    
    private final HashProperties properties;
    
    private SecretKeySpec keySpec;
    
    @PostConstruct
    void init() {
        byte[] keyBytes = Base64.getDecoder().decode(properties.getSecret());
        this.keySpec = new SecretKeySpec(keyBytes, ALGO);
    }

    public String hash(String value) {
        try {
            Mac mac = Mac.getInstance(ALGO);
            mac.init(keySpec);
            return Base64.getEncoder()
                    .encodeToString(mac.doFinal(
                        value.toLowerCase().trim().getBytes(StandardCharsets.UTF_8)
                    ));
        } catch (Exception e) {
            throw new IllegalStateException("Hashing failed", e);
        }
    }
}

