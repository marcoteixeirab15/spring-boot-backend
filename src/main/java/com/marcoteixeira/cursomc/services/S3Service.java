package com.marcoteixeira.cursomc.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    private final AmazonS3 s3Client;
    private final Logger log = LoggerFactory.getLogger(S3Service.class);
    @Value("${s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public URI uploadFile(MultipartFile multipartFile){
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream inputStream = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            return uploadFile(inputStream, fileName, contentType);
        } catch (IOException e) {
            throw new RuntimeException("erro de io " + e);
        }
    }

    public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
        try {

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);

            log.info("Iniciando upload");
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata));
            log.info("finalizando upload");
            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Erro ao converte Url");
        }
    }
}
