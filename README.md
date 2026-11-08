# KeepLockNotifs

## What it does

OxygenOS and ColorOS hide notifications from the lock screen once you've unlocked. Why?? - I don't know. This module keeps them on the lock screen until you dismiss them, as you'd expect. I have no idea who decided to design this feature initially but it annoyed me so much, so I made this.

## Requirements

- Root (Magisk, KernelSU, KernelSU-Next or APatch)
- LSPosed

Reboot after enabling.

## Compatibility

Tested on:
- OnePlus 15 (CPH2747)
- OxygenOS 16.0.10.601
- KernelSU-Next v3.4.2
- LSPosed Irena v2.0.0

It should work on any OxygenOS, ColorOS or realme UI build whose SystemUI has `getSkipCheckPostedBeforeLock`. These share the same SystemUI code but I haven't tested them.

The hook only applies if that method exists, takes no arguments and returns a boolean. Otherwise it does nothing, so on unsupported builds it fails silently rather than crashing SystemUI.

## How it works

Whenever the phone locks, SystemUI stores the lock time (`NotificationCommHelper.lastGoKeyguardTime`). When it decides what to show on the lock screen `OplusKeyguardNotificationVisibilityProviderExImpl.shouldHideNotification` hides any notification posted more than 10 seconds before that time.

That check has a built-in debug bypass `OplusNotificationDebug.getSkipCheckPostedBeforeLock()`. The module hooks that method to return `true`, so only the "posted before lock" check is skipped. The stored lock time, notification sorting, Do Not Disturb and lock screen privacy settings are untouched.

## Uninstall

Disable or uninstall the module in LSPosed and reboot.
