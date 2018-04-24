package com.itfollowme.zhihu;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.itfollowme.zhihu.ui.model.DetailBean;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.itfollowme.zhihu", appContext.getPackageName());
    }

    @Test
    public void testReadJSON(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        try {
            InputStream inputStream = appContext.getAssets().open("news.json");
            int size = inputStream.available();
            byte[] buf = new byte[size];
            inputStream.read(buf);
            String text = new String(buf,"UTF-8");
            //            JSON.parseObject(text,);
            Log.i("text",text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadJSON2(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        try {
            InputStream inputStream = appContext.getAssets().open("9666020.json");
            int size = inputStream.available();
            byte[] buf = new byte[size];
            inputStream.read(buf);
            String text = new String(buf,"UTF-8");
            //            JSON.parseObject(text,);
            Log.i("text",text);

            DetailBean detailBean = JSON.parseObject(text, DetailBean.class);
            Log.i("zhihu",detailBean.getShareUrl());
            Log.i("zhihu","title"+detailBean.getTitle() +"\timageSource:" +detailBean.getImageSource());
            Log.i("zhihu",detailBean.getImage());
            Log.i("zhihu",""+detailBean.getImages());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
