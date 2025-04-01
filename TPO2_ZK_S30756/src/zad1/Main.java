/**
 *
 *  @author Żydowo Kamil S30756
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
  public static void main(String[] args) throws IOException {
    Service s = new Service("Poland");
   String weatherJson = s.getWeather("Warsaw");
   Double rate1 = s.getRateFor("USD");
//    Double rate2 = s.getNBPRate();
    // ...
    // część uruchamiająca GUI


  }
}
