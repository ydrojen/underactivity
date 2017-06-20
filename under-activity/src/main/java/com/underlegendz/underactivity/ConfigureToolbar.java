package com.underlegendz.underactivity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
  static void configureToolbar(ActivityBuilder builder, AppCompatActivity activity, ViewGroup content) {

    Toolbar toolbarView = null;

    if (builder.appBarLayout != null || builder.appBarLayoutResource != null) {
      AppBarLayout appBarLayout = builder.appBarLayout;
      if (appBarLayout == null) {
        appBarLayout = (AppBarLayout) activity.getLayoutInflater()
            .inflate(builder.appBarLayoutResource, content, false);
      }
      content.addView(appBarLayout);
      if(activity instanceof UnderActivityBind) {
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
        toolbarView = (Toolbar) activity.getLayoutInflater()
            .inflate(builder.toolbarResource, content, false);
      } else {
        toolbarView = builder.toolbar;
      }

      if (toolbarView == null) {
        toolbarView = new Toolbar(activity);
      }
      if (activity instanceof UnderActivityBind){
        ((UnderActivityBind) activity).bindToolbar(toolbarView);
      }

      if (builder.enableCoordinatorAppBarLayout) {
        AppBarLayout appBarLayout = new AppBarLayout(activity);
        CoordinatorLayout.LayoutParams coordinatorLayoutParams =
            new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        appBarLayout.setLayoutParams(coordinatorLayoutParams);

        AppBarLayout.LayoutParams appBarLayoutParams =
            new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        appBarLayoutParams.setScrollFlags(builder.toolbarScrollFlags);
        toolbarView.setLayoutParams(appBarLayoutParams);
        appBarLayout.addView(toolbarView);

        if (builder.enableToolbarTabs) {
          TabLayout tabLayout = null;

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

          if(activity instanceof UnderActivityBind){
            ((UnderActivityBind) activity).bindTabLayout(tabLayout);
          }
        }

        coordinatorLayoutParams.setBehavior(new AppBarLayout.Behavior());
        appBarLayout.setLayoutParams(coordinatorLayoutParams);
        content.addView(appBarLayout, 0);
      } else {
        LinearLayout.LayoutParams linearLayoutParams =
            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        toolbarView.setLayoutParams(linearLayoutParams);
        content.addView(toolbarView);
      }
    }

    if (toolbarView != null) {
      if (builder.toolbarPopupTheme != null) {
        toolbarView.setPopupTheme(builder.toolbarPopupTheme);
      }
      if (builder.toolbarBackgroundColor != null) {
        toolbarView.setBackgroundColor(builder.toolbarBackgroundColor);
      }

      activity.setSupportActionBar(toolbarView);
      final ActionBar ab = activity.getSupportActionBar();

      if (ab != null) {
        if (builder.toolbarBack) {
          if (builder.toolbarBackIcon != null) {
            ab.setHomeAsUpIndicator(builder.toolbarBackIcon);
          }
          ab.setDisplayHomeAsUpEnabled(true);
          if(activity instanceof UnderActivityBind){
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