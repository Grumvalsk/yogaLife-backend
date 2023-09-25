package com.grumvalski.yogaLifebackend.config;

import com.grumvalski.yogaLifebackend.utils.CrypLocal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyClass {

    @Bean
    public CrypLocal localCryptor() {
        final char[] ca1 = { 'w', 'O', '2', '*', '&', 'q', '1', 'Z', 'Y', 'M', '-', ')', 'S', 'x', '1', 'r' };
        final char[] ca2 = { '^', 'Q', '1', '?', 'w', 'c', 'A', 'J', 'n', 'b', 'P', '0', '£', 'q', '4', 'h' };
        CrypLocal.init(new String(ca1), new String(ca2));

        return CrypLocal.getInstance();
    }
}
