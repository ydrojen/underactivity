package com.underlegendz.underactivity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ConfigureToolbar {

  /**
   * Configure Activity's Toolbar.
   *
   * @param builder Activity build configuration.
   */
  static void configureToolbar(UnderActivity.Builder builder, UnderActivity underActivity) {
    if (builder.enableCoordinatorAppBarLayout) {
      underActivity.setContent(new CoordinatorLayout(underActivity));
    } else {
      underActivity.setContent(new LinearLayout(underActivity));
      ((LinearLayout) underActivity.getContent()).setOrientation(LinearLayout.VERTICAL);
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
          if (appBarLayout.getChildAt(i) instanceof Toolbar) {
            underActivity.setToolbar((Toolbar) appBarLayout.getChildAt(i));
            break;
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
          appBarLayoutParams.setScrollFlags(builder.appBarLayoutScrollFlags);
          underActivity.getToolbar().setLayoutParams(appBarLayoutParams);
          appBarLayout.addView(underActivity.getToolbar());

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