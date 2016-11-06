package com.hyphenate.chatuidemo.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.redpacketui.utils.RedPacketUtil;
import com.hyphenate.chatuidemo.Constant;
import com.hyphenate.chatuidemo.R;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {


    @BindView(R.id.me_avatar)
    ImageView meAvatar;
    @BindView(R.id.me_nickname)
    TextView meNickname;
    @BindView(R.id.me_number)
    TextView meNumber;

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        setUserInfo();
    }

    private void setUserInfo() {
        EaseUserUtils.setCurrentAppUserAvatar(getActivity(),meAvatar);
        EaseUserUtils.setCurrentAppUserNick(meNickname);
        EaseUserUtils.setCurrentAppUserNameWithNo(meNumber);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(((MainActivity)getActivity()).isConflict){
            outState.putBoolean("isConflict", true);
        }else if(((MainActivity)getActivity()).getCurrentAccountRemoved()){
            outState.putBoolean(Constant.ACCOUNT_REMOVED, true);
        }
    }

    @OnClick({R.id.me_layout_photo, R.id.me_layout_collect, R.id.me_layout_money, R.id.me_layout_smail, R.id.me_layout_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_layout_photo:
                break;
            case R.id.me_layout_collect:
                break;
            case R.id.me_layout_money:
                //red packet code : 进入零钱页面
                RedPacketUtil.startChangeActivity(getActivity());
                break;
            //end of red packet code
            case R.id.me_layout_smail:
                break;
            case R.id.me_layout_setting:

                break;
        }
    }
}
