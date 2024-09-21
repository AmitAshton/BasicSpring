package com.handson.basic.util;


import okhttp3.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


// copied from documentation https://www.sms4free.co.il/outcome-sms-api.html
@Service
public class SmsService {
    protected final Log logger = LogFactory.getLog(getClass());
    OkHttpClient client = new OkHttpClient.Builder().build();

    @Value("${sms4free.key}")
    private String ACCOUNT_KEY;
    @Value("${sms4free.user}")
    private String ACCOUNT_USER;
    @Value("${sms4free.password}")
    private String ACCOUNT_PASS;



    //Overloaded constructor to send a message to both a phone number and an email address.
    public boolean send(String text, String phoneNumber) {

        if (phoneNumber == null) return false;
        MediaType mediaType = MediaType.parse("application/text");
        String url = "https://www.sms4free.co.il/ApiSMS/SendSMS";

        String key = ACCOUNT_KEY;
        String user = ACCOUNT_USER;
        String pass = ACCOUNT_PASS;
        try {

            RequestBody formBody = new FormBody.Builder()
                    .add("key", key)
                    .add("user", user)
                    .add("pass", pass)
                    .add("sender", "HANDSON")
                    .add("recipient", phoneNumber)
                    .add("msg", text)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .addHeader("content-type", "application/json")
                    .addHeader("Accept-Language", "en-US,en;q=0.5")
                    .build();

            String data = client.newCall(request).execute().body().string();
            //print result
            boolean success = Integer.parseInt(data) > 0;

            return success;
        }catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
