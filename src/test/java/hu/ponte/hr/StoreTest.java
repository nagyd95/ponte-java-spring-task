package hu.ponte.hr;

import hu.ponte.hr.controller.ImageMeta;
import hu.ponte.hr.controller.ImagesController;
import hu.ponte.hr.services.ImageStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class StoreTest {

    @Autowired
    ImageStore imageStore;
    @Autowired
    private ImagesController imagesController;
    ImageMeta imageMeta=ImageMeta.builder()
            .name("test")
            .mimeType("testType")
            .size(500)
            .id("1")
            .build();
    @Test
    public void test_01() {
        assertTrue(imageStore.addNewImage(imageMeta));
    }
    @Test
    public void test_02() {
        assertEquals( imageStore.getAll().size(),1);
    }
    @Test
    public void test_03() {
        assertEquals( imageStore.getById("1").getName(),"test");
    }
    @Test
    public void test_04() {
        assertNotEquals( imageStore.makeRandomId(),"0");
    }
    @Test
    public void test_05() {
        assertTrue(imagesController.listImages().size()==1);
    }

}
