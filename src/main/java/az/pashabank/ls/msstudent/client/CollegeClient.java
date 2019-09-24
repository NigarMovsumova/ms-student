package az.pashabank.ls.msstudent.client;

import az.pashabank.ls.msstudent.model.dto.CollegeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient(name = "college", url = "${client.ms-college.url}")
public interface CollegeClient {

    @GetMapping(value = "/colleges/in")
    List<CollegeDto> getCollegesByCity(@RequestBody String city);
}
