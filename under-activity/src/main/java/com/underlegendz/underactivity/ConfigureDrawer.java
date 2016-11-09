package com.underlegendz.underactivity;

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
  static void configureDrawer(UnderActivity.Builder builder, UnderActivity underActivity) {
    if (builder.enableDrawer) {
      // Build Start Drawer
      View drawerCustomLayout;
      if (builder.drawerCustomLayoutResource != null) {
        drawerCustomLayout = underActivity.getLayoutInflater()
            .inflate(builder.drawerCustomLayoutResource, underActivity.getDrawerLayout(), false);
      } else {
        drawerCustomLayout = builder.drawerCustomLayout;
      }

      drawerCustomLayout.setFitsSystemWindows(true); //TODO

      DrawerLayout.LayoutParams lp = underActivity.getDrawerLayoutParams();
      lp.gravity = GravityCompat.START;
      if (drawerCustomLayout != null) { // CustomView
        drawerCustomLayout.setLayoutParams(lp);
        underActivity.getDrawerLayout().addView(drawerCustomLayout);
      } else if (builder.drawerNavigationViewMenuResource != null) { // NavigationView
        NavigationView navigationView = new NavigationView(underActivity);
        navigationView.setLayoutParams(lp);
        underActivity.getDrawerLayout().addView(navigationView);

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
        endDrawerCustomLayout = underActivity.getLayoutInflater()
            .inflate(builder.endDrawerCustomLayoutResource, underActivity.getDrawerLayout(), false);
      } else {
        endDrawerCustomLayout = builder.endDrawerCustomLayout;
      }

      DrawerLayout.LayoutParams lpEnd = underActivity.getDrawerLayoutParams();
      lpEnd.gravity = GravityCompat.END;
      if (endDrawerCustomLayout != null) { // CustomView
        endDrawerCustomLayout.setLayoutParams(lpEnd);
        underActivity.getDrawerLayout().addView(endDrawerCustomLayout);
      } else if (builder.endDrawerNavigationViewMenuResource != null) { // NavigationView
        NavigationView endNavigationView = new NavigationView(underActivity);
        endNavigationView.setLayoutParams(lpEnd);
        underActivity.getDrawerLayout().addView(endNavigationView);

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
}