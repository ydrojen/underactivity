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

import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

class ConfigureToolbar {

  /**
   * Configure Activity's Toolbar.
   *
   * @param builder Activity build configuration.
   */
  static void configureToolbar(ActivityBuilder builder, AppCompatActivity activity,
      ViewGroup content) {

    Toolbar toolbarView = null;

    if (builder.appBarLayout != null || builder.appBarLayoutResource != null) {
      AppBarLayout appBarLayout = builder.appBarLayout;
      if (appBarLayout == null) {
        appBarLayout = (AppBarLayout) activity.getLayoutInflater()
            .inflate(builder.appBarLayoutResource, content, false);
      }
      content.addView(appBarLayout);
      if (activity instanceof UnderActivityBind) {
        for (int i = 0; i < appBarLayout.getChildCount(); i++) {
          View childView = appBarLayout.getChildAt(i);
          if (childView instanceof Toolbar) {
            toolbarView = (Toolbar) childView;
            ((UnderActivityBind) activity).bindToolbar((Toolbar) childView);
          } else if (childView instanceof TabLayout) {
            ((UnderActivityBind) activity).bindTabLayout((TabLayout) childView);
          }
        }
      }
    } else {
      if (builder.toolbarResource != null) {
        toolbarView =
            (Toolbar) activity.getLayoutInflater().inflate(builder.toolbarResource, content, false);
      } else {
        toolbarView = builder.toolbar;
      }

      if (toolbarView == null) {
        toolbarView = new Toolbar(activity);
      }
      if (activity instanceof UnderActivityBind) {
        ((UnderActivityBind) activity).bindToolbar(toolbarView);
      }

      AppBarLayout appBarLayout = new AppBarLayout(activity);
      CoordinatorLayout.LayoutParams coordinatorLayoutParams =
          new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.WRAP_CONTENT);
      appBarLayout.setLayoutParams(coordinatorLayoutParams);

      AppBarLayout.LayoutParams appBarLayoutParams;
      if (toolbarView.getLayoutParams() != null) {
        appBarLayoutParams = new AppBarLayout.LayoutParams(toolbarView.getLayoutParams());
        appBarLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
      } else {
        appBarLayoutParams = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
      }
      appBarLayoutParams.setScrollFlags(builder.toolbarScrollFlags);
      toolbarView.setLayoutParams(appBarLayoutParams);
      appBarLayout.addView(toolbarView);

      if (builder.enableToolbarTabs) {
        TabLayout tabLayout;
        if (builder.tabLayoutResource != null) {
          tabLayout = (TabLayout) activity.getLayoutInflater()
              .inflate(builder.tabLayoutResource, content, false);
        } else {
          tabLayout = builder.tabLayoutView;
        }

        if (tabLayout == null) {
          tabLayout = new TabLayout(activity);
        }

        AppBarLayout.LayoutParams appBarLayoutParams_TabLayout =
            new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        appBarLayoutParams_TabLayout.setScrollFlags(0);
        tabLayout.setLayoutParams(appBarLayoutParams_TabLayout);
        if (builder.toolbarTabLayoutBackgroundColor != null) {
          tabLayout.setBackgroundColor(builder.toolbarTabLayoutBackgroundColor);
        } else if (builder.toolbarBackgroundColor != null) {
          tabLayout.setBackgroundColor(builder.toolbarBackgroundColor);
        }
        appBarLayout.addView(tabLayout);

        if (activity instanceof UnderActivityBind) {
          ((UnderActivityBind) activity).bindTabLayout(tabLayout);
        }
      }

      coordinatorLayoutParams.setBehavior(new AppBarLayout.Behavior());
      appBarLayout.setLayoutParams(coordinatorLayoutParams);
      content.addView(appBarLayout, 0);
    }

    if (toolbarView != null) {
      if (builder.toolbarPopupTheme != null) {
        toolbarView.setPopupTheme(builder.toolbarPopupTheme);
      }
      if (builder.toolbarBackgroundColor != null) {
        toolbarView.setBackgroundColor(builder.toolbarBackgroundColor);
      }
      if (builder.toolbarTitleColor != null) {
        toolbarView.setTitleTextColor(builder.toolbarTitleColor);
      }

      activity.setSupportActionBar(toolbarView);
      final ActionBar ab = activity.getSupportActionBar();

      if (ab != null) {
        if (builder.toolbarBack) {
          if (builder.toolbarBackIcon != null) {
            ab.setHomeAsUpIndicator(builder.toolbarBackIcon);
          }
          ab.setDisplayHomeAsUpEnabled(true);
          if (activity instanceof UnderActivityBind) {
            ((UnderActivityBind) activity).goBackOnHome(true);
          }
        } else if (builder.toolbarDrawerIcon != null) {
          ab.setHomeAsUpIndicator(builder.toolbarDrawerIcon);
          ab.setDisplayHomeAsUpEnabled(true);
        }
      }
    }
  }
}