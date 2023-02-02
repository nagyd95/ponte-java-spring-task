package hu.ponte.hr;

import hu.ponte.hr.services.SignService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class SignTest
{
	Map<String, String> files = new LinkedHashMap<String, String>() {
		{
			put("cat.jpg","aZXeFNeAj7JrkeaFtGHSlfTLJmuqi5y75DrbEVvrl1CvT/Zey+vPycgL2Jvf9UPH+4TpC+U6ak8Bye2w6Jl7YgUH5o0XQK6m+pOQ6rBg7QAJqlxZ3aMn6DVRIBMAWNxdSN4XgHRmJAlwFDMiojHzk/OutcZ5CXWR4saQh7yHDfLQOdOajsyK6ZME8tUNRBY4a1GyYq+Ripm8j5ieBdRUTrRo5PQR8sHFk4zoNM1sq1Ho6x+CERr3GMok8ImUWP2Ej4KgHy3ptp3v8uesODdi3F56njlOcP1Rmt066LcUmehahhkqERguFcbcd02QjX9CDZOjiENu2m2snt/acT2A+w==");
			put("enhanced-buzz.jpg","XXXWsvov1909ajiu2bHj8r7jPJsi9SRFB4tuaXDzyJFhiJqX/jAi2JK5eJwXRBk90jmWBzTpitOpxhC3yicUbBaWIyXF8w5QfMEqA9/mbHeTMfhlULAoyBA4XKzNpTUr6/VaxwRFP6VrJnFhihXMdA/ZMEeCMPPOWCyMBtYu8TXVlQNJ2mGZW0Q0CRUBv6EWpsQA07q5vX27AIAJt86DG/+JjMPNK+a+ebtnrUy8OF17bT36ndBDP75f5UWLqORrSP4HydcHoV5iBAy9u3938yUOoaGRXj1TAgUrwRFBzxmGHnh3OKMFcWBX9c689HZ3CJLinzQXskFH5PQin50IZb4Q==");
			put("rnd.jpg","mXmVHkTVrQCSyih1SwHIag9oGRfzLoCyf9T3AXUJElBIL0p7AVp8qSQjuN9CVE8HDRt++mD4B2J1QJDj3bcX46c6gr5lOs02dYjtY1E7trCmPV95TIEOeqTlYS6hD4Gc8dNn9ECb9eqddSXoTZMr9ouCRyiF1OFayxX0I1ep7nXO/6YskY85c3PNFiwzSHUsNdgGoDZxUs6o8og8xKsrH//+Tny+WFHhuUssJMJHUqKyEUJIcl3P1EBedWaMtf3jfJFFWnvff+/yr51+xiXvzAZNwo3teKN/12IIdXJ1vaZ1rSxEcB8Pugf48FSxhWwhAa6FW7XNFRZwpXBALmJxUw==");
		}
	};
	@Autowired
	SignService signService;

	@Test
	public void test_01() throws Exception {
		assertEquals( signService.signSHA256RSA("cat.jpg"),files.get("cat.jpg"));
		assertEquals( signService.signSHA256RSA("rnd.jpg"),files.get("rnd.jpg"));
	}
	@Test
	public void test_02() throws Exception {
		assertFalse(signService.signSHA256RSA("ca.jpg").equals(files.get("cat.jpg")));
	}


}
