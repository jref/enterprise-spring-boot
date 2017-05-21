package com.ua.codespace.service.impl;

import com.ua.codespace.config.AmazonConfigProperties;
import com.ua.codespace.service.PhotoService;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class AmazonPhotoServiceImpl implements PhotoService {

    @Autowired
    private AmazonConfigProperties awsProperties;

    @Override
    public String saveUserPhoto(String username, MultipartFile image) {
        return savePhoto(username, image, awsProperties.getBucketPath(), awsProperties.getUserUri());
    }

    private String savePhoto(String username, MultipartFile image, String path, String uri) {//todo: split and improve
        AWSCredentials awsCredentials = new AWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey());
        S3Service s3Service;
        s3Service = new RestS3Service(awsCredentials);

        S3Bucket bucket = null;
        try {
            bucket = s3Service.getBucket(awsProperties.getBucketName());
        } catch (S3ServiceException e) {
            System.out.println("Invalid Bucket name");
            e.printStackTrace();
            return null;
        }

        String objectName = uri + "/" + username + "/" + image.getOriginalFilename();
        S3Object imageObject = new S3Object(objectName);

        try {
            imageObject.setDataInputStream(image.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Image input stream error!");
            return null;
        }

        imageObject.setContentLength(image.getSize());
        imageObject.setContentType(image.getContentType());

        AccessControlList acl = new AccessControlList();
        acl.setOwner(bucket.getOwner());
        acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
        imageObject.setAcl(acl);

        try {
            s3Service.putObject(bucket, imageObject);
        } catch (S3ServiceException e) {
            e.printStackTrace();
            System.out.println("Image saving error!");
            return null;
        }

        String link = path + objectName;
        return link;
    }
}
