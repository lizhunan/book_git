package com.sdjy.book.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 李竹楠 on 2018/3/13.
 * 网络监听
 */

public class NetWorkStateReceiver extends BroadcastReceiver {

    public static boolean isNoAvailable = false;

    private NetWorkStateListener netWorkStateListener;

    public void setNetWorkStateListenerl(NetWorkStateListener netWorkStateListener) {
        this.netWorkStateListener = netWorkStateListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            //获取联网状态的NetworkInfo对象
            NetworkInfo info = intent
                    .getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info != null) {
                //如果当前的网络连接成功并且网络连接可用
                if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()) {
                    if (info.getType() == ConnectivityManager.TYPE_WIFI
                            || info.getType() == ConnectivityManager.TYPE_MOBILE) {
                        if (isNoAvailable) {
                            isNoAvailable = false;
                        //    netWorkStateListener.resumeConnect();
                        }
                    }
                } else {
                    isNoAvailable = true;
//                    netWorkStateListener.disConnect();
                }
            }
        }
    }

    public interface NetWorkStateListener {
        public void disConnect();

        public void resumeConnect();
    }


}
