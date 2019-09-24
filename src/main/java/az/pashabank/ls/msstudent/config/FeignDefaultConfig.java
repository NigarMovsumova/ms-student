package az.pashabank.ls.msstudent.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "az.pashabank.ls.msstudent.client")
public class FeignDefaultConfig {
}
