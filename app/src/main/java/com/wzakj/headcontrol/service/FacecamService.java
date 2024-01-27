package com.wzakj.headcontrol.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class FacecamService extends AccessibilityService {

    private HeartBeat mHeartBeat;
    private LayoutManger mLayoutManger;

    /**
     * Called when the accessibility service is started
     */
    @Override
    public void onCreate() {
        super.onCreate();
        android.os.Debug.waitForDebugger();
        FACECAM.debug("onCreate");

        mHeartBeat = new HeartBeat(this);
    }

    /**
     *  Called every time the service is switched ON
     */
    @Override
    public void onServiceConnected() {
        FACECAM.debug("onServiceConnected");

        // Unsubscribe all accessibility events. Cannot be removed directly from
        // @xml/accessibilityservice, otherwise onUnbind and onDestroy never get called
        setServiceInfo(new AccessibilityServiceInfo());


        Toast toast = Toast.makeText(this.getApplicationContext(), "onServiceConnected", Toast.LENGTH_SHORT);
        toast.show();

        mHeartBeat.start();

        mLayoutManger= new LayoutManger(this.getApplicationContext());
        mLayoutManger.createFeedbackOverlay();
    }


    /**
     * Called when service is switched off
     */
    @Override
    public boolean onUnbind (Intent intent) {
        FACECAM.debug("onUnbind");

        mLayoutManger.destroyFeedbackOverlay();
        mLayoutManger= null;
        mHeartBeat.stop();

        return false;
    }

    /**
     * Called when service is switched off after onUnbind
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        FACECAM.debug("onDestroy");

        mHeartBeat.stop();
    }


    /**
     * (required) This method is called back by the system when it detects an
     * AccessibilityEvent that matches the event filtering parameters specified
     * by your accessibility service.
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        FACECAM.debug("onAccessibilityEvent");
    }


    /**
     * (required) This method is called when the system wants to interrupt the
     * feedback your service is providing, usually in response to a user action
     * such as moving focus to a different control. This method may be called
     * many times over the lifecycle of your service.
     */
    @Override
    public void onInterrupt() {
        FACECAM.debug("onInterrupt");
    }
}