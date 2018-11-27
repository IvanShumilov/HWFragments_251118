package ia.shumilov.ru.hwfragments;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Random;

import static ia.shumilov.ru.hwfragments.MainActivity.SLEEP_THREAD_MILS;

public class CustomService extends Service {
    public static final String MY_ACTION = "ia.shumilov.ru.fragments.action";
    public static final String MY_FILTER = "ia.shumilov.ru.fragments.filter";
    private Intent intent = new Intent();
    public CustomService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    sendBroadCast("message from broadcast " + i);
                    try {
                        Thread.sleep(SLEEP_THREAD_MILS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        stopSelf();
        return START_NOT_STICKY;
    }

    private void sendBroadCast(String mess) {
        intent.setAction(MY_ACTION);
        intent.putExtra(MY_FILTER, mess);
        intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
        sendBroadcast(intent);
    }
}
