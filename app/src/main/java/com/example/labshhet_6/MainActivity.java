package com.example.labshhet_6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final Intent BROADCAST_ACTION = null;
    Button button;
    TextView textViewMsg;
    private Receiver localListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        textViewMsg=findViewById(R.id.textViewMsg);
    }
        protected void onStart() {
            super.onStart();
            localListener = new Receiver();
            IntentFilter intentFilter = new IntentFilter(String.valueOf(BROADCAST_ACTION));
            this.registerReceiver(localListener,intentFilter);
        }
        protected void onStop(){
            super.onStop();
            this.unregisterReceiver(localListener);
        }
        public void onClick(View view){
            if (view.getId() == R.id.button) {
                BackgroundService.startAction(this.getApplicationContext());
            }
        }

        public class Receiver extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                String currentText = textViewMsg.getText().toString();
                String message = intent.getStringExtra("value");
                currentText += "\nReceived" + message;
                textViewMsg.setText(currentText);
            }
    }
}

