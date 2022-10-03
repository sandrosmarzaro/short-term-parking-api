package one.digitalinnovation.shorttermparking.services;

import one.digitalinnovation.shorttermparking.models.ParkingSpotModel;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingSpotCheckOut {
    public static Double checkOut(ParkingSpotModel parkingSpotModelToUpdate) {
        final double ONE_HOUR_RATE = 5.0;
        final double HOURLY_RATE = 3.5;
        final double DAILY_RATE = 20.0;
        final OffsetDateTime ENTRY_DATE = parkingSpotModelToUpdate.getEntryDate();
        final OffsetDateTime EXIT_DATE = parkingSpotModelToUpdate.getExitDate();
        final long DAYS_SPENT = ChronoUnit.DAYS.between(ENTRY_DATE, EXIT_DATE);
        final long HOURS_SPENT = ChronoUnit.HOURS.between(ENTRY_DATE, EXIT_DATE);

        if (ChronoUnit.DAYS.between(ENTRY_DATE, EXIT_DATE) > 1) {
            return DAILY_RATE * DAYS_SPENT;
        }
        else if (ChronoUnit.HOURS.between(ENTRY_DATE, EXIT_DATE) > 1) {
            return (HOURLY_RATE * HOURS_SPENT) + ONE_HOUR_RATE;
        }
        else {
            return ONE_HOUR_RATE;
        }
    }
}
