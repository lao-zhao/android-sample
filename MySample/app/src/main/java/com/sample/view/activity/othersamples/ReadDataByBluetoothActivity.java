package com.sample.view.activity.othersamples;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.sample.R;
import com.winsth.android.libs.adapters.CommonAdapter;
import com.winsth.android.libs.adapters.ViewHolder;
import com.winsth.android.libs.utils.DialogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 作者 Aaron Zhao
 * 时间 2019/1/23 17:36
 * 文件 ReadDataByBluetoothActivity.java
 * 描述
 */
public class ReadDataByBluetoothActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button btnStatus, btnSearch;
    private ListView lvDevices;
    private EditText etData;

    private BluetoothAdapter mBluetoothAdapter = null;

    private List<BluetoothDevice> mBluetoothDeviceList = new ArrayList<>();
    private MyAdapter mAdapter = null;

    private BlueToothSearchReceiver mBlueToothSearchReceiver;

    private static final int CONNECT_FAILURE = 2;
    private static final int CONNECT_SUCCESS = 3;
    private static final int WRITE_FAILED = 4;
    private static final int READ_FAILED = 5;
    private static final int READ_SUCCESS = 6;

    private static final String KEY_SOCKET = "socket";
    private static final String KEY_SOCKET_CONNECT = "connect";


    private int REQUEST_ENABLE_BT = 99;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case BlueToothSearchReceiver.DEVICE_NOT_FOUND:
                    DialogUtil.showToast(getApplicationContext(), "没有发现已开启的蓝牙设备");
                    break;
                case BlueToothSearchReceiver.DEVICE_FOUND:
                    BluetoothDevice device = (BluetoothDevice) msg.obj;

                    if (!mBluetoothDeviceList.contains(device))
                        mBluetoothDeviceList.add(device);

                    if (mAdapter != null)
                        mAdapter.notifyDataSetChanged();
                    break;
                case CONNECT_FAILURE:
                    DialogUtil.showToast(getApplicationContext(), "连接失败");
                    break;
                case CONNECT_SUCCESS:
                    DialogUtil.showToast(getApplicationContext(), "连接成功，开始读取数据");
                    new ThreadReadData((Map<String, Object>) msg.obj).start();
                    break;
                case WRITE_FAILED:
                    DialogUtil.showToast(getApplicationContext(), "写入失败");
                    break;
                case READ_FAILED:
                    DialogUtil.showToast(getApplicationContext(), "读取失败");
                    break;
                case READ_SUCCESS:
                    DialogUtil.showToast(getApplicationContext(), "读取成功");
                    String receive = msg.obj + "";
                    String content = etData.getText().toString() + "\r\n" + receive;
                    etData.setText(content);
                    etData.setSelection(content.length());
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data_by_bluetooth);

        btnStatus = (Button) findViewById(R.id.btn_open);
        btnSearch = (Button) findViewById(R.id.btn_search);
        lvDevices = (ListView) findViewById(R.id.lv_devices);
        etData = (EditText) findViewById(R.id.et_data);

        btnStatus.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        lvDevices.setOnItemClickListener(this);

        initBluetoothStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mBlueToothSearchReceiver = new BlueToothSearchReceiver(mHandler);
        // 注册接收周边可见蓝牙设备的广播接收器
        registerReceiver(mBlueToothSearchReceiver, mBlueToothSearchReceiver.getIntentFilter());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.cancelDiscovery();
        }

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open:
//                openOrCloseBlueTooth();
                checkBluetoothPermission();
                break;
            case R.id.btn_search:
                mBluetoothDeviceList.clear();
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }

                if (mBluetoothAdapter != null) {
                    mBluetoothAdapter.startDiscovery();
                }

                bindDevices();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mBluetoothDeviceList != null) {
            final BluetoothDevice device = mBluetoothDeviceList.get(position);
            int conStatus = device.getBondState();
            switch (conStatus) {
                case BluetoothDevice.BOND_BONDED:
                    /*try {
                        // 解除
                        Method removeBond = device.getClass().getMethod("removeBond",  (Class<?>[]) null);
                        removeBond.invoke(device);
                        if (mAdapter != null) {
                            mAdapter.notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/

                    DialogUtil.showToast(getApplicationContext(), "连接线程开始");
                    new ThreadBluetoothConnect(device).start();
                    break;
                case BluetoothDevice.BOND_NONE:
                    try {
                        // 配对
                        Method createBond = device.getClass().getMethod("createBond", (Class<?>[]) null);
                        createBond.invoke(device);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    /* 私有方法 */
    private void initBluetoothStatus() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            DialogUtil.showToast(getApplicationContext(), "当前设备不存在蓝牙模块");
        } else {
            if (mBluetoothAdapter.isEnabled()) {
//                mBluetoothAdapter.disable(); // 关闭蓝牙

                btnStatus.setText("蓝牙状态：开启");
            } else {
                btnStatus.setText("蓝牙状态：关闭");

                // 无需询问用户，直接开启蓝牙，需要android.permission.BLUETOOTH_ADMIN权限
                // mBluetoothAdapter.enable();
            }
        }
    }

    private void openOrCloseBlueTooth() {
        if (mBluetoothAdapter == null) {
            DialogUtil.showToast(getApplicationContext(), "当前设备不存在蓝牙模块");
        } else {
            if (mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.disable(); // 关闭蓝牙

                btnStatus.setText("蓝牙状态：关闭");
            } else {
                // 询问用户，如果用户同意就打开蓝牙
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300); // 300就表示300秒
                startActivity(intent);

                // 无需询问用户，直接开启蓝牙，需要android.permission.BLUETOOTH_ADMIN权限
                // mBluetoothAdapter.enable();

                btnStatus.setText("蓝牙状态：开启");
            }
        }
    }

    private void bindDevices() {
        // 获取蓝牙适配器中已经配对的设备
        if (mBluetoothAdapter != null) {
            Set<BluetoothDevice> deviceList = mBluetoothAdapter.getBondedDevices();
            if (deviceList != null && deviceList.size() > 0) {
                for (BluetoothDevice device : deviceList) {
                    mBluetoothDeviceList.add(device);
                }
            }
        }

        // 显示已经绑定和可见但是还没有绑定的设备信息
        mAdapter = new MyAdapter(mBluetoothDeviceList, R.layout.activity_read_data_by_bluetooth_item);
        lvDevices.setAdapter(mAdapter);
    }

    /**
     * 适配器
     */
    class MyAdapter extends CommonAdapter<BluetoothDevice> {
        public MyAdapter(List<BluetoothDevice> datas, int itemLayoutId) {
            super(ReadDataByBluetoothActivity.this, datas, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder holder, BluetoothDevice item) {
            holder.setText(R.id.tv_name, "设备名称：" + item.getName());
            holder.setText(R.id.tv_address, "设备地址：" + item.getAddress());
            holder.setText(R.id.tv_status, "匹配状态：" + getStatus(item.getBondState()));
        }

        private String getStatus(int status) {
            switch (status) {
                case BluetoothDevice.BOND_NONE:
                    return "未匹配";
                case BluetoothDevice.BOND_BONDING:
                    return "匹配中";
                case BluetoothDevice.BOND_BONDED:
                    return "已匹配";
                default:
                    return "未匹配";
            }
        }
    }

    /**
     * 蓝牙连接线程
     */
    class ThreadBluetoothConnect extends Thread {
        BluetoothDevice mDevice;

        public ThreadBluetoothConnect(BluetoothDevice device) {
            this.mDevice = device;
        }

        @Override
        public void run() {
            super.run();

            mBluetoothAdapter.cancelDiscovery();
            BluetoothSocket bluetoothSocket = null;

            /*try {
                final String spp_uuid = "00001101-0000-1000-8000-00805F9B34FB";
                UUID uuid = UUID.fromString(spp_uuid);
                bluetoothSocket = mDevice.createInsecureRfcommSocketToServiceRecord(uuid);
                bluetoothSocket.connect();

                Map<String, Object> map = new HashMap<>();
                map.put(KEY_SOCKET, bluetoothSocket);
                map.put(KEY_SOCKET_CONNECT, true);
                mHandler.sendMessage(Message.obtain(mHandler, CONNECT_SUCCESS, map));
            } catch (IOException e) {
                System.err.println(e.getMessage());
                mHandler.sendEmptyMessage(CONNECT_FAILURE);
            }*/

            try {
                Method method = mDevice.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
                bluetoothSocket = (BluetoothSocket) method.invoke(mDevice, 1);
                bluetoothSocket.connect();

                Map<String, Object> map = new HashMap<>();
                map.put(KEY_SOCKET, bluetoothSocket);
                map.put(KEY_SOCKET_CONNECT, true);
                mHandler.sendMessage(Message.obtain(mHandler, CONNECT_SUCCESS, map));
            } catch (Exception e) {
                mHandler.sendEmptyMessage(CONNECT_FAILURE);
            }
        }
    }

    class ThreadReadData extends Thread {
        Map<String, Object> mData;

        public ThreadReadData(Map<String, Object> data) {
            this.mData = data;
        }

        @Override
        public void run() {
            super.run();

            boolean connected = (boolean) mData.get(KEY_SOCKET_CONNECT);
            BluetoothSocket socket = (BluetoothSocket) mData.get(KEY_SOCKET);

            if (connected) {
            /*try {
                OutputStream outStream = socket.getOutputStream();
                outStream.write(getHexBytes(""));
            } catch (IOException e) {
                mHandler.sendEmptyMessage(WRITE_FAILED);
            }*/

                try {
                    InputStream inputStream = socket.getInputStream();
                    String data;
                    while (true) {
                        try {
                            byte[] buffer = new byte[1024];
                            inputStream.read(buffer);

                            data = new String(buffer);
                            Message msg = mHandler.obtainMessage();
                            msg.what = READ_SUCCESS;
                            msg.obj = socket.getRemoteDevice().getName() + "    " + data;
                            mHandler.sendMessage(msg);
                        } catch (IOException e) {
                            mHandler.sendEmptyMessage(READ_FAILED);
                            e.printStackTrace();
                            break;
                        }
                    }
                } catch (IOException e) {
                    mHandler.sendEmptyMessage(WRITE_FAILED);
                    e.printStackTrace();
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 私有方法
     */
    private byte[] getHexBytes(String message) {
        int len = message.length() / 2;
        char[] chars = message.toCharArray();
        String[] hexStr = new String[len];
        byte[] bytes = new byte[len];
        for (int i = 0, j = 0; j < len; i += 2, j++) {
            hexStr[j] = "" + chars[i] + chars[i + 1];
            bytes[j] = (byte) Integer.parseInt(hexStr[j], 16);
        }
        return bytes;
    }


    /* 搜索广播 */
    public class BlueToothSearchReceiver extends BroadcastReceiver {
        public static final int DEVICE_FOUND = 0;
        public static final int DEVICE_NOT_FOUND = 1;

        public Handler mHandler;

        public BlueToothSearchReceiver(Handler handler) {
            this.mHandler = handler;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action) || BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mHandler.sendMessage(Message.obtain(mHandler, DEVICE_FOUND, device));
            } else {
                mHandler.sendMessage(Message.obtain(mHandler, DEVICE_NOT_FOUND, ""));
            }
        }

        public IntentFilter getIntentFilter() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
            intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
            intentFilter.addAction(BluetoothDevice.ACTION_NAME_CHANGED);
            intentFilter.addAction(BluetoothDevice.ACTION_PAIRING_REQUEST);
            intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
            intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
            intentFilter.addAction(BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED);

            return intentFilter;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//蓝牙权限开启成功
                openOrCloseBlueTooth();
            } else {
                Toast.makeText(ReadDataByBluetoothActivity.this, "蓝牙权限未开启,请设置", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void checkBluetoothPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            //校验是否已具有模糊定位权限
            if (ContextCompat.checkSelfPermission(ReadDataByBluetoothActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ReadDataByBluetoothActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_ENABLE_BT);
            } else {//权限已打开
                openOrCloseBlueTooth();
            }
        } else {//小于23版本直接使用
            openOrCloseBlueTooth();
        }
    }
}
