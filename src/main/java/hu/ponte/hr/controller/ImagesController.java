package hu.ponte.hr.controller;


import hu.ponte.hr.services.ImageStore;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

@RestController()
@RequestMapping("api/images")
public class ImagesController {

    @Autowired
    private ImageStore imageStore;

    @GetMapping("meta")
    public List<ImageMeta> listImages() {
		  return imageStore.getAll();
    }

    @GetMapping("preview/{id}")
    public void getImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        ImageMeta imageMeta  = imageStore.getById(id);
        response.setContentType(imageMeta.getMimeType());
        InputStream in = new FileInputStream("src/main/resources/public/images/"+imageMeta.getName());
        IOUtils.copy(in,response.getOutputStream());

    }

}
