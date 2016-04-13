package im.xinzeng.ble.device;

/**
 * Created by yjwfn on 16-4-13.
 */
public abstract  class AbsSmartDevice {

    private String hwName;

    private String  friendlyName;

    private String  mac;

    private int     type;

    private int     offset;

    private byte[]  values;


    public AbsSmartDevice(int type){
        this.type = type;
    }


    public String getHwName() {
        return hwName;
    }

    public void setHwName(String hwName) {
        this.hwName = hwName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getType() {
        return type;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


    void setValues(byte[] values){
        this.values = values;
    }

    public void    reset(){
        offset = 0;
    }

}
