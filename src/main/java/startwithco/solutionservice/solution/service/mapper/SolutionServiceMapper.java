package startwithco.solutionservice.solution.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import startwithco.solutionservice.solution.domain.SolutionEntity;

import static startwithco.solutionservice.solution.service.dto.ResponseDto.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SolutionServiceMapper {
    SolutionResponseDto toPaymentResponseDto(SolutionEntity solutionEntity);
}
