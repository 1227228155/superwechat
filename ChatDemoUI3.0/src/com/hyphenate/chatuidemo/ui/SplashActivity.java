package com.hyphenate.chatuidemo.ui;

import android.content.Intent;
import android.os.Bundle;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chatuidemo.SuperWeChatHelper;
import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.db.UserDao;
import com.hyphenate.chatuidemo.utils.L;
import com.hyphenate.easeui.domain.User;

/**
 * 开屏页
 *
 */
public class SplashActivity extends BaseActivity {
	private static final String TAG ="SplashActivity";

	private static final int sleepTime = 2000;

	SplashActivity mContext;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.em_activity_splash);
		super.onCreate(arg0);
		mContext =this;


	}

	@Override
	protected void onStart() {
		super.onStart();

		new Thread(new Runnable() {
			public void run() {
				if (SuperWeChatHelper.getInstance().isLoggedIn()) {
					// auto login mode, make sure all group and conversation is loaed before enter the main screen
					long start = System.currentTimeMillis();
					EMClient.getInstance().groupManager().loadAllGroups();
					EMClient.getInstance().chatManager().loadAllConversations();
					long costTime = System.currentTimeMillis() - start;
					//wait
					if (sleepTime - costTime > 0) {
						try {
							Thread.sleep(sleepTime - costTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					//enter main screen
					startActivity(new Intent(SplashActivity.this, MainActivity.class));
					finish();
				}else {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
					}
					startActivity(new Intent(SplashActivity.this, GuideActivity.class));
					finish();
				}
			}
		}).start();

	}
	
	/**
	 * get sdk version
	 */
	private String getVersion() {
	    return EMClient.getInstance().getChatConfig().getVersion();
	}
}
