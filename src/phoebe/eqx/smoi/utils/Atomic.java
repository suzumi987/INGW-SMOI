package phoebe.eqx.smoi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by IntelliJ IDEA.
 * User: nopparar
 * Date: 11/9/2555
 * Time: 10:57 น.
 */
public class Atomic {
    private static AtomicLong atomicLong = new AtomicLong(0L);
    private static final SimpleDateFormat dtLongFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);


    public static synchronized String getNextAtomicIndex(String anyword) {
        long andIncrement = atomicLong.getAndIncrement();
        if (andIncrement < 0) {
            atomicLong.set(0);
            andIncrement = 0;
        }
        String dFormat = dtLongFormatter.format(new Date());

        return new StringBuilder(anyword).append("-")
                .append(dFormat).append("-")
                .append(andIncrement).toString();
    }

    public static String getOcfSessionId(String invokeId) {
        String[] split = invokeId.split(":");
        return split[0];
    }
}
