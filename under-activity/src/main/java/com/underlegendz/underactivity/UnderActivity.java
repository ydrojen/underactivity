package com.underlegendz.underactivity;

import android.os.Bundle;
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
import com.underlegendz.library.R;

public abstract class UnderActivity extends AppCompatActivity {

  private final String DEFAULT_TAG = "main_content";
  private NavigationView navigationView;
  private Toolbar toolbar;
  private View mainContent;
  private ViewGroup toolbarContent;
  private DrawerLayout drawerLayout;
  private boolean back;

  /**
   * Create Activity.
   */
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Builder builder = new Builder();
    builder = configureActivityBuilder(builder);
    if (builder.enableToolbar && builder.enableDrawer) {
      configureToolbarDrawerActivity(builder);
    } else if (builder.enableToolbar) {
      configureToolbarActivity(builder);
    } else if (builder.enableDrawer) {
      configureDrawerActivity(builder);
    } else {
      configureActivity(builder);
    }
  }

  /**
   * Create activity with Drawer & Toolbar.
   *
   * @param builder Activity build configuration.
   */
  private void configureToolbarDrawerActivity(Builder builder) {
    setContentView(R.layout.underactivity_toolbar_drawer);
    bindActivity();
    configureDrawer(builder);
    configureToolbar(builder);
  }

  /**
   * Create activity with Toolbar.
   *
   * @param builder Activity build configuration.
   */
  private void configureToolbarActivity(Builder builder) {
    setContentView(R.layout.underactivity_toolbar);
    bindActivity();
    configureToolbar(builder);
  }

  /**
   * Create activity with Drawer.
   *
   * @param builder Activity build configuration.
   */
  private void configureDrawerActivity(Builder builder) {
    setContentView(R.layout.underactivity_drawer);
    bindActivity();
    configureDrawer(builder);

    View customLayout;
    if (builder.contentLayoutResource != null) {
      customLayout =
          getLayoutInflater().inflate(builder.contentLayoutResource, drawerLayout, false);
    } else {
      customLayout = builder.contentLayout;
    }
    if (customLayout != null) {
      drawerLayout.removeView(mainContent);
      drawerLayout.addView(customLayout, 0);
    }
  }

  /**
   * Create simple activity.
   *
   * @param builder Activity build configuration.
   */
  private void configureActivity(Builder builder) {
    if (builder.contentLayout != null) {
      setContentView(builder.contentLayout);
    } else if (builder.contentLayoutResource != null) {
      setContentView(builder.contentLayoutResource);
    } else {
      setContentView(R.layout.underactivity);
    }
  }

  /**
   * Configure Activity's Drawer.
   *
   * @param builder Activity build configuration.
   */
  private void configureDrawer(Builder builder) {
    View drawerCustomLayout;
    if (builder.drawerCustomLayoutResource != null) {
      drawerCustomLayout =
          getLayoutInflater().inflate(builder.drawerCustomLayoutResource, drawerLayout, false);
    } else {
      drawerCustomLayout = builder.drawerCustomLayout;
    }

    if (drawerCustomLayout != null) { // CustomView
      drawerLayout.removeView(navigationView);
      drawerLayout.addView(drawerCustomLayout);
      DrawerLayout.LayoutParams lp =
          (DrawerLayout.LayoutParams) drawerCustomLayout.getLayoutParams();
      lp.gravity = GravityCompat.START;
    } else { // NavigationView
      // Header
      if (builder.drawerHeader != null) {
        navigationView.addHeaderView(builder.drawerHeader);
      } else if (builder.drawerHeaderResource != null) {
        navigationView.inflateHeaderView(builder.drawerHeaderResource);
      }

      // Menu
      if (builder.drawerMenuResource != null) {
        navigationView.inflateMenu(builder.drawerMenuResource);
      }

      if (builder.drawerBackgroundColor != null) {
        navigationView.setBackgroundColor(builder.drawerBackgroundColor);
      }

      if (builder.drawerOnNavigationItemSelectedListener != null) {
        navigationView.setNavigationItemSelectedListener(
            builder.drawerOnNavigationItemSelectedListener);
      }
    }
  }

  /**
   * Configure Activity's Toolbar.
   *
   * @param builder Activity build configuration.
   */
  private void configureToolbar(Builder builder) {
    Toolbar customToolbar;
    if (builder.toolbarResource != null) {
      customToolbar =
          (Toolbar) getLayoutInflater().inflate(builder.toolbarResource, toolbarContent, false);
    } else {
      customToolbar = builder.toolbar;
    }
    if (customToolbar != null) {
      toolbarContent.removeView(toolbar);
      toolbarContent.addView(customToolbar, 0);
      toolbar = customToolbar;
    } else {
      if (builder.toolbarPopupTheme != null) {
        toolbar.setPopupTheme(builder.toolbarPopupTheme);
      }
      if (builder.toolbarBackgroundColor != null) {
        toolbar.setBackgroundColor(builder.toolbarBackgroundColor);
      }
    }

    View customLayout;
    if (builder.contentLayoutResource != null) {
      customLayout =
          getLayoutInflater().inflate(builder.contentLayoutResource, drawerLayout, false);
    } else {
      customLayout = builder.contentLayout;
    }
    if (customLayout != null) {
      toolbarContent.removeView(mainContent);
      toolbarContent.addView(customLayout);
    }

    setSupportActionBar(toolbar);
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

  /**
   * Bind components.
   */
  private void bindActivity() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    navigationView = (NavigationView) findViewById(R.id.nav_view);
    mainContent = findViewById(R.id.main_content);
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_content);
    toolbarContent = (ViewGroup) findViewById(R.id.toolbar_content);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        if (back) {
          onBackPressed();
          return true;
        }
        if (drawerLayout != null) {
          drawerLayout.openDrawer(GravityCompat.START);
          return true;
        }
    }
    return super.onOptionsItemSelected(item);
  }

  public Toolbar getToolbar() {
    return toolbar;
  }

  public DrawerLayout getDrawerLayout() {
    return drawerLayout;
  }

  protected abstract Builder configureActivityBuilder(Builder builder);

  protected static class Builder {
    private boolean enableToolbar = false;
    private boolean enableDrawer = false;
    private View contentLayout = null;
    private Integer contentLayoutResource = null;
    private View drawerCustomLayout = null;
    private Integer drawerCustomLayoutResource = null;
    private View drawerHeader = null;
    private Integer drawerHeaderResource = null;
    private Integer drawerMenuResource = null;
    private Integer drawerBackgroundColor = null;
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
     * @param enableToolbar It indicates whether the activity has toolbar.
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
     * resource for
     * navigation drawer and all configuration for NavigationView and enable drawer.
     *
     * @param drawerCustomLayout Navigation drawer's layout view.
     * @return builder.
     */
    public Builder setDrawerCustomLayout(View drawerCustomLayout) {
      this.drawerCustomLayout = drawerCustomLayout;
      drawerCustomLayoutResource = null;
      drawerHeader = null;
      drawerHeaderResource = null;
      drawerMenuResource = null;
      drawerOnNavigationItemSelectedListener = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set custom layout resource for navigation drawer. This method overrides any custom layout
     * view for
     * navigation drawer and all configuration for NavigationView and enable drawer.
     *
     * @param drawerCustomLayoutResource Navigation drawer's layout resource.
     * @return builder.
     */
    public Builder setDrawerCustomLayoutResource(Integer drawerCustomLayoutResource) {
      this.drawerCustomLayoutResource = drawerCustomLayoutResource;
      drawerCustomLayout = null;
      drawerHeader = null;
      drawerHeaderResource = null;
      drawerMenuResource = null;
      drawerOnNavigationItemSelectedListener = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set header view for NavigationView drawer. This method overrides header resource and any
     * custom
     * drawer's layout configuration and enable drawer.
     *
     * @param drawerHeader NavigationView Header's layout view.
     * @return builder.
     */
    public Builder setDrawerHeader(View drawerHeader) {
      this.drawerHeader = drawerHeader;
      drawerHeaderResource = null;
      drawerCustomLayout = null;
      drawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set header view for NavigationView drawer. This method overrides header view and any custom
     * drawer's layout configuration and enable drawer.
     *
     * @param drawerHeaderResource NavigationView Header's layout resource.
     * @return builder.
     */
    public Builder setDrawerHeaderResource(Integer drawerHeaderResource) {
      this.drawerHeaderResource = drawerHeaderResource;
      drawerHeader = null;
      drawerCustomLayout = null;
      drawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set NavigationView menu resource. This method overrides any custom drawer's layout
     * configuration
     * and
     * enable drawer.
     *
     * @param drawerMenuResource NavigationView menu xml.
     * @return builder.
     */
    public Builder setDrawerMenuResource(Integer drawerMenuResource) {
      this.drawerMenuResource = drawerMenuResource;
      drawerCustomLayout = null;
      drawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set NavigationView drawer background color. This method overrides any custom drawer's layout
     * configuration and enable drawer.
     *
     * @param drawerBackgroundColor Drawer's background color.
     * @return builder.
     */
    public Builder setDrawerBackgroundColor(Integer drawerBackgroundColor) {
      this.drawerBackgroundColor = drawerBackgroundColor;
      drawerCustomLayout = null;
      drawerCustomLayoutResource = null;
      enableDrawer = true;
      return this;
    }

    /**
     * Set OnNavigationItemSelectedListener for drawer's NavigationView. This method overrides any
     * custom
     * drawer's layout configuration and enable drawer.
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
     * Set custom Toolbar view. This method overrides any custom toolbar layout resource and enable
     * toolbar.
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
     * Set custom Toolbar layout resource. This method overrides any custom toolbar view and enable
     * toolbar.
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
     * Set Toolbar's popup menu style. This method enable toolbar.
     *
     * @param toolbarPopupTheme Style resource for toolbar popup menu.
     * @return builder.
     */
    public Builder setToolbarPopupTheme(Integer toolbarPopupTheme) {
      this.toolbarPopupTheme = toolbarPopupTheme;
      enableToolbar = true;
      return this;
    }

    /**
     * Set Toolbar's background color. This method enable toolbar.
     *
     * @param toolbarBackgroundColor Color for toolbar background.
     * @return builder.
     */
    public Builder setToolbarBackgroundColor(Integer toolbarBackgroundColor) {
      this.toolbarBackgroundColor = toolbarBackgroundColor;
      enableToolbar = true;
      return this;
    }

    /**
     * Set Toolbar's navigation drawer icon resource. This method enable toolbar and navigation
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
     * Enable/disable Toolbar's back icon and back funcionality. This method enable toolbar.
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
