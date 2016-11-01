package com.hyphenate.chatuidemo.utils;

import android.util.Log;


import com.google.gson.Gson;
import com.hyphenate.chatuidemo.I;
import com.hyphenate.chatuidemo.bean.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by clawpo on 2016/9/21.
 */
public class ResultUtils {
    public static <T> Result getResultFromJson(String jsonStr, Class<T> clazz){
        Result result = new Result();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            result.setRetCode(jsonObject.getInt("retCode"));
            result.setRetMsg(jsonObject.getBoolean("retMsg"));
            if(!jsonObject.isNull("retData")) {
                JSONObject jsonRetData = jsonObject.getJSONObject("retData");
                if (jsonRetData != null) {
                    Log.e("Utils", "jsonRetData=" + jsonRetData);
                    String date;
                    try {
                        date = URLDecoder.decode(jsonRetData.toString(), I.UTF_8);
                        Log.e("Utils", "jsonRetData=" + date);
                        T t = new Gson().fromJson(date, clazz);
                        result.setRetData(t);
                        return result;

                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                        T t = new Gson().fromJson(jsonRetData.toString(), clazz);
                        result.setRetData(t);
                        return result;
                    }
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public static <T> Result getListResultFromJson(String jsonStr, Class<T> clazz){
        Result result = new Result();
        Log.e("Utils","jsonStr="+jsonStr);
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            result.setRetCode(jsonObject.getInt("retCode"));
            result.setRetMsg(jsonObject.getBoolean("retMsg"));
            if(!jsonObject.isNull("retData")) {
                JSONArray array = jsonObject.getJSONArray("retData");
                if (array != null) {
                    List<T> list = new ArrayList<T>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonGroupAvatar = array.getJSONObject(i);
                        T ga = new Gson().fromJson(jsonGroupAvatar.toString(), clazz);
                        list.add(ga);
                    }
                    result.setRetData(list);
                    return result;
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


}
