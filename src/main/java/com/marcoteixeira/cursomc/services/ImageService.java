package com.marcoteixeira.cursomc.services;

import com.marcoteixeira.cursomc.services.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
        String extensaoImage = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());

        if (!"png".equals(extensaoImage) && !"jpg".equals(extensaoImage)){
            throw new FileException("Somente arquivos PNG e JPG são permitidos.");
        }

        try {
            BufferedImage image = ImageIO.read(uploadedFile.getInputStream());
            if("png".equals(extensaoImage)){
                image = pngToJpg(image);
            }
            return image;
        }catch (IOException e) {
            throw new FileException("Erro ao processar arquivo");
        }
    }

    public BufferedImage pngToJpg(BufferedImage image) {
        BufferedImage jpgImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(image, 0 ,0, Color.WHITE, null);
        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage bufferedImage, String extension){
        try{
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        }catch (IOException e){
            throw new FileException("Erro ao ler arquivo");
        }
    }

}
