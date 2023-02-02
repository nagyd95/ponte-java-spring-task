package hu.ponte.hr;

import hu.ponte.hr.controller.upload.UploadController;
import hu.ponte.hr.services.SignService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SeniorTestApplicationTests
{
	@Autowired
	UploadController uploadController;

	@Autowired
	SignService signService;
	@Test
	public void contextLoads() {
		assertThat(uploadController).isNotNull();
		assertThat(signService).isNotNull();
	}



}

