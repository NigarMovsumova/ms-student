package az.pashabank.ls.msstudent.client;

import az.pashabank.ls.msstudent.exception.ClientException;
import az.pashabank.ls.msstudent.model.dto.CollegeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollegeClientFallback implements CollegeClient {

    private static final Logger logger = LoggerFactory.getLogger(CollegeClientFallback.class);

    @Override
    public List<CollegeDto> getCollegesByCity(String city) {
        logger.error("ActionLog.getCollegesByCity.error college client fallback");
        throw new ClientException();
    }

    @Override
    public CollegeDto getCollegeById(Long id) {
        logger.error("ActionLog.getCollegeById.error college client fallback");
        throw new ClientException();
    }
}
