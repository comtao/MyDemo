package owen.cn.com.demo.support.setting;

import android.content.Context;

import owen.cn.com.demo.GlobalContext;

/**
 * Created by tao.lai on 2015/9/18 0018.
 */
public class SettingUtility {
    private static final String FIRST_START = "firstStart";


    private SettingUtility() {

    }

    private static Context getContext() {
        return GlobalContext.getInstance();
    }

    public static boolean firstStart() {
        boolean value = SettingHelper.getSharedPreferences(getContext(),
                FIRST_START, true);
        if (value) {
            SettingHelper.setEditor(getContext(), FIRST_START, false);
        }
        return value;
    }
}
