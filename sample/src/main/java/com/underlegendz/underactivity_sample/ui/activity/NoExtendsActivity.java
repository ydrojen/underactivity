package com.underlegendz.underactivity_sample.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.underlegendz.underactivity.ActivityBuilder;
import com.underlegendz.underactivity.ActivityRender;
import com.underlegendz.underactivity.UnderActivityBind;
import com.underlegendz.underactivity_sample.R;

public class NoExtendsActivity extends AppCompatActivity implements UnderActivityBind {
  private DrawerLayout mDrawerLayout;
  private boolean mGoBackOnHome;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityBuilder builder = new ActivityBuilder();
    builder.enableToolbar(true)
        .setToolbarTitleColor(Color.WHITE)
        .setDrawerCustomLayout(R.layout.custom_drawer)
        .setToolbarDrawerIcon(R.drawable.ic_menu)
        .setContentLayout(R.layout.content);
    ActivityRender.render(this, builder);
    setTitle("No extends Activity");
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

  @Override
  public void bindToolbar(Toolbar toolbar) {
    // Dont use Toolbar in this activity
  }

  @Override
  public void bindDrawerLayout(DrawerLayout drawerLayout) {
    mDrawerLayout = drawerLayout;
  }

  @Override
  public void bindTabLayout(TabLayout tabLayout) {
    // Dont use TabLayout in this activity

  }

  @Override
  public void goBackOnHome(boolean back) {
    mGoBackOnHome = back;
  }
}
