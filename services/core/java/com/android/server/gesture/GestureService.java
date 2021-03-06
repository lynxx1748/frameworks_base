/*
 * Copyright (C) 2013 The CyanogenMod Project (Jens Doll)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.android.server.gesture;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.service.gesture.IGestureService;
import android.util.Slog;

import com.android.server.input.InputManagerService;

/**
 * A system service to track gesture sensor gestures. This service is
 * responsible for creating input events from motion events generated by
 * gesture sensor input hardware:
 * <li>Installing an input filter to listen for gesture sensor events</li>
 * <li>Generating input events to be injected into the input stream</li>
 */
public class GestureService extends IGestureService.Stub {
    public static final String TAG = "GestureService";
    public static final boolean DEBUG = false;

    private Context mContext;
    private InputManagerService mInputManager;
    private GestureInputFilter mInputFilter;

    public GestureService(Context context, InputManagerService inputManager) {
        mContext = context;
        mInputManager = inputManager;
    }

    // called by system server
    public void systemReady() {
        if (DEBUG) Slog.d(TAG, "Starting Gesture Sensor service");
        mInputFilter = new GestureInputFilter(mContext);
    }

    public void setOnLongPressPendingIntent(PendingIntent pendingIntent) {
        mInputFilter.setOnLongPressPendingIntent(pendingIntent);
    }

    public void setOnDoubleClickPendingIntent(PendingIntent pendingIntent) {
        mInputFilter.setOnDoubleClickPendingIntent(pendingIntent);
    }
}
