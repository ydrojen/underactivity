package com.underlegendz.underactivity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.underlegendz.library.R;

class ConfigureContent {

  static void configureContent(UnderActivity.Builder builder, UnderActivity underActivity) {
    View customLayout;
    if (builder.contentLayoutResource != null) {
      customLayout = underActivity.getLayoutInflater()
          .inflate(builder.contentLayoutResource, underActivity.getContent(), false);
    } else {
      customLayout = builder.contentLayout;
    }
    if (customLayout != null) {
      //mCoordinatorLayout.removeView(mMainContent);
      underActivity.getContent().addView(customLayout);
      ViewGroup.LayoutParams layoutParams = customLayout.getLayoutParams();
      layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
      layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
    } else {
      FrameLayout content = new FrameLayout(underActivity);
      content.setId(R.id.main_content);
      underActivity.getContent().addView(content);
      ViewGroup.LayoutParams layoutParams = content.getLayoutParams();
      layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
      layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
      if (builder.enableCoordinatorAppBarLayout) {
        ((CoordinatorLayout.LayoutParams) layoutParams).setBehavior(new AppBarLayout.ScrollingViewBehavior());
      }
    }
  }
}