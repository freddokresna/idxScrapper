/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idxscrapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author freddo
 */
public class NewClass {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat fmts = new SimpleDateFormat("yyyy-MMMM-dd");
        Date startDate = formatter.parse("2015-01-01");
        Date endDate = formatter.parse("2018-12-30");
        LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            // Do your job here with `date`.
            System.out.println(date);
            Date my = formatter.parse(date.toString());
            System.out.println(date.getMonth());
            System.out.println(date.getYear());
            System.out.println(date.getDayOfMonth());

        }

    }
}
