package br.com.youtubemanager.core;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtils {

    public static String formatNumber(BigInteger number) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        return numberFormat.format(number);
    }

}
