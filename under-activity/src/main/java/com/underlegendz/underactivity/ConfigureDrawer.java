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
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.View;

class ConfigureDrawer {

  /**
   * Configure Activity's Drawer.
   *
   * @param builder Activity build configuration.
   */
  static void configureDrawer(ActivityBuilder builder, Activity activity, DrawerLayout drawerLayout) {
      // Build Start Drawer
      View drawerCustomLayout;
      if (builder.drawerCustomLayoutResource != null) {
        drawerCustomLayout = activity.getLayoutInflater()
            .inflate(builder.drawerCustomLayoutResource, drawerLayout, false);
      } else {
        drawerCustomLayout = builder.drawerCustomLayout;
      }
      DrawerLayout.LayoutParams lp = ActivityRender.getDrawerLayoutParams();
      lp.gravity = GravityCompat.START;
      if (drawerCustomLayout != null) { // CustomView
        drawerCustomLayout.setLayoutParams(lp);
        drawerLayout.addView(drawerCustomLayout);
      } else if (builder.drawerNavigationViewMenuResource != null) { // NavigationView
        NavigationView navigationView = new NavigationView(activity);
        navigationView.setLayoutParams(lp);
        drawerLayout.addView(navigationView);

        // Header
        if (builder.drawerNavigationViewHeader != null) {
          navigationView.addHeaderView(builder.drawerNavigationViewHeader);
        } else if (builder.drawerNavigationViewHeaderResource != null) {
          navigationView.inflateHeaderView(builder.drawerNavigationViewHeaderResource);
        }

        // Menu
        if (builder.drawerNavigationViewMenuResource != null) {
          navigationView.inflateMenu(builder.drawerNavigationViewMenuResource);
        }

        if (builder.drawerNavigationViewBackgroundColor != null) {
          navigationView.setBackgroundColor(builder.drawerNavigationViewBackgroundColor);
        }

        if (builder.drawerOnNavigationItemSelectedListener != null) {
          navigationView.setNavigationItemSelectedListener(
              builder.drawerOnNavigationItemSelectedListener);
        }
      }

      // Build End Drawer
      View endDrawerCustomLayout;
      if (builder.endDrawerCustomLayoutResource != null) {
        endDrawerCustomLayout = activity.getLayoutInflater()
            .inflate(builder.endDrawerCustomLayoutResource, drawerLayout, false);
      } else {
        endDrawerCustomLayout = builder.endDrawerCustomLayout;
      }

      DrawerLayout.LayoutParams lpEnd = ActivityRender.getDrawerLayoutParams();
      lpEnd.gravity = GravityCompat.END;
      if (endDrawerCustomLayout != null) { // CustomView
        endDrawerCustomLayout.setLayoutParams(lpEnd);
        drawerLayout.addView(endDrawerCustomLayout);
      } else if (builder.endDrawerNavigationViewMenuResource != null) { // NavigationView
        NavigationView endNavigationView = new NavigationView(activity);
        endNavigationView.setLayoutParams(lpEnd);
        drawerLayout.addView(endNavigationView);

        // Header
        if (builder.endDrawerNavigationViewHeader != null) {
          endNavigationView.addHeaderView(builder.endDrawerNavigationViewHeader);
        } else if (builder.endDrawerNavigationViewHeaderResource != null) {
          endNavigationView.inflateHeaderView(builder.endDrawerNavigationViewHeaderResource);
        }

        // Menu
        if (builder.endDrawerNavigationViewMenuResource != null) {
          endNavigationView.inflateMenu(builder.endDrawerNavigationViewMenuResource);
        }

        if (builder.endDrawerNavigationViewBackgroundColor != null) {
          endNavigationView.setBackgroundColor(builder.endDrawerNavigationViewBackgroundColor);
        }

        if (builder.endDrawerOnNavigationItemSelectedListener != null) {
          endNavigationView.setNavigationItemSelectedListener(
              builder.endDrawerOnNavigationItemSelectedListener);
        }
      }
    }
}