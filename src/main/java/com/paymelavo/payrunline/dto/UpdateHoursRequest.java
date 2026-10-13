package com.paymelavo.payrunline.dto;

import java.math.BigDecimal;

public record UpdateHoursRequest(
        BigDecimal hours
)
{}
