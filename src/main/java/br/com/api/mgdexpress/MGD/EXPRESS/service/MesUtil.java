package br.com.api.mgdexpress.MGD.EXPRESS.service;

import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

@Component
public class MesUtil {
    public static String obterNomeMes(Month month) {
        return month.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
    }
}
