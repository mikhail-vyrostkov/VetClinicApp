package ru.vyrostkov.grpc.enumUnit;

import java.time.Instant;

public enum Timestamps {
    TIME_1(Instant.parse("08:00")),
    TIME_2(Instant.parse("08:30")),
    TIME_3(Instant.parse("09:00")),
    TIME_4(Instant.parse("09:30")),
    TIME_5(Instant.parse("10:00")),
    TIME_6(Instant.parse("10:30")),
    TIME_7(Instant.parse("11:00")),
    TIME_8(Instant.parse("11:30")),
    TIME_9(Instant.parse("12:00")),
    TIME_10(Instant.parse("12:30")),
    TIME_11(Instant.parse("13:00")),
    TIME_12(Instant.parse("13:30")),
    TIME_13(Instant.parse("14:00")),
    TIME_14(Instant.parse("14:30")),
    TIME_15(Instant.parse("15:00")),
    TIME_16(Instant.parse("15:30")),
    TIME_17(Instant.parse("16:00")),
    TIME_18(Instant.parse("16:30")),
    TIME_19(Instant.parse("17:00")),
    TIME_20(Instant.parse("17:30")),
    TIME_21(Instant.parse("18:00")),
    TIME_22(Instant.parse("18:30")),
    TIME_23(Instant.parse("19:00")),
    TIME_24(Instant.parse("19:30")),
    TIME_25(Instant.parse("20:00"));

    Timestamps(Instant time) {
    }
}
