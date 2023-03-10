package hu.ponte.hr.controller.upload;

import hu.ponte.hr.services.ImageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Component
@RequestMapping("api/file")
public class UploadController
{
    @Autowired
    private ImageStore imageStore;

    @RequestMapping(value = "post", method = RequestMethod.POST)
    @ResponseBody
    public String handleFormUpload(@RequestParam("file") MultipartFile file) throws Exception {
        String returnString;
        if(file.isEmpty() || file.getSize() / 1024 > 2048){
            returnString =  "not ok";
        }else {
            returnString = imageStore.createImageFromFile(file) ? "ok" : "not ok";
        }
        return returnString;
    }
}
