package com.marcoteixeira.cursomc.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    private Logger log = LoggerFactory.getLogger(S3Service.class);

    @Value("${s3.bucket}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public S3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadFile(String localFilePath){

        try {
            File file = new File(localFilePath);
            log.info("Iniciando upload");
            s3Client.putObject(new PutObjectRequest(bucketName, "teste.jpeg", file));
            log.info("finalizando upload");
        }catch (AmazonServiceException ase){
            log.info("AmazonServiceException: " + ase);
            log.info("Status code: " + ase.getErrorCode());
        }catch (AmazonClientException ace){
            log.info("AmazonClientException: " + ace);
        }

    }


}
