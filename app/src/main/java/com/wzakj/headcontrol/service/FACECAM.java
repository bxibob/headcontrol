package com.wzakj.headcontrol.service;

import android.util.Log;


/***
 * Constants and common stuff
 */
public class FACECAM {
    public static final String TAG = FACECAM.class.getSimpleName();

    public static final boolean DEBUG = true;

    public static void debug(String message) {
        if ( DEBUG ) Log.d(TAG, message);
    }
}
