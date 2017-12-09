/*
 * Created by Jose Fuentes on 9/12/17 21:06
 * Copyright (C) 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License"),
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.underlegendz.underactivity_sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.underlegendz.underactivity.ActivityBuilder;
import com.underlegendz.underactivity_sample.R;

public class NavigationViewActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  @Override
  protected ActivityBuilder configureActivityBuilder(ActivityBuilder builder) {
    return super.configureActivityBuilder(builder)
        .setDrawerNavigationViewHeader(R.layout.navigation_header)
        .setDrawerNavigationViewMenuResource(R.menu.drawer_menu)
        .setDrawerOnNavigationItemSelectedListener(this)
        .setToolbarDrawerIcon(R.drawable.ic_menu)
        .setContentLayout(R.layout.content);
  }

  @Override
  public void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    getToolbar().setTitle("Navigation View");
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()){
      case R.id.drawer_option:
        Toast.makeText(this, R.string.sample_action, Toast.LENGTH_SHORT).show();
        break;
      case R.id.drawer_share:
        Intent intent=new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_link));
        startActivity(Intent.createChooser(intent, getString(R.string.share)));
        break;
    }
    getDrawerLayout().closeDrawers();
    return false;
  }
}
