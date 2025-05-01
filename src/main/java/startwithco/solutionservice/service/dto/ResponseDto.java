package startwithco.solutionservice.service.dto;

public class ResponseDto {
    public record PaymentResponseDto(
            Long amount,
            String orderId,
            String orderName
    ) {

    }
}
