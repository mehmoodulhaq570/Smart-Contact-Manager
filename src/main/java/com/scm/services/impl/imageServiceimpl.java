package com.scm.services.impl;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.scm.services.imageService;

@Service
public class imageServiceimpl implements imageService {

    private Cloudinary cloudinary;
    public imageServiceimpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage) {
        // Upload the image to the server
        String filename = UUID.randomUUID().toString();

        try {
            byte[] data = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap("public_id", filename));

        } catch (IOException e) {
            
            e.printStackTrace();
        }

        // return the URL of the uploaded image
        return ""; // Placeholder return statement
    }

}
