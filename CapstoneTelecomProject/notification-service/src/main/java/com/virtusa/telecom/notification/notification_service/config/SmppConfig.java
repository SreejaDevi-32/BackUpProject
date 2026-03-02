//package com.virtusa.telecom.notification.notification_service.config;
//
//
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.*;
//
//@Configuration
//@ConditionalOnProperty(
//        name = "smpp.enabled",
//        havingValue = "true",
//        matchIfMissing = false
//)
//@Getter
//public class SmppConfig {
//	/*
//
//    @Value("${smpp.host}")
//    private String host;
//
//    @Value("${smpp.port}")
//    private int port;
//
//    @Value("${smpp.system-id}")
//    private String systemId;
//
//    @Value("${smpp.password}")
//    private String password;
//
//    @Bean
//    @Scope("prototype") // important for new session per request
//    public SMPPSession smppSession() throws Exception {
//
//        SMPPSession session = new SMPPSession();
//
//        session.connectAndBind(
//                host,
//                port,
//                new BindParameter(
//                        BindType.BIND_TX,
//                        systemId,
//                        password,
//                        "",
//                        TypeOfNumber.UNKNOWN,
//                        NumberingPlanIndicator.UNKNOWN,
//                        null
//                )
//        );
//
//        return session;
//    }
//    */
//}