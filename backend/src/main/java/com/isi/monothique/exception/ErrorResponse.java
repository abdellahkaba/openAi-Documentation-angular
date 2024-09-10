package com.isi.monothique.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}

