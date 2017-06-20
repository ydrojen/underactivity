package com.underlegendz.underactivity;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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