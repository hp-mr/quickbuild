package com.mr.quickbuild.LocalData;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.mr.quickbuild.Contants;
import com.mr.quickbuild.utils.PreferencesUtils;



/**
 * Created by Kirali
 * Time  2017/8/11
 * Describe: 用户的个人信息需要保存在本地
 */

public class LocalDataUtil {

    public static void put(Context context,Object object) {
        String user_json = ((JSONObject) JSONObject.toJSON(object)).toJSONString();
        PreferencesUtils.putString(context, Contants.USER_JSON, user_json);
    }
    public static Object getUser(Context context) {
        String user_json = PreferencesUtils.getString(context, Contants.USER_JSON);
        if (!TextUtils.isEmpty(user_json)) {
            return JSONObject.parseObject(user_json, Object.class);
        }
        return null;
    }

    public static void putToken(Context context, String token) {
        PreferencesUtils.putString(context, Contants.TOKEN, token);
    }


    public static String getToken(Context context) {
        return PreferencesUtils.getString(context, Contants.TOKEN);
    }

    public static void clearUser(Context context) {
        PreferencesUtils.putString(context, Contants.USER_JSON, "");
    }

    public static void clearToken(Context context) {
        PreferencesUtils.putString(context, Contants.TOKEN, "");
    }

}
