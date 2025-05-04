package startwithco.solutionservice.service.dto;


import startwithco.solutionservice.domain.SolutionEntity;

public class ResponseDto {
    public record SolutionResponseDto(
            Long solutionSeq,
            Long companySeq,
            SolutionEntity.FIELD field,
            String solutionName,
            String detailImage,
            String description,
            Long amount,
            SolutionEntity.CLOUD cloud,
            Long duration,
            Long sellCount
    ) {

    }
}
