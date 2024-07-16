package com.uk.management.service;

import com.uk.management.dto.AuthRequest;

public interface JWTService {

    public String createJwtToken(AuthRequest authRequest) throws Exception;
}
