package com.railconnect.service;

import java.math.BigDecimal;

public interface FareService {

    BigDecimal calculateFare(int trainId,
                            String source,
                            String destination,
                            int numberOfPassengers);

    BigDecimal calculatePassengerFare(int trainId,
                                      String source,
                                      String destination);

    BigDecimal getBaseFare(int trainId);

    BigDecimal getTotalFare(BigDecimal fare,
                            int numberOfPassengers);
}