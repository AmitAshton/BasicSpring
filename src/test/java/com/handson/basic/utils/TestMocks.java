package com.handson.basic.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.handson.basic.util.AWSService;
import com.handson.basic.util.SmsService;
import org.apache.commons.io.IOUtils;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@TestConfiguration
public class TestMocks {


    @Bean
    @Primary
    public AmazonS3 s3Client() throws IOException {
        AmazonS3 mockS3 = Mockito.mock(AmazonS3.class);
//        S3Object mockS3Object = Mockito.mock(S3Object.class);
//        when(mockS3.getObject(anyString(), anyString())).thenReturn(mockS3Object);
//        S3ObjectInputStream[] streams = new S3ObjectInputStream[200];
//        S3ObjectInputStream first = new S3ObjectInputStream(new ByteArrayInputStream(IOUtils.resourceToByteArray("sleep.zip", getClass().getClassLoader())) , null);
//        for (int i = 0; i < streams.length; i++) {
//            streams[i] = new S3ObjectInputStream(new ByteArrayInputStream(IOUtils.resourceToByteArray("sleep.zip", getClass().getClassLoader())), null);
//        }
//        when(mockS3Object.getObjectContent()).thenReturn(first, streams);
//        ObjectListing listing = Mockito.mock(ObjectListing.class);
//        Mockito.when(mockS3.listObjects(Mockito.any(), Mockito.any())).thenReturn(listing);
//        Mockito.when(listing.getObjectSummaries()).thenReturn(new ArrayList<>());

        return mockS3;

    }

    @Bean
    @Primary
    public AuthenticationManager authenticationManager() {
        var res =  Mockito.mock(AuthenticationManager.class);
        var auth = Mockito.mock(Authentication.class);
        Mockito.when(res.authenticate(any())).thenReturn(auth);
        return res;
    }


    @Bean
    @Primary
    public SmsService smsService() {
        SmsService service = Mockito.mock(SmsService.class);
        return service;
    }
    @Bean
    @Primary
    public AWSService awsService() {
        AWSService service = Mockito.mock(AWSService.class);
        return service;
    }
}
