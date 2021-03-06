/*
 * WiFi Analyzer
 * Copyright (C) 2017  VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.vrem.wifianalyzer.wifi;

import android.view.View;
import android.widget.TextView;

import com.vrem.wifianalyzer.BuildConfig;
import com.vrem.wifianalyzer.MainActivity;
import com.vrem.wifianalyzer.R;
import com.vrem.wifianalyzer.RobolectricUtil;
import com.vrem.wifianalyzer.wifi.band.WiFiBand;
import com.vrem.wifianalyzer.wifi.band.WiFiChannelCountry;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ChannelAvailableAdapterTest {
    private ChannelAvailableAdapter fixture;
    private WiFiChannelCountry wiFiChannelCountry;

    @Before
    public void setUp() {
        MainActivity mainActivity = RobolectricUtil.INSTANCE.getActivity();

        wiFiChannelCountry = WiFiChannelCountry.get("US");
        //noinspection ArraysAsListWithZeroOrOneArgument
        fixture = new ChannelAvailableAdapter(mainActivity, Arrays.asList(wiFiChannelCountry));
    }

    @Test
    public void testGetView() throws Exception {
        // setup
        String expected = wiFiChannelCountry.getCountryCode() + " - " + wiFiChannelCountry.getCountryName();
        String expected_GHZ_2 = StringUtils.join(wiFiChannelCountry.getChannelsGHZ2().toArray(), ",");
        String expected_GHZ_5 = StringUtils.join(wiFiChannelCountry.getChannelsGHZ5().toArray(), ",");
        // execute
        View actual = fixture.getView(0, null, null);
        // validate
        assertNotNull(actual);

        assertEquals(expected, ((TextView) actual.findViewById(R.id.channel_available_country)).getText());
        assertEquals(WiFiBand.GHZ2.getBand() + " : ", ((TextView) actual.findViewById(R.id.channel_available_title_ghz_2)).getText());
        assertEquals(expected_GHZ_2, ((TextView) actual.findViewById(R.id.channel_available_ghz_2)).getText());
        assertEquals(WiFiBand.GHZ5.getBand() + " : ", ((TextView) actual.findViewById(R.id.channel_available_title_ghz_5)).getText());
        assertEquals(expected_GHZ_5, ((TextView) actual.findViewById(R.id.channel_available_ghz_5)).getText());
    }

}