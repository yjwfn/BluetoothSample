package im.xinzeng.ble;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;

import java.lang.ref.WeakReference;
import java.util.UUID;

/**
 * Created by yjwfn on 16-4-13.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BluetoothScanner {


    private final String TAG = "BluetoothScanner";

    private Context mContext;

    private static BluetoothScanner INSTANCE;

    public     BluetoothScanner(Context context){
        this.mContext = context;
    }

    public static BluetoothScanner getInstance(Context context){
        synchronized (BluetoothScanner.class){
            if(INSTANCE == null)
                INSTANCE = new BluetoothScanner(context);
        }

        return INSTANCE;
    }

    public boolean startLeScan(UUID[] UUID, BluetoothAdapter.LeScanCallback callback){
        if(!BluetoothHelper.isValid(mContext)){
            BluetoothHelper.logd(TAG, "蓝牙功能不可用");
            return false;
        }


        BluetoothHelper.logd(TAG, "开始扫描");
        BluetoothAdapter adapter =  BluetoothAdapter.getDefaultAdapter();
        boolean result = adapter.startLeScan(UUID, callback);
        return result;
    }

    public void stopLeScan(BluetoothAdapter.LeScanCallback call){
        if( BluetoothHelper.isValid(mContext)) {
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            adapter.stopLeScan(call);
        }
    }

    public boolean isScanning(){
        if(!BluetoothHelper.isValid(mContext)){
            return false;
        }

        BluetoothAdapter adapter =  BluetoothAdapter.getDefaultAdapter();
        int scanMode = adapter.getState();
        return   scanMode != BluetoothAdapter.SCAN_MODE_NONE;
    }
}
