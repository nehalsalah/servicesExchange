package com.serviceexchange.www.serviceexchangeandroid.utils.filedownloaderUtil;

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.serviceexchange.www.serviceexchangeandroid.R;

import java.util.ArrayList;
import java.util.List;

public class FileDownloaderUtil implements FileDownloaderUtilInt {

    private Context aContext;
    private DownloadManager downloadManager;
    private NotificationManager notificationManager;
    private List<Long> refIdList = new ArrayList<>();

    public FileDownloaderUtil(Context context) {
        aContext = context;
    }

    @Override
    public void downloadFile(String url) {
        downloadManager = (DownloadManager) aContext.getSystemService(Context.DOWNLOAD_SERVICE);
        notificationManager = (NotificationManager) aContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("Downloading");
        request.setDescription("In Progress");
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "");
        Long refId = downloadManager.enqueue(request);
        refIdList.add(refId);
        BroadcastReceiver onComplete = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                // get the refid from the download manager
                long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                refIdList.remove(referenceId);
                if (refIdList.isEmpty()) {
                    Log.i("INSIDE", "" + referenceId);
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(aContext)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Download")
                                    .setContentText("Download is Complete");
                    notificationManager.notify(455, mBuilder.build());
                }
                aContext.unregisterReceiver(this);
            }
        };
        aContext.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
}
