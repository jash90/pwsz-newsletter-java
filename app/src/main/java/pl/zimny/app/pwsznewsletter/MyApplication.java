package pl.zimny.app.pwsznewsletter;

import android.app.Application;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

/**
 * Created by ZimnY on 30.12.2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XLog.init(LogLevel.ALL);
    }
}
