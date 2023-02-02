package hu.ponte.hr.services;

import hu.ponte.hr.controller.ImageMeta;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageStore {
  private ArrayList<ImageMeta> imageMetaList= new ArrayList<>();

  @Autowired
  private SignService signService;

  public boolean addNewImage(ImageMeta imageMeta) {
    imageMetaList.add(imageMeta);
    return true;
  }

  public List<ImageMeta> getAll() {
    return imageMetaList;
  }
  public ImageMeta getById(String id){
    return imageMetaList.stream().filter(im -> im.getId().equals(id)).findFirst().orElse(null);
  }

  public String makeImageMetaFromFile(File targetFile,String type) throws IOException {
    String id = makeRandomId();
    String fileName= targetFile.getName();
    long bytes = Files.size(Paths.get(targetFile.getPath()));
    String digitalSign= makeDigitalSign(fileName);

    ImageMeta imageMeta = ImageMeta.builder()
        .id(id)
        .name(fileName)
        .mimeType(type)
        .size(bytes)
        .digitalSign(digitalSign)
        .build();

     addNewImage(imageMeta);
     return id;
  }

  private String makeDigitalSign(String fileName) {
    String digitalSign;
    try {
      digitalSign = signService.signSHA256RSA(fileName);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return digitalSign;
  }

  public boolean makeFileFromMultiFile(MultipartFile file) throws IOException {
    InputStream initialStream = file.getInputStream();
    byte[] buffer = new byte[initialStream.available()];
    initialStream.read(buffer);
    String type = file.getContentType();
    File targetFile = new File("src/main/resources/public/images/"+file.getOriginalFilename());
    try (OutputStream outStream = new FileOutputStream(targetFile)) {
      outStream.write(buffer);
    }
    makeImageMetaFromFile(targetFile,type);
    return true;
  }
  public String makeRandomId(){
    return Long.toString(ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE));
  }
}
