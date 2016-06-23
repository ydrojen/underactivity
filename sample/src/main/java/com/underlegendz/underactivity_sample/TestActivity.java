package com.underlegendz.underactivity_sample;
/*
 * Copyright (C) 2016 Jose Fuentes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.TextView;
import com.underlegendz.underactivity.UnderActivity;

public class TestActivity extends UnderActivity {
  @Override protected Builder configureActivityBuilder(Builder builder) {
    return builder
        //.setContentLayoutResource(R.layout.custom_layout)
        .enableCoordinatorAppBarLayout(true)
        .setAppBarLayoutScrollFlags(Builder.SCROLL_FLAG_SCROLL|Builder.SCROLL_FLAG_SNAP|Builder.SCROLL_FLAG_ENTER_ALWAYS)
        .enableToolbarTabs(true)
        .setToolbarBackgroundColor(getResources().getColor(R.color.accentColor))
        .setDrawerCustomLayoutResource(R.layout.custom_drawer);
  }

  @Override public void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    setFragment(new RecyclerFragment());
    TabLayout tabLayout = getTabLayout();
    tabLayout.setTabMode(TabLayout.MODE_FIXED);
    tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
    tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
    tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));

    //TextView text = (TextView) findViewById(R.id.text);
    //TextView code = (TextView) findViewById(R.id.code);
    //
    //text.setText(R.string.drawer_customlayout_text);
    //code.setText(R.string.drawer_customlayout_code);
    //
    //findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
    //  @Override public void onClick(View v) {
    //    Intent i = new Intent(v.getContext(), ToolbarActivity.class);
    //    v.getContext().startActivity(i);
    //  }
    //});
    //findViewById(R.id.next_drawer).setOnClickListener(new View.OnClickListener() {
    //  @Override public void onClick(View v) {
    //    Intent i = new Intent(v.getContext(), ToolbarActivity.class);
    //    v.getContext().startActivity(i);
    //  }
    //});
    //findViewById(R.id.share_drawer).setOnClickListener(new View.OnClickListener() {
    //  @Override public void onClick(View v) {
    //    Intent shareIntent = new Intent(Intent.ACTION_SEND);
    //    shareIntent.setType("text/plain");
    //    shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_url));
    //    v.getContext().startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
    //  }
    //});
  }
}
