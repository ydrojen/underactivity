package com.underlegendz.underactivity;

import android.app.Activity;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.underlegendz.library.R;

class ConfigureContent {

  static void configureContent(ActivityBuilder builder, Activity activity, ViewGroup content) {
    View customLayout;
    if (builder.contentLayoutResource != null) {
      customLayout = activity.getLayoutInflater()
          .inflate(builder.contentLayoutResource, content, false);
    } else {
      customLayout = builder.contentLayout;
    }
    if (customLayout != null) {
      content.addView(customLayout);
      ViewGroup.LayoutParams layoutParams = customLayout.getLayoutParams();
      layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
      layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
    } else {
      FrameLayout container = new FrameLayout(activity);
      container.setId(R.id.main_content);
      content.addView(container);
      ViewGroup.LayoutParams layoutParams = container.getLayoutParams();
      layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
      layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
      if (builder.enableCoordinatorAppBarLayout) {
        ((CoordinatorLayout.LayoutParams) layoutParams).setBehavior(new AppBarLayout.ScrollingViewBehavior());
      }
    }
  }
}