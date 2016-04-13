package im.xinzeng.ble;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.ParcelUuid;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.List;

/**
 * Created by yjwfn on 16-4-13.
 */
public final class BluetoothHelper {

    public static final int     REQUEST_PERMISSION = 0x1;
    public static final int     REQUEST_ENABLE_BLUETOOTH = 0x2;
    /**
     * 检查蓝牙权限
     * @param context
     * @return
     */
    public static  boolean checkBluetoothPermission(Context context){
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH) & ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_ADMIN)) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 检查位置权限当获取扫描结果需要位置权限
     * @param context
     * @return
     */
    public static boolean checkLocationPermission(Context context){
        return ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean   checkPermissions(Context context){
        return checkBluetoothPermission(context) && checkLocationPermission(context);
    }


    public static void requestEnableBluetooth(Activity context){
         if(checkPermissions(context) ){
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             context.startActivityForResult(intent, REQUEST_ENABLE_BLUETOOTH);
        }
    }

    public static void requestPermissions(Activity activity){
        ActivityCompat.requestPermissions(activity, new String[]{
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
        },
                REQUEST_PERMISSION
        );
    }



    public static boolean isValid(Context context){
        if(checkBluetoothPermission(context)){
            BluetoothAdapter bd = BluetoothAdapter.getDefaultAdapter();
            return bd != null &&  bd.isEnabled();
        }
        return false;
    }

    public static void  printDevice(String tag, BluetoothDevice device){
        StringBuffer    sb = new StringBuffer();
        sb.append("name: " + device.getName());
        sb.append("\n");
        sb.append("uuid: " + device.getAddress());
        sb.append("\n");

        // sb.append("type: " + device.getType());
        ParcelUuid[] uuid = device.getUuids();
        if(uuid != null){
            for(ParcelUuid u: uuid){
                sb.append(u.toString());
                sb.append("\n");
            }
        }

        logd(tag, sb.toString());
    }

    public static void printBluetoothGatt(String tag, BluetoothGatt gatt){
        List<BluetoothGattService> serviceList = gatt.getServices();
        StringBuffer sb = new StringBuffer();
        sb.append("name: " + gatt.getDevice().getName());
        sb.append("\n");
        for(BluetoothGattService service : serviceList){
            sb.append("Service: " + service.getUuid() + "\n");
            for(BluetoothGattCharacteristic bgc  : service.getCharacteristics()){
                sb.append("Characteristic: " + bgc.getUuid()  + "\n");
            }
        }

        logd(tag, sb.toString());
    }

    public static void  logd(String tag, String msg){
        Log.d(tag, msg);
    }
}
