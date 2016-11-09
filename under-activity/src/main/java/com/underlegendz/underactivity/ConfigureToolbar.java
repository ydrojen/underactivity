package com.underlegendz.underactivity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

class ConfigureToolbar {

  /**
   * Configure Activity's Toolbar.
   *
   * @param builder Activity build configuration.
   */
  static void configureToolbar(UnderActivity.Builder builder, UnderActivity underActivity) {
    if (builder.enableCoordinatorAppBarLayout) {
      underActivity.setContent(new CoordinatorLayout(underActivity));
    } else {
      LinearLayout content = new LinearLayout(underActivity);
      underActivity.setContent(content);
      content.setOrientation(LinearLayout.VERTICAL);
    }
    underActivity.getContent().setLayoutParams(underActivity.getDrawerLayoutParams());
    underActivity.getDrawerLayout().addView(underActivity.getContent(), 0);

    if (builder.enableToolbar) {

      if (builder.appBarLayout != null || builder.appBarLayoutResource != null) {
        AppBarLayout appBarLayout = builder.appBarLayout;
        if (appBarLayout == null) {
          appBarLayout = (AppBarLayout) underActivity.getLayoutInflater()
              .inflate(builder.appBarLayoutResource, underActivity.getContent(), false);
        }
        underActivity.getContent().addView(appBarLayout);
        for (int i = 0; i < appBarLayout.getChildCount(); i++) {
          View childView = appBarLayout.getChildAt(i);
          if (childView instanceof Toolbar) {
            underActivity.setToolbar((Toolbar) childView);
          }else if(childView instanceof TabLayout){
            underActivity.setTabLayout((TabLayout) childView);
          }
        }
      } else {
        Toolbar customToolbar;
        if (builder.toolbarResource != null) {
          customToolbar = (Toolbar) underActivity.getLayoutInflater()
              .inflate(builder.toolbarResource, underActivity.getContent(), false);
        } else {
          customToolbar = builder.toolbar;
        }

        if (customToolbar != null) {
          underActivity.setToolbar(customToolbar);
        } else {
          underActivity.setToolbar(new Toolbar(underActivity));
        }

        if (builder.enableCoordinatorAppBarLayout) {
          AppBarLayout appBarLayout = new AppBarLayout(underActivity);
          CoordinatorLayout.LayoutParams coordinatorLayoutParams =
              new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
          appBarLayout.setLayoutParams(coordinatorLayoutParams);

          AppBarLayout.LayoutParams appBarLayoutParams =
              new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
          appBarLayoutParams.setScrollFlags(builder.toolbarScrollFlags);
          underActivity.getToolbar().setLayoutParams(appBarLayoutParams);
          appBarLayout.addView(underActivity.getToolbar());

          if(builder.enableToolbarTabs){
            TabLayout tabLayout = null;

            if(builder.tabLayoutResource != null){
              tabLayout = (TabLayout) underActivity.getLayoutInflater().inflate(builder.tabLayoutResource, underActivity.getContent(), false);
            } else {
              tabLayout = builder.tabLayoutView;
            }

            if(tabLayout == null){
              tabLayout = new TabLayout(underActivity);
            }

            AppBarLayout.LayoutParams appBarLayoutParams_TabLayout =
                new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            appBarLayoutParams_TabLayout.setScrollFlags(0);
            tabLayout.setLayoutParams(appBarLayoutParams_TabLayout);
            if(builder.toolbarTabLayoutBackgroundColor != null){
              tabLayout.setBackgroundColor(builder.toolbarTabLayoutBackgroundColor);
            }else if(builder.toolbarBackgroundColor != null){
              tabLayout.setBackgroundColor(builder.toolbarBackgroundColor);
            }
            appBarLayout.addView(tabLayout);
            underActivity.setTabLayout(tabLayout);
          }

          coordinatorLayoutParams.setBehavior(new AppBarLayout.Behavior());
          appBarLayout.setLayoutParams(coordinatorLayoutParams);
          underActivity.getContent().addView(appBarLayout, 0);
        } else {
          LinearLayout.LayoutParams linearLayoutParams =
              new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
          underActivity.getToolbar().setLayoutParams(linearLayoutParams);
          underActivity.getContent().addView(underActivity.getToolbar());
        }
      }

      if (underActivity.getToolbar() != null) {
        if (builder.toolbarPopupTheme != null) {
          underActivity.getToolbar().setPopupTheme(builder.toolbarPopupTheme);
        }
        if (builder.toolbarBackgroundColor != null) {
          underActivity.getToolbar().setBackgroundColor(builder.toolbarBackgroundColor);
        }

        underActivity.setSupportActionBar(underActivity.getToolbar());
        final ActionBar ab = underActivity.getSupportActionBar();

        if (ab != null) {
          if (builder.toolbarBack) {
            if (builder.toolbarBackIcon != null) {
              ab.setHomeAsUpIndicator(builder.toolbarBackIcon);
            }
            ab.setDisplayHomeAsUpEnabled(true);
            underActivity.setBack(true);
          } else if (builder.toolbarDrawerIcon != null) {
            ab.setHomeAsUpIndicator(builder.toolbarDrawerIcon);
            ab.setDisplayHomeAsUpEnabled(true);
          }
        }
      }
    }
  }
}