package com.sdjy.book.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import java.util.Map;

public class ALiPushReceiver extends MessageReceiver {

    public static final String REC_TAG = "receiver";

    @Override
    protected void onNotification(Context context, String s, String s1, Map<String, String> map) {
        super.onNotification(context, s, s1, map);

    }

    @Override
    protected void onMessage(Context context, CPushMessage cPushMessage) {
        super.onMessage(context, cPushMessage);
    }

    @Override
    protected void onNotificationOpened(Context context, String s, String s1, String s2) {
        super.onNotificationOpened(context, s, s1, s2);
    }

    @Override
    protected void onNotificationClickedWithNoAction(Context context, String s, String s1, String s2) {
        super.onNotificationClickedWithNoAction(context, s, s1, s2);
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String s, String s1, Map<String, String> map, int i, String s2, String s3) {
        super.onNotificationReceivedInApp(context, s, s1, map, i, s2, s3);
    }

    @Override
    protected void onNotificationRemoved(Context context, String s) {
        super.onNotificationRemoved(context, s);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
