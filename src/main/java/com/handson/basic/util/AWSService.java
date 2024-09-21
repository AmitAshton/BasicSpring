package com.handson.basic.util;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.UUID;
import org.apache.commons.io.FileUtils;

@Service
public class AWSService {

    @Value("${bucket.url}")
    String bucket;

    @Autowired
    private AmazonS3 s3Client;

    private static final Logger logger = LoggerFactory.getLogger(AWSService.class);

    public void putInBucket(MultipartFile file, String path) {
        try {
            Path tempFile = Files.createTempFile("file" + UUID.randomUUID(), null);
            FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile.toFile());
            saveAndSend( tempFile, path);
            Files.deleteIfExists(tempFile);
        } catch (Exception e) {
            logger.error("Error uploading file to bucket: " + bucket + "/ " + path, e);
        }
    }


    private void saveAndSend( Path uploadFile, String destPath) throws IOException {
         PutObjectRequest request = new PutObjectRequest(bucket, destPath, uploadFile.toFile());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/png");
        request.setMetadata(metadata);
        s3Client.putObject(request);
    }

    public String generateLink(String fileUrl) {
        return generateLink(bucket, fileUrl);
    }

    public String generateLink(String bucketName, String fileUrl) {
        // Set the presigned URL to expire after one min.
        if (fileUrl == null) return null;
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        return generateLink(bucketName, fileUrl, expTimeMillis);
    }
    public String generateLink(String bucketName, String fileUrl, long expTimeMillis) {
        if (fileUrl.lastIndexOf(bucketName) >= 0) {
            fileUrl = fileUrl.substring(fileUrl.lastIndexOf(bucketName) + bucketName.length() + 1);
        }
        try {
            Date expiration = new Date();
            expiration.setTime(expTimeMillis);

            // Generate the presigned URL.
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucketName, fileUrl)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(expiration);
            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
            return url.toString();
        } catch (Exception e) {
            logger.error("Error generating presigned link", e);
        }
        return null;
    }

}
