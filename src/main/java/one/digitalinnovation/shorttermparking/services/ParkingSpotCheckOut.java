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
        final int ENTRY_MINUTE = ENTRY_DATE.getMinute();
        final int EXIT_MINUTE = EXIT_DATE.getMinute();
        final double MINUTE_SPENT = EXIT_MINUTE - ENTRY_MINUTE;
        if (MINUTE_SPENT <= ChronoUnit.HOURS.getDuration().toMinutes()) {
            return ONE_HOUR_RATE;
        }
        if (MINUTE_SPENT <= ChronoUnit.DAYS.getDuration().toMinutes()) {
            return ONE_HOUR_RATE + HOURLY_RATE * (MINUTE_SPENT / ChronoUnit.HOURS.getDuration().toMinutes());
        }
        return DAILY_RATE * (MINUTE_SPENT / ChronoUnit.DAYS.getDuration().toMinutes());
    }
}
