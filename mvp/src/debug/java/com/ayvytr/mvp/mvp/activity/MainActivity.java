package com.ayvytr.mvp.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.ayvytr.mvp.BaseMvpActivity;
import com.ayvytr.mvp.R;
import com.ayvytr.mvp.WeatherBeseEntity;
import com.ayvytr.mvp.mvp.contract.MainContract;
import com.ayvytr.mvp.mvp.presenter.MainPresenter;
import com.ayvytr.network.ApiClient;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    private TextView tv;
    private TextView tvWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ApiClient.getInstance().init();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
    }


    @Override
    public void initExtra() {
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        tv = findViewById(R.id.tv);
        tvWeather = findViewById(R.id.tvWeather);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.getData();
        mPresenter.getWeather();
    }

    @Override
    public int getContentViewRes() {
        return R.layout.activity_main;
    }

    @Override
    public void showInterval(Long aLong) {
        tv.setText(String.valueOf(aLong));
    }

    @Override
    public void showWeather(WeatherBeseEntity weatherBeseEntity) {
        tvWeather.setText(weatherBeseEntity.getResult().get(0).getCity() + " " + weatherBeseEntity.getResult().get(0)
                                                                                                  .getWeather());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
