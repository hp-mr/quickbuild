package com.mr.quickbuild.utils;


import com.mr.quickbuild.R;

/**
 * @Description: java类作用描述
 * @Author: kirali
 * @CreateDate: 2019/1/9 0009 15:35
 * @UpdateUser: kirali
 * @UpdateDate: 2019/1/9 0009 15:35
 * @UpdateRemark: 更新说明
 */
public class ResourceUtils {

    public static int getLevelImageResId(int level)
    {
        int resId = 0;
        try
        {
            String imageName = String.valueOf("ic_level" + level);
            resId= MainUtils.getContext().getResources().getIdentifier(imageName, "drawable", MainUtils.getContext().getPackageName());
        } catch (Exception e)
        {
            resId = 0;
        }
        return resId;
    }
}
