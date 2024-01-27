package com.wzakj.headcontrol.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class LayoutManger {
    private Context mContext;
    private FeedbackOverlayView mFOV;

    LayoutManger (Context context) {
        mContext = context;
    }


    /**
     *   Set overlay window to provide visual feedback
     */
    @SuppressLint("WrongConstant")
    void createFeedbackOverlay() {
        if (mFOV != null) return;

        LayoutParams feedbackParams = new LayoutParams();

        feedbackParams.setTitle("FeedbackOverlay");

        // Transparent background  透明背景
        feedbackParams.format = PixelFormat.TRANSLUCENT;

        // Create an always on top window
        feedbackParams.type = LayoutParams.TYPE_PHONE | LayoutParams.TYPE_SYSTEM_OVERLAY;

        // Whole screen is covered (including status bar)
        feedbackParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        mFOV = new FeedbackOverlayView(mContext);
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.addView(mFOV, feedbackParams);

        FACECAM.debug("finish createFeedbackOverlay");
    }

    void destroyFeedbackOverlay() {
        if (mFOV == null) return;

        WindowManager wm= (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.removeViewImmediate(mFOV);
        mFOV = null;
        FACECAM.debug("finish destroyFeedbackOverlay");
    }

}
