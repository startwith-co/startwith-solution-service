package startwithco.solutionservice.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import startwithco.solutionservice.domain.SolutionEntity;

import static startwithco.solutionservice.service.dto.ResponseDto.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SolutionServiceMapper {
    SolutionResponseDto toPaymentResponseDto(SolutionEntity solutionEntity);
}
