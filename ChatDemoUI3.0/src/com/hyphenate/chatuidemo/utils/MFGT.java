package com.hyphenate.chatuidemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import com.hyphenate.chatuidemo.I;
import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.ui.AddFriendActivity;
import com.hyphenate.chatuidemo.ui.LoginActivity;
import com.hyphenate.chatuidemo.ui.RegisterActivity;
import com.hyphenate.chatuidemo.ui.Request2AddActivity;
import com.hyphenate.easeui.domain.User;


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
    public static void startActivity(Context context,Intent intent){
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
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
    public static void gotoFriend(Activity context, String username){
        Intent intent = new Intent();
        intent.setClass(context,AddFriendActivity.class);
        intent.putExtra(I.User.USER_NAME,username);
         startActivity(context,intent);
    }

    public static void gotoAddFirendMsg(Activity context,String username){
        Intent intent = new Intent();
        intent.setClass(context,Request2AddActivity.class);
        intent.putExtra(I.User.USER_NAME,username);
        startActivity(context, intent);
    }

}
