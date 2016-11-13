package com.hyphenate.chatuidemo.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chatuidemo.I;
import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.SuperWeChatHelper;
import com.hyphenate.chatuidemo.bean.Result;
import com.hyphenate.chatuidemo.utils.MFGT;
import com.hyphenate.chatuidemo.utils.NetDao;
import com.hyphenate.chatuidemo.utils.OkHttpUtils;
import com.hyphenate.chatuidemo.utils.ResultUtils;
import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFriendActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.friend_avatar)
    ImageView friendAvatar;
    @BindView(R.id.friend_nickname)
    TextView friendNickname;

    String username = null;
    User user = null;
    @BindView(R.id.friend_username)
    TextView friendUsername;
    @BindView(R.id.friend_add)
    Button friendAdd;
    @BindView(R.id.friend_xiaoxi)
    Button friendXiaoxi;
    @BindView(R.id.friend_shipin)
    Button friendShipin;
    boolean isFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        ButterKnife.bind(this);
        username =  getIntent().getStringExtra(I.User.USER_NAME);
        if (username==null){
            MFGT.finish(this);
            return;
        }
        initView();
        user = SuperWeChatHelper.getInstance().getAppContactList().get(username);
        if(user==null){
            isFriend  = false;
        }else {
            setUserInfo();
            isFriend = true;
        }
        isFriend(isFriend);
        syncUserInfo();
    }
    private void syncFail(){
        if (!isFriend){
            MFGT.finish(this);
            return;
        }
    }

    private void syncUserInfo() {
        NetDao.syncUserInfo(this, username, new OkHttpUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                if (s!=null){
                    Result result = ResultUtils.getResultFromJson(s, User.class);
                    if (result!=null&&result.isRetMsg()){
                        User u = (User) result.getRetData();
                        if (u!=null){
                            if (isFriend){
                                SuperWeChatHelper.getInstance().saveAppContact(u);
                            }
                            user = u;
                            setUserInfo();
                        }else {
                            syncFail();
                        }

                    }else {
                        syncFail();
                    }
                }else {
                    syncFail();
                }
            }

            @Override
            public void onError(String error) {
                syncFail();
            }
        });
    }

    private void initView() {

        titleBack.setVisibility(View.VISIBLE);
        titleName.setVisibility(View.VISIBLE);
        titleName.setText("个人资料");
    }

    private void isFriend(boolean a) {
        if (a) {
            friendXiaoxi.setVisibility(View.VISIBLE);
            friendShipin.setVisibility(View.VISIBLE);
        } else {
            friendAdd.setVisibility(View.VISIBLE);
        }
    }

    private void setUserInfo() {
        EaseUserUtils.setAppUserAvatar(this, user.getMUserName(), friendAvatar);
        EaseUserUtils.setAppUserNick(user.getMUserNick(), friendNickname);
        EaseUserUtils.setAppUserNameWithNo(user.getMUserName(), friendUsername);
    }

    @OnClick({R.id.title_back, R.id.friend_add,R.id.friend_xiaoxi,R.id.friend_shipin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                MFGT.finish(this);
                break;
            case R.id.friend_add:
                MFGT.gotoAddFirendMsg(this, user.getMUserName());
                break;
            case R.id.friend_xiaoxi:
                startActivity(new Intent(this, ChatActivity.class).putExtra("userId", user.getMUserName()));
                break;
            case R.id.friend_shipin:
                if (!EMClient.getInstance().isConnected()){
                    Toast.makeText(this, R.string.not_connect_to_server, Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(this,VideoCallActivity.class).putExtra("username",user.getMUserName())
                            .putExtra("isComingCall",false));
                }
                break;
        }
    }


}
