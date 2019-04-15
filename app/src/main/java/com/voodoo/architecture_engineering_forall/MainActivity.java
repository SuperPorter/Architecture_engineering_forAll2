package com.voodoo.architecture_engineering_forall;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jaeger.library.StatusBarUtil;
import com.voodoo.code_architecture.Base.BaseActivity;
import com.voodoo.code_architecture.Base.BaseActivityTitlebar;
import com.voodoo.code_architecture.Base.BaseTempActivity;
import com.voodoo.code_architecture.BaseApplication;
import com.voodoo.code_architecture.Utils.android.DialogUtils;
import com.voodoo.code_architecture.Utils.android.ToastUtils;
import com.voodoo.code_architecture.request.data.pojo.GankResp;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btnHideHead)
    Button mBtnHideHead;
    @BindView(R.id.btnShowHead)
    Button mBtnShowHead;
    @BindView(R.id.btnSetTitle)
    Button mBtnSetTitle;
    @BindView(R.id.btnTopLeft)
    Button mBtnTopLeft;
    @BindView(R.id.btnTopRight)
    Button mBtnTopRight;
    @BindView(R.id.btnShowLoadDialog)
    Button mBtnShowLoadDialog;
    @BindView(R.id.btnShowLoadDialogCancle)
    Button mBtnShowLoadDialogCancle;
    @BindView(R.id.btnShowLoad)
    Button mBtnShowLoad;
    @BindView(R.id.btnShowEror)
    Button mBtnShowEror;
    @BindView(R.id.btnShowEmpty)
    Button mBtnShowEmpty;
    private Context mContext;
    @Override
    protected void setScreenManager() {
//        StatusBarUtil.setColor(this, Color.BLACK);
        StatusBarUtil.setTranslucent(this, 255);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initData() {
        RequestBusiness.getInstance().getAPI().getGank("http://gank.io/api/data/福利/1000/1").enqueue(new Callback<GankResp>() {
            @Override
            public void onResponse(Call<GankResp> call, Response<GankResp> response) {
                if (response.isSuccessful()) {
                    ToastUtils.showLong("我是加载到的数据：" + response.body().results);
                }else{
                    DialogUtils.showAlert(BaseApplication.getContext(), "错误", response.code()+"",
                            "重新请求", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }, "取消请求", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                }
            }

            @Override
            public void onFailure(Call<GankResp> call, Throwable t) {
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutId());
        setModuleTitle("测试页面");
        mContext = this;
    }

    @Override
    protected void onClickFailureResetButton(View view) {
        super.onClickFailureResetButton(view);
        ToastUtils.showShort("点击了重新加载");

    }

    @Override
    protected void onClickTitlebarRight(View view) {
        super.onClickTitlebarRight(view);
        ToastUtils.showShort("点击了标题栏右边");
    }
    @OnClick({R.id.btnHideHead, R.id.btnShowHead, R.id.btnSetTitle, R.id.btnTopLeft, R.id.btnTopRight, R.id.btnShowLoadDialog, R.id.btnShowLoadDialogCancle, R.id.btnShowLoad, R.id.btnShowEror, R.id.btnShowEmpty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnHideHead:
                hideHeadView();
                break;
            case R.id.btnShowHead:
                showHeadView();
                break;
            case R.id.btnSetTitle:
                setModuleTitle("从新设置标题");
                break;
            case R.id.btnTopLeft:
                //只显示默认返回箭头
                showTopLeftButton();
                //文字
                //            showTopLeftButton("返回");
                //也可以自己设置返回箭头
                //            showTopLeftButton("返回", R.mipmap.ic_launcher);
                break;
            case R.id.btnTopRight:
                //设置文字
                showTopRightImg(R.drawable.vbtn_titlebar_me);
                //图片
                //            showTopRightImg(R.mipmap.share);
                break;
            case R.id.btnShowLoadDialog:
                showLoadingDialog();
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideLoadingDialog();
                            }
                        });
                    }
                }.start();
                break;
            case R.id.btnShowLoadDialogCancle:
                showLoadingDialogCancle();
                break;
            case R.id.btnShowLoad:
                showLoadingBar();
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideLoadingBar();
                            }
                        });
                    }
                }.start();
                break;
            case R.id.btnShowEror:
                showErrorLayout();
                break;
            case R.id.btnShowEmpty:
                showEmptyLayout("空页面");
                break;
        }
    }
}
