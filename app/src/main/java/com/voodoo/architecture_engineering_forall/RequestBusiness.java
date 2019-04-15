package com.voodoo.architecture_engineering_forall;

import com.voodoo.architecture_engineering_forall.Api.RequestApiInterface;
import com.voodoo.code_architecture.request.retrofit.BaseRequestBusiness;
import com.voodoo.code_architecture.request.retrofit.RetrofitRequestManager;

/**
 * @author sam
 * @version 1.0
 * @date 2018/3/20
 */

public class RequestBusiness extends BaseRequestBusiness {
    private RequestApiInterface requesteApiInterface;
    private static RequestBusiness mBaseRequestBusiness;

    /**
     * 单例模式，得到requestbusiness的实例
     * @return
     */
    public static synchronized RequestBusiness getInstance() {
        if (mBaseRequestBusiness == null) {
            mBaseRequestBusiness = new RequestBusiness();
        }
        return mBaseRequestBusiness;
    }

    /**
     * 得到接口API，调用相应的接口
     * @return
     */
    public RequestApiInterface getAPI() {
        //构建Retrofit
        if (requesteApiInterface == null) {
            requesteApiInterface =
                    RetrofitRequestManager.getInstance().getRetrofit().create(RequestApiInterface.class);
        }
        return requesteApiInterface;
    }
}
