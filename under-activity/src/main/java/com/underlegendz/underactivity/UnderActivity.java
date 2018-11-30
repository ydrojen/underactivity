/*
 * Created by Jose Fuentes on 9/12/17 17:31
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

package com.underlegendz.underactivity;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import com.underlegendz.library.R;
import java.util.UUID;

public abstract class UnderActivity extends AppCompatActivity implements UnderActivityBind{

  private final String DEFAULT_TAG = UUID.randomUUID().toString();

  // Layout views
  private DrawerLayout mDrawerLayout;

  // Components Views
  private Toolbar mToolbar;
  private TabLayout mTabLayout;

  private boolean mGoBackOnHome;

  /**
   * Create Activity.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ActivityBuilder builder = new ActivityBuilder();
    builder = configureActivityBuilder(builder);
    ActivityRender.render(this, builder);
    super.onCreate(savedInstanceState);
  }



  /**
   * Finds fragment with default tag.
   *
   * @return Tag's fragment or null if not found.
   */
  protected Fragment getFragment() {
    return getFragment(DEFAULT_TAG);
  }

  /**
   * Finds fragment with the specified tag.
   *
   * @param tag Tag's fragment to find.
   * @return Tag's fragment or null if not found.
   */
  protected Fragment getFragment(String tag) {
    if (getSupportFragmentManager() != null) {
      return getSupportFragmentManager().findFragmentByTag(tag);
    }
    return null;
  }

  protected void setFragment(Fragment fragment) {
    setFragment(fragment, DEFAULT_TAG, false);
  }

  protected void setFragment(Fragment fragment, boolean addToBackStack) {
    setFragment(fragment, DEFAULT_TAG, addToBackStack);
  }

  protected void setFragment(Fragment fragment, String tag) {
    setFragment(fragment, tag, false);
  }

  protected void setFragment(Fragment fragment, String tag, boolean addToBackStack) {
    if (fragment != null && getSupportFragmentManager() != null) {
      FragmentTransaction replaceTransaction = getSupportFragmentManager().beginTransaction();
      replaceTransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
      replaceTransaction.replace(R.id.main_content, fragment, tag);
      if (addToBackStack) {
        replaceTransaction.addToBackStack(tag);
      }
      replaceTransaction.commit();
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        if (mGoBackOnHome) {
          onBackPressed();
          return true;
        }
        if (mDrawerLayout != null) {
          try{
            mDrawerLayout.openDrawer(GravityCompat.START);
          }catch (IllegalArgumentException exceptionStart){
            try{
              mDrawerLayout.openDrawer(GravityCompat.END);
            }catch (IllegalArgumentException exceptionEnd){
              // do nothing
            }
          }
          return true;
        }
    }
    return super.onOptionsItemSelected(item);
  }

  public Toolbar getToolbar() {
    return mToolbar;
  }

  public DrawerLayout getDrawerLayout() {
    return mDrawerLayout;
  }

  public TabLayout getTabLayout() {
    return mTabLayout;
  }

  @Override
  public void bindDrawerLayout(DrawerLayout drawerLayout){
    mDrawerLayout = drawerLayout;
  }

  @Override
  public void bindTabLayout(TabLayout tabLayout) {
    mTabLayout = tabLayout;
  }

  @Override
  public void bindToolbar(Toolbar toolbar) {
    mToolbar = toolbar;
  }

  boolean isGoBackOnHome() {
    return mGoBackOnHome;
  }

  @Override
  public void goBackOnHome(boolean back) {
    this.mGoBackOnHome = back;
  }

  protected abstract ActivityBuilder configureActivityBuilder(ActivityBuilder builder);


}
