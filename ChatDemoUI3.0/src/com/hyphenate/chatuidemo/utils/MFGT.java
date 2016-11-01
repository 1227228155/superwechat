package com.hyphenate.chatuidemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.baidu.platform.comapi.map.I;
import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.ui.LoginActivity;
import com.hyphenate.chatuidemo.ui.RegisterActivity;

import java.util.ArrayList;




public class MFGT {
    public static void finish(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }

    public static void startActivity(Context context,Class<?> cls){
        Intent intent = new Intent();
        intent.setClass(context,cls);
       startActivity(context,intent.getClass());
    }

    public static void gotoLogin(Activity context){
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        startActivity(context,intent.getClass());
    }
    public  static  void gotoRegister(Activity context){
        Intent intent = new Intent();
        intent.setClass(context, RegisterActivity.class);
        startActivity(context,intent.getClass());

    }


}
