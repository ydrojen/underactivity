/*
 * Created by Jose Fuentes on 9/12/17 17:31
 * Copyright (C) 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License"),
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.underlegendz.underactivity;

import android.app.Activity;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.underlegendz.library.R;

class ConfigureContent {

  static void configureContent(ActivityBuilder builder, Activity activity, ViewGroup content) {
    View customLayout;
    if (builder.contentLayoutResource != null) {
      customLayout =
          activity.getLayoutInflater().inflate(builder.contentLayoutResource, content, false);
    } else {
      customLayout = builder.contentLayout;
    }
    if (customLayout != null) {
      content.addView(customLayout);
      ViewGroup.LayoutParams layoutParams = customLayout.getLayoutParams();
      layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
      layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
      ((CoordinatorLayout.LayoutParams) layoutParams).setBehavior(
          new AppBarLayout.ScrollingViewBehavior());
    } else {
      FrameLayout container = new FrameLayout(activity);
      container.setId(R.id.main_content);
      content.addView(container);
      ViewGroup.LayoutParams layoutParams = container.getLayoutParams();
      layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
      layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
      ((CoordinatorLayout.LayoutParams) layoutParams).setBehavior(
          new AppBarLayout.ScrollingViewBehavior());
    }
  }
}