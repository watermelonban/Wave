package com.watermelonban.wave;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * 主要入口页面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout mTabComputer;
    private LinearLayout mTabPrinter;
    private LinearLayout mTabMonitor;

    private ImageButton mImgComputer;
    private ImageButton mImgPrinter;
    private ImageButton mImgMonitor;

    private Fragment mComputerFragment;
    private Fragment mPrinterFragment;
    private Fragment mMonitorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();

        setSelect(0);
    }

    //初始化Tab布局元素响应事件
    private void initEvent(){
        mTabComputer.setOnClickListener(this);
        mTabPrinter.setOnClickListener(this);
        mTabMonitor.setOnClickListener(this);
    }

    //初始化布局
    private void initView(){
        //初始化Tab布局元素，用于点击响应对象
        mTabComputer = (LinearLayout) this.findViewById(R.id.id_tab_computer);
        mTabPrinter = (LinearLayout) this.findViewById(R.id.id_tab_printer);
        mTabMonitor = (LinearLayout) this.findViewById(R.id.id_tab_monitor);

        //初始化Tab图标，用户点击切换图标状态
        mImgComputer = (ImageButton) this.findViewById(R.id.id_tab_computer_img);
        mImgPrinter = (ImageButton) this.findViewById(R.id.id_tab_printer_img);
        mImgMonitor = (ImageButton) this.findViewById(R.id.id_tab_monitor_img);
    }

    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId()){
            case R.id.id_tab_computer:
                setSelect(0);
                break;
            case R.id.id_tab_printer:
                setSelect(1);
                break;
            case R.id.id_tab_monitor:
                setSelect(2);
                break;
            default:
                break;
        }
    }

    /**
     * 恢复所有icon初始化
     */
    private void resetImgs(){
        mImgComputer.setImageResource(R.mipmap.ic_launcher);
        mImgPrinter.setImageResource(R.mipmap.ic_launcher);
        mImgMonitor.setImageResource(R.mipmap.ic_launcher);
    }

    private void setSelect(int i){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //隐藏所有的fragment
        hideFragment(transaction);
        //显示某一个fragment
        switch (i) {
            case 0:
                if (mComputerFragment == null) {
                    //没有电脑fragment创建并添加
                    mComputerFragment = new ComputerFragment();
                    transaction.add(R.id.id_content,mComputerFragment);
                } else{
                    //有直接显示出来
                    transaction.show(mComputerFragment);
                }
                mImgComputer.setImageResource(R.mipmap.ic_launcher);
                break;
            case 1:
                if (mPrinterFragment == null) {
                    mPrinterFragment = new PrinterFragment();
                    transaction.add(R.id.id_content,mPrinterFragment);
                } else{
                    transaction.show(mPrinterFragment);
                }
                mImgPrinter.setImageResource(R.mipmap.ic_launcher);
                break;
            case 2:
                if (mMonitorFragment == null) {
                    mMonitorFragment = new MonitorFragment();
                    transaction.add(R.id.id_content,mMonitorFragment);
                } else{
                    transaction.show(mMonitorFragment);
                }
                mImgMonitor.setImageResource(R.mipmap.ic_launcher);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction){
        if(mComputerFragment!=null){
            transaction.hide(mComputerFragment);
        }
        if (mPrinterFragment!=null) {
            transaction.hide(mPrinterFragment);
        }
        if(mMonitorFragment!=null){
            transaction.hide(mMonitorFragment);
        }
    }
}
