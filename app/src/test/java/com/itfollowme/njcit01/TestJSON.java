package com.itfollowme.njcit01;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by notre on 2018/3/14.
 */

public class TestJSON {

    @Test
    public void testWriteJSON(){
        User user = new User();
        user.setId(10010);
        user.setName("龚宇");
        user.setPassword("123123");
        user.setBirth(new Date());
        List<String> address = new ArrayList<>();
        address.add("文澜路99号");
        address.add("徐州");
        address.add("阿里巴巴总部食堂");
        user.setAddress(address);

        Map<String,Integer> scores = new HashMap<>();
        scores.put("Math",3);
        scores.put("Android",30);
        scores.put("OC",99);

        user.setScores(scores);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void testReadJSON() throws Exception {
        File file = new File("G:\\students\\41651x\\student.json");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1];
        while(isr.read(buf)!=-1){
            sb.append(buf);
        }
        isr.close();
        System.out.println(sb.toString());

        User user = JSON.parseObject(sb.toString(),User.class);
        System.out.println(user.getName() + " "+user.getScores());
    }
}
