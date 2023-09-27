package com.aurora.sptest.common;


import com.alibaba.fastjson2.JSONObject;

/**
 * @author panda00hi
 * @date 2023.09.25
 */
public class CommonTest {


    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", 1.01f);
        Object o = jsonObject.get("1");
        System.out.println(o);

        float o1 = jsonObject.getFloatValue("1");
        System.out.println(o1);

    }
}
