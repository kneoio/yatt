package com.semantyca.yatt.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class TimeUtil {
    public static ZonedDateTime getRndDateBetween(LocalDateTime from, LocalDateTime to) {
        ZonedDateTime fromZoned = from.atZone(ZoneId.systemDefault());
        ZonedDateTime toZoned = to.atZone(ZoneId.systemDefault());
        long random = ThreadLocalRandom.current().nextLong(fromZoned.toInstant().toEpochMilli(), toZoned.toInstant().toEpochMilli());
        return ZonedDateTime.ofInstant(new Date(random).toInstant(), ZoneId.systemDefault());
    }
}
