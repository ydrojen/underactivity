package com.underlegendz.underactivity_sample;
/*
 * Copyright (C) 2016 Jose Fuentes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.Bundle;
import com.underlegendz.underactivity.UnderActivity;

public class DemoActivity extends UnderActivity {

  static final String KEY_TOOLBAR_ENABLE = "TOOLBAR_ENABLE";
  static final String KEY_TOOLBAR_BG_COLOR = "TOOLBAR_BG_COLOR";
  static final String KEY_TOOLBAR_BACK = "TOOLBAR_BACK";
  static final String KEY_TOOLBAR_SCROLLFLAGS = "TOOLBAR_SCROLLFLAGS";
  static final String KEY_TOOLBAR_TABS = "TOOLBAR_TABS";
  static final String KEY_TOOLBAR_TABS_SCROLLFLAGS = "TOOLBAR_TABS_SCROLLFLAGS";
  static final String KEY_TOOLBAR_TABS_BG_COLOR = "TOOLBAR_TABS_BG_COLOR";
  static final String KEY_DRAWER_ENABLE = "DRAWER_ENABLE";
  static final String KEY_START_DRAWER_TYPE = "START_DRAWER_TYPE";
  static final String KEY_START_NV_HEADER = "START_NV_HEADER";
  static final String KEY_START_BG_COLOR = "START_BG_COLOR";

  @Override protected Builder configureActivityBuilder(Builder builder) {
    return builder
        .setContentLayout(R.layout.main);
  }

  @Override public void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
  }
}
