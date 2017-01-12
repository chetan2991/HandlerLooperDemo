package com.handerlooperdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity
{


    Thread myThread;
    Handler handler;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById( R.id.progressBar2 );
        myThread = new Thread( new MyThread() );
        myThread.start();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message)
            {

                progressBar.setProgress( message.arg1 );
                return true;
            }
        });
    }


    public class MyThread implements Runnable
    {
        @Override
        public void run()
        {
            for( int i = 0 ; i< 100 ; i++ )
            {
                Message message  = Message.obtain();
                message.arg1 = i;
                handler.sendMessage( message );
                try
                {
                    Thread.sleep( 1000 );
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
        }
    }
}
