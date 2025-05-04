package startwithco.solutionservice.service.dto;

import startwithco.solutionservice.domain.type.CLOUD;
import startwithco.solutionservice.domain.type.FIELD;

public class ResponseDto {
    public record SolutionResponseDto(
            Long solutionSeq,
            Long companySeq,
            FIELD field,
            String solutionName,
            String detailImage,
            String description,
            Long amount,
            CLOUD cloud,
            Long duration,
            Long sellCount
    ) {

    }
}
