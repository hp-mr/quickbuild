package com.mr.quickbuild.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


import com.mr.quickbuild.dialog.LoadingDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class MyObserver<T> implements Observer<Result<T>> {
    private LoadingDialog dialog;
//    private MaterialDialog.Builder dialog_login;
    Context mContext;
    boolean mShowDialog,mShow_login=true;
    private Disposable d;
    public MyObserver(Context context, Boolean showDialog) {
        mContext = context;
        mShowDialog = showDialog;
    }
    public MyObserver(Context context, Boolean showDialog,Boolean show_login) {
        mContext = context;
        mShowDialog = showDialog;
        mShow_login = show_login;
    }
    public MyObserver(Context context) {
        this(context, false);
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        if (!isConnected(mContext)) {
            Toast.makeText(mContext, "未连接网络", Toast.LENGTH_SHORT).show();
            if (d.isDisposed()) {
                d.dispose();
            }
        } else {
            if(mShowDialog){
                if (dialog == null) {
                    dialog = new LoadingDialog(mContext);
                    dialog.setDesc("正在加载中");
                }
                dialog.show();
            }
        }
    }

    @Override
    public void onNext(Result<T> value) {
        if (value.getCode() == 0) {
            Log.e("OkHttp", "成功");
            onSuccess(value.getData());
        } else if(value.getCode()==42002){//token 无效
//            if(null==dialog_login){
//                dialog_login= new MaterialDialog.Builder(mContext)
//                        .title("是否立即去登录？")
//                        .positiveText("确认")
//                        .negativeText("取消")
//                        .theme(Theme.LIGHT)
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                mContext.startActivity(new Intent(mContext, PwdLoginActivity.class));
//                            }
//                        });
//
//            }
//            if(mShow_login){
//                dialog_login.show();
//            }
        }else {
            onError(value.getMessage());
            Log.e("OkHttp", "失败");
        }
    }

    @Override
    public void onError(Throwable e) {
        onError(e.getMessage());
        Log.e("OkHttp", "onError");
        if (d.isDisposed()) {
            d.dispose();
        }
    }

    @Override
    public void onComplete() {
        if (d.isDisposed()) {
            d.dispose();
        }
        if (dialog != null&&dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    /**
     * 是否有网络连接，不管是wifi还是数据流量
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        boolean available = info.isAvailable();
        return available;
    }


    protected abstract void onSuccess(T t);

    protected void onError(String s) {

    }

}
