/*
 * ====================================================================
 * Copyright (C) 2022 AbandonedCart @ TagMo
 * ====================================================================
 */

package com.hiddenramblings.tagmo.amiibo.tagdata;

import com.hiddenramblings.tagmo.nfctech.TagArray;

import java.io.IOException;
import java.util.Arrays;

public class AppDataSplatoon extends AppData {

    public AppDataSplatoon(byte[] appData) throws IOException {
        super(appData);
    }
}