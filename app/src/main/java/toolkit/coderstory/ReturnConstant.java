package toolkit.coderstory;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;

public class ReturnConstant extends XC_MethodHook {
    private final XSharedPreferences prefs;
    private final String prefsKey;
    private final Object value;

    public ReturnConstant(XSharedPreferences prefs, String prefsKey, Object value) {
        this.prefs = prefs;
        this.prefsKey = prefsKey;
        this.value = value;
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        prefs.reload();
        if (prefs.getBoolean(prefsKey, true)) {
            XposedBridge.log("D/" + MainHook.TAG + " " + "set return value of " + param.method + " to " + value);
            param.setResult(value);
        }
    }
}
