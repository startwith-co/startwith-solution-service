package startwithco.solutionservice.solution.service.dto;


import startwithco.solutionservice.solution.domain.SolutionEntity;

public class ResponseDto {
    public record SolutionResponseDto(
            Long solutionSeq,
            Long companySeq,
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
