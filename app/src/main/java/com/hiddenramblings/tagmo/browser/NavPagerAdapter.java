package com.hiddenramblings.tagmo.browser;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hiddenramblings.tagmo.BuildConfig;
import com.hiddenramblings.tagmo.TagMo;

public class NavPagerAdapter extends FragmentStateAdapter {

    Preferences mPrefs = new Preferences(TagMo.getContext());

    private final BrowserFragment fragmentBrowser = new BrowserFragment();
    private final EliteBankFragment fragmentElite = new EliteBankFragment();
    private final FlaskSlotFragment fragmentFlask = new FlaskSlotFragment();
    private final SettingsFragment fragmentSettings = new SettingsFragment();
    private final WebsiteFragment fragmentWebsite = new WebsiteFragment();

    public NavPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @SuppressLint("NewApi")
    @NonNull @Override
    public Fragment createFragment(int position) {
        boolean hasEliteEnabled = mPrefs.elite_support();
        boolean hasFlaskEnabled = mPrefs.flask_support();
        switch (position) {
            case 1:
                return (!BuildConfig.WEAR_OS && hasEliteEnabled) ? fragmentElite : hasFlaskEnabled
                        ? fragmentFlask : BuildConfig.WEAR_OS ? fragmentSettings : fragmentWebsite;
            case 2:
                return ((!BuildConfig.WEAR_OS && hasEliteEnabled) && hasFlaskEnabled) ? fragmentFlask
                        : (BuildConfig.WEAR_OS && hasFlaskEnabled) ? fragmentSettings : fragmentWebsite;
            case 3:
                return fragmentWebsite;
            default:
                return fragmentBrowser;
        }
    }

    @Override
    public int getItemCount() {
        int viewCount = 2;
        if (BuildConfig.WEAR_OS) viewCount += 1;
        if (mPrefs.elite_support()) viewCount += 1;
        if (mPrefs.flask_support()) viewCount += 1;
        return viewCount;
    }

    public BrowserFragment getBrowser() {
        return fragmentBrowser;
    }

    public EliteBankFragment getEliteBanks() {
        return fragmentElite;
    }

    public FlaskSlotFragment getFlaskSlots() {
        return fragmentFlask;
    }

    public SettingsFragment getSettings() {
        return fragmentSettings;
    }

    public WebsiteFragment getWebsite() {
        return fragmentWebsite;
    }
}

