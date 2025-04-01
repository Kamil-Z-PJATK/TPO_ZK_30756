/**
 *
 *  @author Żydowo Kamil S30756
 *
 */

package zad1;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;

public class Service {

    private Locale locale;

    public Service(String  country) {

        this.locale = getLocale(country);
       System.out.println(locale);
    }

    public Locale getLocale(String country) {
        for (String locale : Locale.getISOLanguages()) {
            Locale newLocale = new Locale("",locale);
            if (newLocale.getDisplayCountry(Locale.ENGLISH).equals(country)) {
                this.locale = newLocale;
            }


        }
        return locale;
    }

    public String getWeather(String city) {
        String weather = "";
        String key="";
        String loc=city+","+locale.getCountry();
        System.out.println(loc);

        try {
            String urls="http://api.openweathermap.org/data/2.5/weather?q="+loc+"&units=metric&APPID="+key;
           URL url = new URL(urls);
            URLConnection req= url.openConnection();
            req.connect();


           JSONParser parser = new JSONParser();
           JSONObject object = (JSONObject) parser.parse(new InputStreamReader(req.getInputStream()));
           String main = object.toJSONString();
           weather=main;


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return weather;

    }


    public double getRateFor(String currency) {

        Double rate = 0.0;

        Currency curr = Currency.getInstance(locale);
        String key="";
        String locCur=curr.getCurrencyCode();

        String urls="https://v6.exchangerate-api.com/v6/"+key+"/latest/"+locCur;
        try {
            URL url= new URL(urls);
            URLConnection req=url.openConnection();
            req.connect();

            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(new InputStreamReader(req.getInputStream()));
            Map<String,Double> map = (Map<String, Double>) object.get("conversion_rates");
      //      System.out.println(map.get(currency));
            rate = map.get(currency);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return rate;

    }




}  
