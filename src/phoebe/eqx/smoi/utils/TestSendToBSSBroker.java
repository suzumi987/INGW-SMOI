/**
 * @version 1.0
 * @author pawarich
 */
package phoebe.eqx.smoi.utils;

import ec02.utils.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestSendToBSSBroker {

    public static String Send(String message, String urlString) {
        String response = "";
        try {
            System.setProperty("sun.net.client.defaultReadTimeout", "50000");
            System.setProperty("sun.net.client.defaultConnectTimeout", "50000");

            StringBuilder Request = new StringBuilder(message);
            String myurl = "http://10.104.130.38:8090" + urlString;
            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "text/xml; charset=utf-8");
            connection.setRequestProperty("SOAPAction", myurl);

            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(Request);
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder strBuf = new StringBuilder();
            strBuf.append(in.readLine());
            strBuf.append(in.readLine());


            in.close();
            response = strBuf.toString();

            connection.getInputStream().close();
            connection.disconnect();

        } catch (IOException e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }

        return response;
    }
}
