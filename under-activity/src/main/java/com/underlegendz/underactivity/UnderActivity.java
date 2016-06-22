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
      // Build Start Drawer
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
      } else if(builder.drawerNavigationViewMenuResource != null){ // NavigationView
        NavigationView navigationView = new NavigationView(this);
        navigationView.setLayoutParams(lp);
        mDrawerLayout.addView(navigationView);

        // Header
        if (builder.drawerNavigationViewHeader != null) {
          navigationView.addHeaderView(builder.drawerNavigationViewHeader);
        } else if (builder.drawerNavigationViewHeaderResource != null) {
          navigationView.inflateHeaderView(builder.drawerNavigationViewHeaderResource);
        }

        // Menu
        if (builder.drawerNavigationViewMenuResource != null) {
          navigationView.inflateMenu(builder.drawerNavigationViewMenuResource);
        }

        if (builder.drawerNavigationViewBackgroundColor != null) {
          navigationView.setBackgroundColor(builder.drawerNavigationViewBackgroundColor);
        }

        if (builder.drawerOnNavigationItemSelectedListener != null) {
          navigationView.setNavigationItemSelectedListener(
              builder.drawerOnNavigationItemSelectedListener);
        }
      }

      // Build End Drawer
      View endDrawerCustomLayout;
      if (builder.endDrawerCustomLayoutResource != null) {
        endDrawerCustomLayout =
            getLayoutInflater().inflate(builder.endDrawerCustomLayoutResource, mDrawerLayout, false);
      } else {
        endDrawerCustomLayout = builder.endDrawerCustomLayout;
      }

      DrawerLayout.LayoutParams lpEnd = getDrawerLayoutParams();
      lpEnd.gravity = GravityCompat.END;
      if (endDrawerCustomLayout != null) { // CustomView
        endDrawerCustomLayout.setLayoutParams(lpEnd);
        mDrawerLayout.addView(endDrawerCustomLayout);
      } else if(builder.endDrawerNavigationViewMenuResource != null){ // NavigationView
        NavigationView endNavigationView = new NavigationView(this);
        endNavigationView.setLayoutParams(lpEnd);
        mDrawerLayout.addView(endNavigationView);

        // Header
        if (builder.endDrawerNavigationViewHeader != null) {
          endNavigationView.addHeaderView(builder.endDrawerNavigationViewHeader);
        } else if (builder.endDrawerNavigationViewHeaderResource != null) {
          endNavigationView.inflateHeaderView(builder.endDrawerNavigationViewHeaderResource);
        }

        // Menu
        if (builder.endDrawerNavigationViewMenuResource != null) {
          endNavigationView.inflateMenu(builder.endDrawerNavigationViewMenuResource);
        }

        if (builder.endDrawerNavigationViewBackgroundColor != null) {
          endNavigationView.setBackgroundColor(builder.endDrawerNavigationViewBackgroundColor);
        }

        if (builder.endDrawerOnNavigationItemSelectedListener != null) {
          endNavigationView.setNavigationItemSelectedListener(
              builder.endDrawerOnNavigationItemSelectedListener);
        }
      }
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
    if(builder.enableCoordinatorAppBarLayout){
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
      }

      if(builder.enableCoordinatorAppBarLayout){
        AppBarLayout appBarLayout = new AppBarLayout(this);
        CoordinatorLayout.LayoutParams coordinatorLayoutParams =
            new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        appBarLayout.setLayoutParams(coordinatorLayoutParams);

        AppBarLayout.LayoutParams appBarLayoutParams  = new AppBarLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        appBarLayoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        mToolbar.setLayoutParams(appBarLayoutParams);
        appBarLayout.addView(mToolbar);

        coordinatorLayoutParams.setBehavior(new AppBarLayout.Behavior());
        appBarLayout.setLayoutParams(coordinatorLayoutParams);
        mContent.addView(appBarLayout, 0);
      }else {
        LinearLayout.LayoutParams linearLayoutParams =
            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mToolbar.setLayoutParams(linearLayoutParams);
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
      if(builder.enableCoordinatorAppBarLayout){
        ((CoordinatorLayout.LayoutParams)layoutParams).setBehavior(new AppBarLayout.ScrollingViewBehavior());
      }
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
    // Components enabled
    private boolean enableToolbar = false;
    private boolean enableDrawer = false;
    private boolean enableCoordinatorAppBarLayout = false;
    // Main Content
    private View contentLayout = null;
    private Integer contentLayoutResource = null;
    // Start Drawer
    private View drawerCustomLayout = null;
    private Integer drawerCustomLayoutResource = null;
    private View drawerNavigationViewHeader = null;
    private Integer drawerNavigationViewHeaderResource = null;
    private Integer drawerNavigationViewMenuResource = null;
    private Integer drawerNavigationViewBackgroundColor = null;
    private NavigationView.OnNavigationItemSelectedListener drawerOnNavigationItemSelectedListener;
    // End Drawer
    private View endDrawerCustomLayout = null;
    private Integer endDrawerCustomLayoutResource = null;
    private View endDrawerNavigationViewHeader = null;
    private Integer endDrawerNavigationViewHeaderResource = null;
    private Integer endDrawerNavigationViewMenuResource = null;
    private Integer endDrawerNavigationViewBackgroundColor = null;
    private NavigationView.OnNavigationItemSelectedListener endDrawerOnNavigationItemSelectedListener;
    // Toolbar
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
     * Enable/Disable CoordinatorLayout for Content and AppBarLayout for Toolbar.
     *
     * @param enableCoordinatorAppBarLayout It indicates whether create CoordinatorLayout for the
     * main content and include the Toolbar in an AppBarLayout.
     * @return builder.
     */
    public Builder enableCoordinatorAppBarLayout(boolean enableCoordinatorAppBarLayout) {
      this.enableCoordinatorAppBarLayout = enableCoordinatorAppBarLayout;
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
     * Set custom layout view for navigation drawer with gravity 'start'. This method overrides any
     * custom layout resource for navigation drawer with gravity 'start' and all configuration for
     * NavigationView with gravity 'start' and enable drawer.
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
     * Set custom layout resource for navigation drawer with gravity 'start'. This method overrides
     * any custom layout view for navigation drawer with gravity 'start' and all configuration for
     * NavigationView with gravity 'start' and enable drawer.
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
     * Set header view for NavigationView drawer with gravity 'start'. This method overrides header
     * resource and any custom drawer's layout configuration for gravity 'start' and enable drawer.
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
     * Set header view for NavigationView drawer with gravity 'start'. This method overrides header
     * view and any custom drawer's layout configuration for gravity 'start' and enable drawer.
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
     * Set NavigationView with gravity 'start' menu resource. This method overrides any custom
     * drawer's layout configuration for gravity 'start' and enable drawer.
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
     * Set NavigationView drawer with gravity 'start' background color. This method overrides any
     * custom drawer's layout configuration for gravity 'start' and enable drawer.
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
     * Set OnNavigationItemSelectedListener for drawer's NavigationView with gravity 'start'. This
     * method overrides any custom drawer's layout configuration for gravity 'start and enable
     * drawer.
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
     * Set custom layout view for navigation drawer with gravity 'end'. This method overrides any
     * custom layout resource for navigation drawer with gravity 'end' and all configuration for
     * NavigationView with gravity 'end' and enable drawer.
     *
     * @param endDrawerCustomLayout Navigation drawer's layout view.
     * @return builder.
     */
    public Builder setEndDrawerCustomLayout(View endDrawerCustomLayout) {
      this.endDrawerCustomLayout = drawerCustomLayout;
      endDrawerCustomLayoutResource = null;
      endDrawerNavigationViewHeader = null;
      endDrawerNavigationViewHeaderResource = null;
      endDrawerNavigationViewMenuResource = null;
      endDrawerOnNavigationItemSelectedListener = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set custom layout resource for navigation drawer with gravity 'end'. This method overrides
     * any custom layout view for navigation drawer with gravity 'end' and all configuration for
     * NavigationView with gravity 'end' and enable drawer.
     *
     * @param endDrawerCustomLayoutResource Navigation drawer's layout resource.
     * @return builder.
     */
    public Builder setEndDrawerCustomLayoutResource(Integer endDrawerCustomLayoutResource) {
      this.endDrawerCustomLayoutResource = endDrawerCustomLayoutResource;
      endDrawerCustomLayout = null;
      endDrawerNavigationViewHeader = null;
      endDrawerNavigationViewHeaderResource = null;
      endDrawerNavigationViewMenuResource = null;
      endDrawerOnNavigationItemSelectedListener = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set header view for NavigationView drawer with gravity 'end'. This method overrides header
     * resource and any custom drawer's layout configuration for gravity 'end' and enable drawer.
     *
     * @param endDrawerNavigationViewHeader NavigationView Header's layout view.
     * @return builder.
     */
    public Builder setEndDrawerNavigationViewHeader(View endDrawerNavigationViewHeader) {
      this.endDrawerNavigationViewHeader = endDrawerNavigationViewHeader;
      endDrawerNavigationViewHeaderResource = null;
      endDrawerCustomLayout = null;
      endDrawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set header view for NavigationView drawer with gravity 'end'. This method overrides header
     * view and any custom drawer's layout configuration for gravity 'end' and enable drawer.
     *
     * @param endDrawerNavigationViewHeaderResource NavigationView Header's layout resource.
     * @return builder.
     */
    public Builder setEndDrawerNavigationViewHeaderResource(Integer endDrawerNavigationViewHeaderResource) {
      this.endDrawerNavigationViewHeaderResource = endDrawerNavigationViewHeaderResource;
      endDrawerNavigationViewHeader = null;
      endDrawerCustomLayout = null;
      endDrawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set NavigationView with gravity 'end' menu resource. This method overrides any custom
     * drawer's layout configuration for gravity 'end' and enable drawer.
     *
     * @param endDrawerNavigationViewMenuResource NavigationView menu xml.
     * @return builder.
     */
    public Builder setEndDrawerNavigationViewMenuResource(Integer endDrawerNavigationViewMenuResource) {
      this.endDrawerNavigationViewMenuResource = endDrawerNavigationViewMenuResource;
      endDrawerCustomLayout = null;
      endDrawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set NavigationView drawer with gravity 'end' background color. This method overrides any
     * custom drawer's layout configuration for gravity 'end' and enable drawer.
     *
     * @param endDrawerNavigationViewBackgroundColor Drawer's background color.
     * @return builder.
     */
    public Builder setEndDrawerNavigationViewBackgroundColor(Integer endDrawerNavigationViewBackgroundColor) {
      this.endDrawerNavigationViewBackgroundColor = endDrawerNavigationViewBackgroundColor;
      endDrawerCustomLayout = null;
      endDrawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set OnNavigationItemSelectedListener for drawer's NavigationView with gravity 'end'. This
     * method overrides any custom drawer's layout configuration for gravity 'end and enable
     * drawer.
     *
     * @param endDrawerOnNavigationItemSelectedListener ItemSelectedListener.
     * @return builder.
     */
    public Builder setEndDrawerOnNavigationItemSelectedListener(
        NavigationView.OnNavigationItemSelectedListener endDrawerOnNavigationItemSelectedListener) {
      this.endDrawerOnNavigationItemSelectedListener = endDrawerOnNavigationItemSelectedListener;
      endDrawerCustomLayout = null;
      endDrawerCustomLayoutResource = null;
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
