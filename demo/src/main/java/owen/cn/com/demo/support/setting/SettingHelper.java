package owen.cn.com.demo.support.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Created by tao.lai on 2015/9/18 0018.
 */
public class SettingHelper {

    private static Editor editor = null;
    private static SharedPreferences sharedPreferences = null;

    private SettingHelper() {

    }

    private static Editor getEditorObject(Context paramContext) {
        if (editor == null)
            editor = PreferenceManager
                    .getDefaultSharedPreferences(paramContext).edit();
        return editor;
    }

    private static SharedPreferences getSharedPreferencesObject(
            Context paramContext) {
        if (sharedPreferences == null)
            sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(paramContext);
        return sharedPreferences;
    }

    public static int getSharedPreferences(Context paramContext,
                                           String paramString, int paramInt) {
        return getSharedPreferencesObject(paramContext).getInt(paramString,
                paramInt);
    }

    public static long getSharedPreferences(Context paramContext,
                                            String paramString, long paramLong) {
        return getSharedPreferencesObject(paramContext).getLong(paramString,
                paramLong);
    }

    public static Boolean getSharedPreferences(Context paramContext,
                                               String paramString, Boolean paramBoolean) {
        return getSharedPreferencesObject(paramContext).getBoolean(paramString,
                paramBoolean);
    }

    public static String getSharedPreferences(Context paramContext,
                                              String paramString1, String paramString2) {
        return getSharedPreferencesObject(paramContext).getString(paramString1,
                paramString2);
    }

    public static void setEditor(Context paramContext, String paramString,
                                 int paramInt) {
        getEditorObject(paramContext).putInt(paramString, paramInt).commit();
    }

    public static void setEditor(Context paramContext, String paramString,
                                 long paramLong) {
        getEditorObject(paramContext).putLong(paramString, paramLong).commit();
    }

    public static void setEditor(Context paramContext, String paramString,
                                 Boolean paramBoolean) {
        getEditorObject(paramContext).putBoolean(paramString, paramBoolean)
                .commit();
    }

    public static void setEditor(Context paramContext, String paramString1,
                                 String paramString2) {
        getEditorObject(paramContext).putString(paramString1, paramString2)
                .commit();
    }

}
