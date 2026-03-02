package com.virtusa.telecom.notification.notification_service.service;

import org.springframework.stereotype.Service;

/*import org.jsmpp.bean.Alphabet;
import org.jsmpp.bean.BindType;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.GeneralDataCoding;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.virtusa.telecom.notification.notification_service.dto.NotificationRequest;
*/
@Service("SMS")
public class SmsNotificationService {
/*implements NotificationService {

    @Value("${smpp.host}")
    private String host;

    @Value("${smpp.port}")
    private int port;

    @Value("${smpp.systemId}")
    private String systemId;

    @Value("${smpp.password}")
    private String password;

    @Override
    @Async("notificationExecutor")
    public void send(NotificationRequest request) {

        SMPPSession session = new SMPPSession();

        try {
            session.connectAndBind(
                    host,
                    port,
                    new BindParameter(
                            BindType.BIND_TX,
                            systemId,
                            password,
                            "",
                            TypeOfNumber.UNKNOWN,
                            NumberingPlanIndicator.UNKNOWN,
                            null
                    )
            );

            session.submitShortMessage(
                    "CMT",
                    TypeOfNumber.UNKNOWN,
                    NumberingPlanIndicator.UNKNOWN,
                    "SENDER",
                    TypeOfNumber.INTERNATIONAL,
                    NumberingPlanIndicator.ISDN,
                    request.getRecipient(),
                    new ESMClass(),
                    (byte) 0,
                    (byte) 1,
                    null,
                    null,
                    new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT),
                    (byte) 0,
                    new GeneralDataCoding(Alphabet.ALPHA_DEFAULT),
                    (byte) 0,
                    request.getMessage().getBytes()
            );

        } catch (Exception e) {
            throw new RuntimeException("SMS sending failed");
        } finally {
            session.unbindAndClose();
        }
    }
    */
}