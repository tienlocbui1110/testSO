package com.example.example1;

import com.facebook.sonar.core.SonarConnection;
import com.facebook.sonar.core.SonarObject;
import com.facebook.sonar.core.SonarPlugin;

public class MyPlugin implements SonarPlugin {
    private SonarConnection mConnection;

    @Override
    public String getId() {
        return "MyPlugin";
    }

    @Override
    public void onConnect(SonarConnection connection) throws Exception {
        mConnection = connection;
    }

    @Override
    public void onDisconnect() throws Exception {
        mConnection = null;
    }

    public void sendData(String s1, String s2, String s3) {
        mConnection.send(s1,
                new SonarObject.Builder()
                        .put(s2, s3)
                        .build());
    }
}
