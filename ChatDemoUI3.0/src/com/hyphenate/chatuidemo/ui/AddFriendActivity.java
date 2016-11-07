package com.hyphenate.chatuidemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chatuidemo.I;
import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.SuperWeChatHelper;
import com.hyphenate.chatuidemo.utils.MFGT;
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

    User user = null;
    @BindView(R.id.friend_username)
    TextView friendUsername;
    @BindView(R.id.friend_add)
    Button friendAdd;
    @BindView(R.id.friend_xiaoxi)
    Button friendXiaoxi;
    @BindView(R.id.friend_shipin)
    Button friendShipin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        user = (User) getIntent().getSerializableExtra(I.User.USER_NAME);
        titleBack.setVisibility(View.VISIBLE);
        titleName.setVisibility(View.VISIBLE);
        titleName.setText("个人资料");
        setUserInfo();
        isFriend();
    }

    private void isFriend() {
        if (SuperWeChatHelper.getInstance().getAppContactList().containsKey(user.getMUserName())) {
            friendXiaoxi.setVisibility(View.VISIBLE);
            friendShipin.setVisibility(View.VISIBLE);
        } else {
            friendAdd.setVisibility(View.VISIBLE);
        }
    }

    private void setUserInfo() {
        EaseUserUtils.setAppUserAvatar(this, user.getMUserName(), friendAvatar);
        EaseUserUtils.setAppUserNick(user.getMUserNick(), friendNickname);
        EaseUserUtils.setUserNick(user.getMUserName(), friendUsername);
    }

    @OnClick({R.id.title_back, R.id.friend_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                MFGT.finish(this);
                break;
            case R.id.friend_add:
                MFGT.gotoAddFirendMsg(this,user.getMUserName());
                break;
        }
    }
}
