package com.ggf.qcpp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.Log;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.ggf.qcpp.App;
import com.ggf.qcpp.Prefs;
import com.ggf.qcpp.e_formpengamatan.e_1_formpengamatan_list.e_1_formpengamatanlist;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.mapbox.mapboxsdk.geometry.LatLng;

/**
 * Created by fiyyanp on 2/1/2018.
 */

public class Utils {
    public static int convertStirngToInt(String value) {
        int val = Integer.parseInt(value);
        return val ;
    }

    public static float parseFloatDefault(String text, float defaultValue) {
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static int parseIntDefault(String text, int defaultValue) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static String parStringDefault(String text, String defaultValue) {
        try {
            return text;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    //CHOPPER---------------------------------------------------------------------------------------
    public static float totalnilaiChopper(String tanamanHancur , String Bonggol , String AplikasiRapat) {
        float valTanam = Float.parseFloat(tanamanHancur )* 4;
        float valBonggol =Float.parseFloat(Bonggol )* 4;
        float valAplikasi =Float.parseFloat(AplikasiRapat )* 2;
        float hasil = valTanam +valBonggol + valAplikasi ;
        return hasil / 10;

    }

    public static void goToListPengamatan(Context context){
        Intent intent = new Intent(context, e_1_formpengamatanlist.class);
        context.startActivity(intent);
    }

    //dropbibit-------------------------------------------------------------------------------------
    public static int totalbibtpertumpuk(String bibitnormal , String bibitafkir) {
        int valnormal = Integer.parseInt(bibitnormal );
        int valafkir = Integer.parseInt(bibitafkir );
        int hasil = valafkir + valnormal ;
        return hasil ;

    }

    //petikbibit------------------------------------------------------------------------------------
    public static int totalrealbibit (String bibitnormal , String bibitafkir) {
        int valnormal = Integer.parseInt(bibitnormal );
        int valafkir = Integer.parseInt(bibitafkir );
        int hasil = valafkir + valnormal ;
        return hasil ;

    }

    //pooldipping-----------------------------------------------------------------------------------
    public static int totalsamplebibit (String bibitnormal , String bibitafkir) {
        int valnormal = Integer.parseInt(bibitnormal );
        int valafkir = Integer.parseInt(bibitafkir );
        int hasil = valafkir + valnormal ;
        return hasil ;

    }

    //tanam-----------------------------------------------------------------------------------------
    public static long rataratajtdb (String totaltanaman , String panjangsample) {
        long valtotal =  Long.parseLong(totaltanaman) - 1 ;
        long valpanjang = Long.parseLong(panjangsample);
        long hasil = valpanjang / valtotal;
        return hasil ;

    }

    public static long rataratajtab (String panjangsample) {
        long valtotal =  5 ;
        long valpanjang = Long.parseLong(panjangsample);
        long hasil = valpanjang / valtotal;
        return hasil ;

    }

    //transport-------------------------------------------------------------------------------------
    public static long totaltertinggal (String totaltumpuk , String ratapertumpuk) {
        long valtotal =  Long.parseLong(totaltumpuk) ;
        long valrata = Long.parseLong(ratapertumpuk);
        long hasil = valrata * valtotal;
        return hasil ;

    }

    public static long totalterlindas (String gulud, String dijalan, String terlindas) {
        long valtotguludl =  Long.parseLong(gulud);
        long valtotjalan = Long.parseLong(dijalan);
        long valtotterlindas = Long.parseLong(terlindas);
        long hasil = valtotguludl + valtotjalan + valtotterlindas;
        return hasil ;

    }

    //----------------------------------------------------------------------------------------------

    public static int getHeightOfMultiLineText(String text, int textSize, int maxWidth) {
        TextPaint paint = new TextPaint();
        paint.setTextSize(textSize);
        int index = 0;
        int lineCount = 0;
        while (index < text.length()) {
            index += paint.breakText(text, index, text.length(), true, maxWidth, null);
            lineCount++;
        }

        Rect bounds = new Rect();
        paint.getTextBounds("Yy", 0, 2, bounds);
        // obtain space between lines
        double lineSpacing = Math.max(0, ((lineCount - 1) * bounds.height() * 0.25));

        return (int) Math.floor(lineSpacing + lineCount * bounds.height());
    }

    public static String generateSpk( String kategori) {
        long uniqueTimestamp = System.currentTimeMillis();
        String header = "spk" + kategori +uniqueTimestamp ;
        return header ;
    }
    public static String generateTglSekarang() {
        LocalDateTime now = LocalDateTime.now();

        // Format dalam format lokal (Indonesia)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", new Locale("id", "ID"));
        String formattedDate = now.format(formatter);
        return formattedDate;
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_INDONESIA_PHONE_NUMBER_REGEX =
            Pattern.compile("\\(?(?:\\+62|62|0)(?:\\d{2,3})?\\)?[ .-]?\\d{2,4}[ .-]?\\d{2,4}[ .-]?\\d{2,4}");

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static void storePengamatan(String data){
        App.getPref().put(Prefs.PENGAMATAN, data);
    }

    private static final String PREFS_NAME = "local_storage";
    private static final String KEY_SAVED_DATA = "saved_data";

    // Function to save model to local storage when there's a network error
    public static void saveDataToLocalStorage(Context context, String data) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_SAVED_DATA, data);
            editor.apply();
            Log.d("Utils", "Data saved to local storage: " + data);
        } catch (Exception e) {
            Log.e("Utils", "Error saving data to local storage: ", e);
        }
    }

    // Function to retrieve saved data from local storage
    public static String getSavedDataFromLocalStorage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SAVED_DATA, null);
    }

    // Example of making a network request and handling errors
    public static void fetchDataFromNetwork(Context context) {
        try {
            // Simulating a network request (you would replace this with actual networking code)
            // throw new IOException("Network error"); // Uncomment to simulate an error

            // If the network request is successful, handle it here
            String dataFromServer = "Sample data from server";
            Log.d("Utils", "Data fetched from network: " + dataFromServer);

        } catch (Exception e) {
            // If a network error occurs, save data to local storage
            Log.e("Utils", "Network error occurred: ", e);
            String dataToSaveLocally = "Sample data to save locally in case of network error";
            saveDataToLocalStorage(context, dataToSaveLocally);
        }
    }

    public static String convertMongoDate(String val) {
        ISO8601DateFormat df = new ISO8601DateFormat();
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM, yyyy HH:mm");
        try {
            Date d = df.parse(val);
            String finalStr = outputFormat.format(d);
            val = finalStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return val;
    }

    public static String now(){
        Date date = new Date();
        DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat f1 = new SimpleDateFormat("yyyy/MM/dd");
        String d = f.format(date);
        String d1 = f1.format(date);
        return d;
    }

    public static String convertMongoDateWithoutTIme(String val) {
        ISO8601DateFormat df = new ISO8601DateFormat();
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM, yyyy");
        try {
            Date d = df.parse(val);
            String finalStr = outputFormat.format(d);
            val = finalStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return val;
    }

    public static String convertRupiah(String val) {


        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        String biaya =kursIndonesia.format(Long.valueOf(val));
        return biaya;
//        int value = Integer.parseInt(val);
//        Locale localeID = new Locale("in", "ID");
//        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
//        String biaya =  formatRupiah.format(". "+(double)value);
//        return biaya ;
    }

    public static String convertRupiahBigDecimal(String val) {

//        double money = 100.1;
        BigDecimal amount = new BigDecimal(val);
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        formatter.setDecimalFormatSymbols(formatRp);
        String moneyString = formatter.format(amount);
        System.out.println(moneyString);
        return moneyString;
    }
}
