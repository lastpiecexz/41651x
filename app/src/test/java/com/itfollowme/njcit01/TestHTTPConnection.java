package com.itfollowme.njcit01;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by notre on 2018/3/27.
 */

public class TestHTTPConnection {

    @Test
    public void testConn(){
        try {
            URL url = new URL("http://gank.io/api/data/Android/10/1");
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            InputStream in  = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            char[] buf = new char[1];
            while(br.read(buf)!=-1){
                System.out.print(buf[0]);
            }

            br.close();
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
