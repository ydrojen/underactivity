package com.underlegendz.underactivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.underlegendz.library.R;

public abstract class UnderActivity extends AppCompatActivity {

  private final String DEFAULT_TAG = "main_content";

  // Layout views
  private DrawerLayout mDrawerLayout;

  // Components Views
  private NavigationView mNavigationView;
  private ViewGroup mContent;
  private Toolbar mToolbar;

  private boolean back;

  /**
   * Bind components.
   */
  private void bindLayoutViews() {
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
  }

  /**
   * Create Activity.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Builder builder = new Builder();
    builder = configureActivityBuilder(builder);

    setContentView(R.layout.underactivity);
    bindLayoutViews();

    configureDrawer(builder);
    configureToolbar(builder);
    configureContent(builder);
  }

  /**
   * Configure Activity's Drawer.
   *
   * @param builder Activity build configuration.
   */
  private void configureDrawer(Builder builder) {
    if (builder.enableDrawer) {
      // Build Drawer
      View drawerCustomLayout;
      if (builder.drawerCustomLayoutResource != null) {
        drawerCustomLayout =
            getLayoutInflater().inflate(builder.drawerCustomLayoutResource, mDrawerLayout, false);
      } else {
        drawerCustomLayout = builder.drawerCustomLayout;
      }

      DrawerLayout.LayoutParams lp = getDrawerLayoutParams();
      lp.gravity = GravityCompat.START;
      if (drawerCustomLayout != null) { // CustomView
        drawerCustomLayout.setLayoutParams(lp);
        mDrawerLayout.addView(drawerCustomLayout);
      } else { // NavigationView
        mNavigationView = new NavigationView(this);
        mNavigationView.setLayoutParams(lp);
        mDrawerLayout.addView(mNavigationView);

        // Header
        if (builder.drawerNavigationViewHeader != null) {
          mNavigationView.addHeaderView(builder.drawerNavigationViewHeader);
        } else if (builder.drawerNavigationViewHeaderResource != null) {
          mNavigationView.inflateHeaderView(builder.drawerNavigationViewHeaderResource);
        }

        // Menu
        if (builder.drawerNavigationViewMenuResource != null) {
          mNavigationView.inflateMenu(builder.drawerNavigationViewMenuResource);
        }

        if (builder.drawerNavigationViewBackgroundColor != null) {
          mNavigationView.setBackgroundColor(builder.drawerNavigationViewBackgroundColor);
        }

        if (builder.drawerOnNavigationItemSelectedListener != null) {
          mNavigationView.setNavigationItemSelectedListener(
              builder.drawerOnNavigationItemSelectedListener);
        }
      }
    } else {
      // No drawer
      mDrawerLayout.removeView(mNavigationView);
    }
  }

  @NonNull
  private DrawerLayout.LayoutParams getDrawerLayoutParams() {
    return new DrawerLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
  }

  /**
   * Configure Activity's Toolbar.
   *
   * @param builder Activity build configuration.
   */
  private void configureToolbar(Builder builder) {
    if(builder.enableAppBarLayout){
      mContent = new CoordinatorLayout(this);
    }else{
      mContent = new LinearLayout(this);
      ((LinearLayout) mContent).setOrientation(LinearLayout.VERTICAL);
    }
    mContent.setLayoutParams(getDrawerLayoutParams());
    mDrawerLayout.addView(mContent, 0);

    if (builder.enableToolbar) {
      Toolbar customToolbar;
      if (builder.toolbarResource != null) {
        customToolbar =
            (Toolbar) getLayoutInflater().inflate(builder.toolbarResource, mContent,
                false);
      } else {
        customToolbar = builder.toolbar;
      }
      if (customToolbar != null) {
        mToolbar = customToolbar;
      } else {
        mToolbar = new Toolbar(this);
        CoordinatorLayout.LayoutParams lp =
            new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mToolbar.setLayoutParams(lp);
      }

      if(builder.enableAppBarLayout){
        AppBarLayout appBarLayout = new AppBarLayout(this);
        CoordinatorLayout.LayoutParams lp =
            new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        appBarLayout.setLayoutParams(lp);
        appBarLayout.addView(mToolbar);
        mContent.addView(appBarLayout, 0);
      }else {
        mContent.addView(mToolbar);
      }

      if (builder.toolbarPopupTheme != null) {
        mToolbar.setPopupTheme(builder.toolbarPopupTheme);
      }
      if (builder.toolbarBackgroundColor != null) {
        mToolbar.setBackgroundColor(builder.toolbarBackgroundColor);
      }
    }
  }

