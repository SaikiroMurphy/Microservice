package re.edu.pharmacy_service;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PharmacyServiceApplication {
	@Value("${spring.application.name}")
	public String applicationName;

	@Value("${app.branch-name}")
	public String branchName;

	@Value("${app.hotline}")
	public String hotline;

	@Value("${pharmacy.vat-rate}")
	public String vatRate;

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		SpringApplication.run(PharmacyServiceApplication.class, args);
	}

}
