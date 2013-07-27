package com.example.splitactionbardemo;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    private Menu mOptionsMenu;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setButtonRefreshState(false);
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        mOptionsMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                setButtonRefreshState(true);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            //
                        } finally {
                            mHandler.sendEmptyMessage(0);
                        }
                    }
                }.start();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setButtonRefreshState(boolean state ) {
        MenuItem item = mOptionsMenu.findItem(R.id.refresh);
        if (state) {
            item.setActionView(R.layout.actionbar_indeterminate_progress);
        } else {
            item.setActionView(null);
        }
    }
}
