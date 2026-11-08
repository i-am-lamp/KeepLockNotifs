package com.keeplocknotifs;

import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (!lpparam.packageName.equals("com.android.systemui")) return;
        try {
            Method m = lpparam.classLoader
                    .loadClass("com.oplus.systemui.notification.debug.OplusNotificationDebug")
                    .getDeclaredMethod("getSkipCheckPostedBeforeLock");
            if (m.getReturnType() == boolean.class) {
                XposedBridge.hookMethod(m, XC_MethodReplacement.returnConstant(true));
            }
        } catch (Throwable ignored) {
        }
    }
}
