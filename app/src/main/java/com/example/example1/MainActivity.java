package com.example.example1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.soloader.SoLoader;
import com.facebook.sonar.android.AndroidSonarClient;
import com.facebook.sonar.android.utils.SonarUtils;
import com.facebook.sonar.core.SonarClient;
import com.facebook.sonar.plugins.network.NetworkSonarPlugin;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.test);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(myIntent);
                final SonarClient client = AndroidSonarClient.getInstance(MainActivity.this);
// Client may be null if AndroidSonarClient.createInstance() was never called
// which is the case in production builds.
                if (client != null) {
                    final MyPlugin plugin = client.getPlugin("MyPlugin");
                    plugin.sendData("2", "3", "4");
                }

            }
        });

        try {
            SoLoader.init(getApplication(), 0);
        } catch (IOException e) {
            Log.e(this.getLocalClassName(), e.toString());
        }
        if (BuildConfig.DEBUG && SonarUtils.shouldEnableSonar(getApplicationContext())) {
            final SonarClient client = AndroidSonarClient.getInstance(this);
            // Add new Plugins
            client.addPlugin(new MyPlugin());
            // Start
            client.start();
        }
    }
}
