package com.csab.rxjava_sandbox;

import android.app.Fragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowSupportMenuInflater;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.ANDROID.assertThat;

@Config(emulateSdk = 18, reportSdk = 18, shadows = {ShadowSupportMenuInflater.class})
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mActivity;

    @Before
    public void setup() {
        mActivity = Robolectric.buildActivity(MainActivity.class).create().resume().visible().get();
    }

    @Test
    public void testActivityState() {
        assertThat(mActivity).isNotNull().isNotFinishing();
    }

    @Test
    public void testFragmentState() {
        Fragment fragment = mActivity.getFragmentManager().findFragmentByTag("base");
        assertThat(fragment).isNotNull().isAdded().isResumed();
    }

}
