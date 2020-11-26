package com.team2.chatbot_uxis

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("FCM Log", "Refreshed token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {                      //포어그라운드
            sendNotification(
                remoteMessage.notification!!.body,
                remoteMessage.notification!!.title
            )
        } else if (remoteMessage.data.size > 0) {                           //백그라운드
            sendNotification(
                remoteMessage.data["body"],
                remoteMessage.data["title"]
            )
            /* 백그라운드 작동 내용 */
        }
    }

    private fun sendNotification(
        messageBody: String?,
        messageTitle: String?
    ) {
        Log.d("FCM Log", "알림 메시지: $messageBody")

        /* 알림의 탭 작업 설정 */
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val channelId = "Channel ID"
        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        /* 알림 만들기 */
        val notificationBuilder =
            NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setFullScreenIntent(pendingIntent, true)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        /* 새로운 인텐트로 앱 열기 */
        val newintent = Intent(this, MainActivity::class.java)
        newintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(newintent)

        /* 채널 만들기*/
        /* Android 8.0 이상에서 알림을 게시하려면 알림을 만들어야 함 */if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Channel Name"
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }
}