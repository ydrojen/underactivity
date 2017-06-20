package com.underlegendz.underactivity;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/*
  Copyright (C) 2017 Jose Fuentes

  Licensed under the Apache License, Version 2.0 (the "License"),
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
public class ActivityRender {
  public static void render(AppCompatActivity activity, ActivityBuilder builder) {
    if (builder.enableFullScreenMode) {
      activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
      activity.getWindow()
          .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    DrawerLayout drawerLayout = null;
    if (builder.enableDrawer) {
      drawerLayout = new DrawerLayout(activity);
      drawerLayout.setLayoutParams(getCoordinatorLayoutParams());
      if(activity instanceof UnderActivityBind){
        ((UnderActivityBind) activity).bindDrawerLayout(drawerLayout);
      }

      activity.setContentView(drawerLayout);
      ConfigureDrawer.configureDrawer(builder, activity, drawerLayout);
    }

    ViewGroup content = null;
    if (builder.enableCoordinatorAppBarLayout) {
      CoordinatorLayout coordinatorLayout = new CoordinatorLayout(activity);
      content = coordinatorLayout;
    } else {
      LinearLayoutCompat ll = new LinearLayoutCompat(activity);
      ll.setOrientation(LinearLayoutCompat.VERTICAL);
      content = ll;
    }

    if(drawerLayout != null){
      content.setLayoutParams(getDrawerLayoutParams());
      drawerLayout.addView(content, 0);
    }else{
      activity.setContentView(content);
    }

    if(builder.enableToolbar){
      ConfigureToolbar.configureToolbar(builder, activity, content);
    }

    ConfigureContent.configureContent(builder, activity, content);
  }

  @NonNull
  static DrawerLayout.LayoutParams getDrawerLayoutParams() {
    return new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT);
  }

  @NonNull
  static CoordinatorLayout.LayoutParams getCoordinatorLayoutParams() {
    return new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT);
  }
}
