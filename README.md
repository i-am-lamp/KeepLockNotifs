# KeepLockNotifs

## What it does

OxygenOS hides notifications from the lock screen once you've unlocked, even though they're still in the shade. This module keeps them on the lock screen until you dismiss them.

## How it works

Whenever the phone locks, SystemUI stores the lock time (`NotificationCommHelper.lastGoKeyguardTime`). When it decides what to show on the lock screen, `OplusKeyguardNotificationVisibilityProviderExImpl.shouldHideNotification` hides any notification posted more than 10 seconds before that time.

That check has a built-in debug bypass, `OplusNotificationDebug.getSkipCheckPostedBeforeLock()`. The module hooks that method to return `true`, so only the "posted before lock" check is skipped. The stored lock time, notification sorting, Do Not Disturb and lock screen privacy settings are untouched.

## Requirements

- Root (Magisk, KernelSU or APatch)
- LSPosed, with System UI in the module's scope

Reboot after enabling.

## Compatibility

Tested on OxygenOS 16 (OnePlus CPH2747).

It should work on any OxygenOS, ColorOS or realme UI build whose SystemUI has `getSkipCheckPostedBeforeLock`. These share the same SystemUI code, but I haven't tested them.

The hook only applies if that method exists, takes no arguments and returns a boolean. Otherwise it does nothing, so on unsupported builds it fails silently rather than crashing SystemUI.

## Uninstall

Disable or uninstall the module in LSPosed and reboot.
