package br.com.zupacademy.valeria.mercadolivre.product.images;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.Date;

public class UploadImages {

    public static String upload(MultipartFile image){
        return generateImageUrl(image.getOriginalFilename());
    }

    private static String generateImageUrl(String originalFilename){
        String imageExtension = FilenameUtils.getExtension(originalFilename);
        String imageName = FilenameUtils.removeExtension(originalFilename);

        Calendar instance = Calendar.getInstance();
        Date date = instance.getTime();

        return String.format("%s%d.%s", imageName, date.hashCode(), imageExtension);
    }
}
