package com.AristoPets.aws;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;


@Service
public class AmazonS3UploadService {

    private static final Base64.Decoder decoder = Base64.getMimeDecoder();
    private AmazonS3 amazonS3Client;

    public AmazonS3UploadService() {
        amazonS3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }

    public String uploadImage(String base64Image, String bucketName) {
        String contentType = base64Image.substring(5, base64Image.indexOf(";"));  //data:content-type
        base64Image = base64Image.substring(base64Image.indexOf(",") + 1);
        byte[] decodedImage = decoder.decode(base64Image);
        InputStream byteStream = new ByteArrayInputStream(decodedImage);
        String fileName = UUID.randomUUID().toString();

        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(decodedImage.length);
        metaData.setContentType(contentType);
        metaData.setCacheControl("public");

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, byteStream, metaData);
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3Client.putObject(putObjectRequest);
        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }
}
