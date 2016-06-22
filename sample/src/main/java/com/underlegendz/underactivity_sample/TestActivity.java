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

public class TestActivity extends UnderActivity {
  @Override protected Builder configureActivityBuilder(Builder builder) {
    return builder.enableToolbar(true)
        .setToolbarBackgroundColor(getResources().getColor(R.color.accentColor))
        .enableCoordinatorAppBarLayout(true)
        .setDrawerCustomLayoutResource(R.layout.custom_drawer)
        .setEndDrawerCustomLayoutResource(R.layout.custom_drawer);
  }

  @Override public void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    setFragment(new RecyclerFragment());
  }
}
