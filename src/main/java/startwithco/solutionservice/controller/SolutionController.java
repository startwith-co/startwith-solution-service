package startwithco.solutionservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import startwithco.solutionservice.base.BaseResponse;
import startwithco.solutionservice.exception.badRequest.BadRequestErrorResult;
import startwithco.solutionservice.exception.badRequest.BadRequestException;
import startwithco.solutionservice.service.SolutionService;

import static startwithco.solutionservice.service.dto.ResponseDto.*;

@RestController
@RequestMapping("/api/solution-service")
@RequiredArgsConstructor
public class SolutionController {
    private final SolutionService solutionService;

    @GetMapping("/toss-payment-query")
    public ResponseEntity<BaseResponse<PaymentResponseDto>> tossPaymentQuery(
            @RequestParam(name = "solutionSeq") Long solutionSeq,
            @RequestParam(name = "buyerSeq") Long buyerSeq,
            @RequestParam(name = "sellerSeq") Long sellerSeq
    ) {
        if (solutionSeq == null || buyerSeq == null || sellerSeq == null || buyerSeq.equals(sellerSeq)) {
            throw new BadRequestException(BadRequestErrorResult.BAD_REQUEST_EXCEPTION);
        }

        PaymentResponseDto response = solutionService.getTossPaymentParam(solutionSeq, buyerSeq, sellerSeq);

        return ResponseEntity.ok().body(BaseResponse.ofSuccess(HttpStatus.OK.value(), response));
    }
}
