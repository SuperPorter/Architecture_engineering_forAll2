package com.voodoo.code_architecture.Base;

import android.content.Context;

/**
 * Description:基类presenter
 */
public abstract class BasePresenter<T extends BaseUiView, E extends BaseModel> {
    public Context mContext;
    public E mModel;
    public T mView;

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart() {
    }


    public void onDestroy() {
        mModel.onDestroy();
    }

    public void okRefresh(){

    }

    public void okLoadMore(){

    }
}