  private void configureContent(Builder builder) {
    View customLayout;
    if (builder.contentLayoutResource != null) {
      customLayout =
          getLayoutInflater().inflate(builder.contentLayoutResource, mContent, false);
    } else {
      customLayout = builder.contentLayout;
    }
    if (customLayout != null) {
      //mCoordinatorLayout.removeView(mMainContent);
      mContent.addView(customLayout);
      ViewGroup.LayoutParams layoutParams = customLayout.getLayoutParams();
      layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
      layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
    }else{
      FrameLayout content = new FrameLayout(this);
      content.setId(R.id.main_content);
      mContent.addView(content);
      ViewGroup.LayoutParams layoutParams = content.getLayoutParams();
      layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
      layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
    }

    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
      final ActionBar ab = getSupportActionBar();

      if (ab != null) {
        if (builder.toolbarDrawerIcon != null) {
          ab.setHomeAsUpIndicator(builder.toolbarDrawerIcon);
          ab.setDisplayHomeAsUpEnabled(true);
        }
        if (builder.toolbarBack) {
          ab.setDisplayHomeAsUpEnabled(true);
          back = builder.toolbarBack;
        }
      }
    }
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
        if (back) {
          onBackPressed();
          return true;
        }
        if (mDrawerLayout != null) {
          mDrawerLayout.openDrawer(GravityCompat.START);
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

  protected abstract Builder configureActivityBuilder(Builder builder);

  protected static class Builder {
    private boolean enableToolbar = false;
    private boolean enableDrawer = false;
    private boolean enableAppBarLayout = false;
    private View contentLayout = null;
    private Integer contentLayoutResource = null;
    private View drawerCustomLayout = null;
    private Integer drawerCustomLayoutResource = null;
    private View drawerNavigationViewHeader = null;
    private Integer drawerNavigationViewHeaderResource = null;
    private Integer drawerNavigationViewMenuResource = null;
    private Integer drawerNavigationViewBackgroundColor = null;
    private NavigationView.OnNavigationItemSelectedListener drawerOnNavigationItemSelectedListener;
    private Toolbar toolbar = null;
    private Integer toolbarResource = null;
    private Integer toolbarPopupTheme = null;
    private Integer toolbarBackgroundColor = null;
    private Integer toolbarDrawerIcon = null;
    private boolean toolbarBack = false;

    /**
     * Enable/Disable Toolbar.
     *
     * @param enableToolbar It indicates whether the activity has mToolbar.
     * @return builder.
     */
    public Builder enableToolbar(boolean enableToolbar) {
      this.enableToolbar = enableToolbar;
      return this;
    }

    /**
     * Enable/Disable Drawer.
     *
     * @param enableDrawer It indicates whether the activity has navigation drawer.
     * @return builder.
     */
    public Builder enableDrawer(boolean enableDrawer) {
      this.enableDrawer = enableDrawer;
      return this;
    }

    /**
     * Enable/Disable AppBarLayout for Toolbar.
     *
     * @param enableAppBarLayout It indicates whether the toolbar is content in an AppBarLayout.
     * @return builder.
     */
    public Builder enableAppBarLayout(boolean enableAppBarLayout) {
      this.enableAppBarLayout = enableAppBarLayout;
      return this;
    }

    /**
     * Set custom layout view for activity. This method overrides any custom layout resource.
     *
     * @param contentLayout Activity's layout view.
     * @return builder.
     */
    public Builder setContentLayout(View contentLayout) {
      this.contentLayout = contentLayout;
      this.contentLayoutResource = null;
      return this;
    }

    /**
     * Set custom layout resource for activity. This method overrides any custom layout view.
     *
     * @param contentLayoutResource Activity's layout resource.
     * @return builder.
     */
    public Builder setContentLayoutResource(Integer contentLayoutResource) {
      this.contentLayoutResource = contentLayoutResource;
      this.contentLayout = null;
      return this;
    }

    /**
     * Set custom layout view for navigation drawer. This method overrides any custom layout
     * resource for navigation drawer and all configuration for NavigationView and enable drawer.
     *
     * @param drawerCustomLayout Navigation drawer's layout view.
     * @return builder.
     */
    public Builder setDrawerCustomLayout(View drawerCustomLayout) {
      this.drawerCustomLayout = drawerCustomLayout;
      drawerCustomLayoutResource = null;
      drawerNavigationViewHeader = null;
      drawerNavigationViewHeaderResource = null;
      drawerNavigationViewMenuResource = null;
      drawerOnNavigationItemSelectedListener = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set custom layout resource for navigation drawer. This method overrides any custom layout
     * view for navigation drawer and all configuration for NavigationView and enable drawer.
     *
     * @param drawerCustomLayoutResource Navigation drawer's layout resource.
     * @return builder.
     */
    public Builder setDrawerCustomLayoutResource(Integer drawerCustomLayoutResource) {
      this.drawerCustomLayoutResource = drawerCustomLayoutResource;
      drawerCustomLayout = null;
      drawerNavigationViewHeader = null;
      drawerNavigationViewHeaderResource = null;
      drawerNavigationViewMenuResource = null;
      drawerOnNavigationItemSelectedListener = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set header view for NavigationView drawer. This method overrides header resource and any
     * custom drawer's layout configuration and enable drawer.
     *
     * @param drawerNavigationViewHeader NavigationView Header's layout view.
     * @return builder.
     */
    public Builder setDrawerNavigationViewHeader(View drawerNavigationViewHeader) {
      this.drawerNavigationViewHeader = drawerNavigationViewHeader;
      drawerNavigationViewHeaderResource = null;
      drawerCustomLayout = null;
      drawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set header view for NavigationView drawer. This method overrides header view and any custom
     * drawer's layout configuration and enable drawer.
     *
     * @param drawerNavigationViewHeaderResource NavigationView Header's layout resource.
     * @return builder.
     */
    public Builder setDrawerNavigationViewHeaderResource(Integer drawerNavigationViewHeaderResource) {
      this.drawerNavigationViewHeaderResource = drawerNavigationViewHeaderResource;
      drawerNavigationViewHeader = null;
      drawerCustomLayout = null;
      drawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set NavigationView menu resource. This method overrides any custom drawer's layout
     * configuration and enable drawer.
     *
     * @param drawerNavigationViewMenuResource NavigationView menu xml.
     * @return builder.
     */
    public Builder setDrawerNavigationViewMenuResource(Integer drawerNavigationViewMenuResource) {
      this.drawerNavigationViewMenuResource = drawerNavigationViewMenuResource;
      drawerCustomLayout = null;
      drawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set NavigationView drawer background color. This method overrides any custom drawer's layout
     * configuration and enable drawer.
     *
     * @param drawerNavigationViewBackgroundColor Drawer's background color.
     * @return builder.
     */
    public Builder setDrawerNavigationViewBackgroundColor(Integer drawerNavigationViewBackgroundColor) {
      this.drawerNavigationViewBackgroundColor = drawerNavigationViewBackgroundColor;
      drawerCustomLayout = null;
      drawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set OnNavigationItemSelectedListener for drawer's NavigationView. This method overrides any
     * custom drawer's layout configuration and enable drawer.
     *
     * @param drawerOnNavigationItemSelectedListener ItemSelectedListener.
     * @return builder.
     */
    public Builder setDrawerOnNavigationItemSelectedListener(
        NavigationView.OnNavigationItemSelectedListener drawerOnNavigationItemSelectedListener) {
      this.drawerOnNavigationItemSelectedListener = drawerOnNavigationItemSelectedListener;
      drawerCustomLayout = null;
      drawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set custom Toolbar view. This method overrides any custom mToolbar layout resource and
     * enable mToolbar.
     *
     * @param toolbar Toolbar's view.
     * @return builder.
     */
    public Builder setToolbar(Toolbar toolbar) {
      this.toolbar = toolbar;
      toolbarResource = null;
      enableToolbar = true;
      return this;
    }

    /**
     * Set custom Toolbar layout resource. This method overrides any custom mToolbar view and
     * enable mToolbar.
     *
     * @param toolbarResource Toolbar's layout resource.
     * @return builder.
     */
    public Builder setToolbarResource(Integer toolbarResource) {
      this.toolbarResource = toolbarResource;
      toolbar = null;
      enableToolbar = true;
      return this;
    }

    /**
     * Set Toolbar's popup menu style. This method enable mToolbar.
     *
     * @param toolbarPopupTheme Style resource for mToolbar popup menu.
     * @return builder.
     */
    public Builder setToolbarPopupTheme(Integer toolbarPopupTheme) {
      this.toolbarPopupTheme = toolbarPopupTheme;
      enableToolbar = true;
      return this;
    }

    /**
     * Set Toolbar's background color. This method enable mToolbar.
     *
     * @param toolbarBackgroundColor Color for mToolbar background.
     * @return builder.
     */
    public Builder setToolbarBackgroundColor(Integer toolbarBackgroundColor) {
      this.toolbarBackgroundColor = toolbarBackgroundColor;
      enableToolbar = true;
      return this;
    }

    /**
     * Set Toolbar's navigation drawer icon resource. This method enable mToolbar and navigation
     * drawer.
     *
     * @param toolbarDrawerIcon Navigation drawer icon resource.
     * @return builder.
     */
    public Builder setToolbarDrawerIcon(Integer toolbarDrawerIcon) {
      this.toolbarDrawerIcon = toolbarDrawerIcon;
      enableDrawer = true;
      enableToolbar = true;
      return this;
    }

    /**
     * Enable/disable Toolbar's back icon and back funcionality. This method enable mToolbar.
     *
     * @param toolbarBack enable/disable.
     * @return builder.
     */
    public Builder setToolbarBack(boolean toolbarBack) {
      this.toolbarBack = toolbarBack;
      enableToolbar = true;
      return this;
    }
  }
}
