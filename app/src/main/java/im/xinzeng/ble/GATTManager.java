package im.xinzeng.ble;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCallback;
import android.content.Context;
import android.os.Build;

import java.util.Map;

import im.xinzeng.ble.device.AbsSmartDevice;

/**
 * Created by yjwfn on 16-4-13.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class GATTManager implements BluetoothAdapter.LeScanCallback{

    static final String    TAG = "GATTManager";

    private static GATTManager instance;



    private GATTCallback             mCallback;

    Context mAppContext;


    Map<String, AbsSmartDevice>     mSmartDevices;





    private GATTManager(Context context){
        this.mAppContext = context;
        this.mCallback = new GATTCallback();
    }

    public static GATTManager getInstance(Context context){
        synchronized (GATTManager.class){
            if(instance == null){
                instance = new GATTManager(context);
            }
        }
        return instance;
    }


    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        if(mSmartDevices == null){
            return;
        }

        BluetoothHelper.printDevice(TAG, device);
        String mac = device.getAddress();
        AbsSmartDevice sd = mSmartDevices.get(mac);
        if(sd.getHwName() == null){
            sd.setHwName(sd.getHwName());
            device.connectGatt(mAppContext, false, mCallback);
        }
    }



     class GATTCallback extends BluetoothGattCallback{




    }


}





