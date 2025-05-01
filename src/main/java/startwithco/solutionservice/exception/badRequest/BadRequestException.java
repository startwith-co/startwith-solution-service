package startwithco.solutionservice.exception.badRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BadRequestException extends RuntimeException {
	private final BadRequestErrorResult errorResult;
}
