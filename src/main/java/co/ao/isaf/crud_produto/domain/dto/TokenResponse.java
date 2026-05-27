package co.ao.isaf.crud_produto.domain.dto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public record TokenResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        long expiresIn,
        Instant expiresAt,
        Instant issuedAt
) {
    public static TokenResponse of(
            String accessToken,
            String refreshToken,
            String tokenType,
            Instant expiresAt,
            Instant issuedAt
    ) {
        return new TokenResponse(
                accessToken,
                refreshToken,
                tokenType,
                ChronoUnit.SECONDS.between(issuedAt, expiresAt),
                expiresAt,
                issuedAt
        );
    }
}
