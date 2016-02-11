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
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.underlegendz.underactivity.UnderActivity;

public class NavigationViewActivity extends UnderActivity
    implements NavigationView.OnNavigationItemSelectedListener {
  @Override protected Builder configureActivityBuilder(Builder builder) {
    return builder.setContentLayoutResource(R.layout.custom_layout)
        .setDrawerMenuResource(R.menu.drawer_menu)
        .setDrawerHeaderResource(R.layout.header_navigation_drawer)
        .setDrawerOnNavigationItemSelectedListener(this);
  }

  @Override public void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    TextView text = (TextView) findViewById(R.id.text);
    TextView code = (TextView) findViewById(R.id.code);

    text.setText(R.string.drawer_navigationview_text);
    code.setText(R.string.drawer_navigationview_code);

    findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent i = new Intent(v.getContext(), NavigationDrawerActivity.class);
        v.getContext().startActivity(i);
      }
    });
  }

  @Override public boolean onNavigationItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case R.id.drawer_next:
        Intent i = new Intent(this, NavigationDrawerActivity.class);
        startActivity(i);
        return true;
      case R.id.drawer_share:
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_url));
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
        return true;
    }
    return false;
  }
}
