package im.xinzeng.ble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void startScan(View view) {

        if(!BluetoothHelper.isValid(this)){
            if(BluetoothHelper.checkPermissions(this))
                BluetoothHelper.requestEnableBluetooth(this);
            else
                BluetoothHelper.requestPermissions(this);
            return;
        }


        BluetoothScanner  bs =  BluetoothScanner.getInstance(getApplicationContext());
        bs.startLeScan(null, GATTManager.getInstance(getApplicationContext()));
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void stopScan(View view) {
        BluetoothScanner  bs =  BluetoothScanner.getInstance(getApplicationContext());
        bs.stopLeScan( GATTManager.getInstance(getApplicationContext()));
    }
}
