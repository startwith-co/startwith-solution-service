package startwithco.solutionservice.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static startwithco.solutionservice.service.dto.ResponseDto.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SolutionServiceMapper {
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "orderName", source = "orderName")
    PaymentResponseDto toPaymentResponseDto(Long amount, String orderId, String orderName);
}
