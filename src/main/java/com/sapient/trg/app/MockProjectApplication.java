package com.sapient.trg.app;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sapient.trg.dao.RegionRepository;
import com.sapient.trg.dao.StoreRepository;
import com.sapient.trg.dao.UserProfileRepository;
import com.sapient.trg.entity.RegionMaster;
import com.sapient.trg.entity.StoreMaster;
import com.sapient.trg.entity.UserProfile;

import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@SpringBootApplication(scanBasePackages = "com.sapient")
@EntityScan(basePackages = "com.sapient.trg.entity")
@EnableJpaRepositories(basePackages = "com.sapient.trg.dao")
public class MockProjectApplication  implements CommandLineRunner{

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private RegionRepository regionMaster;
	
	@Autowired
	private UserProfileRepository profileRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MockProjectApplication.class, args);
		
		
	}
	@Bean
	public Docket openApiPetStore() {
		return new Docket(DocumentationType.OAS_30)
				.groupName("open-api-user-management")
				.select()
				.paths(rolePaths())
				.build();
	}

	private Predicate<String> rolePaths() {
		return regex(".*/usm/.*");
	}
	@Override
	public void run(String... args) throws Exception {
		RegionMaster master=regionMaster.getById(58l);
		List<UserProfile> user =profileRepository.getUsersByregion(master);
		System.out.println(user);
//		List<StoreMaster> storeMaster=storeRepository.getStoreMasterByRegion(master);
//		for (StoreMaster store : storeMaster) {
//			List<UserProfile> user =profileRepository.getUsersByregion(store);
//			System.out.println(storeMaster);
//		}
	}
	
}
