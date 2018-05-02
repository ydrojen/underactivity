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

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ActivityBuilder {
  public ActivityBuilder() {
  }

  // Show fullscreen
  boolean enableFullScreenMode = false;
  // Components enabled
  boolean enableToolbar = false;
  boolean enableDrawer = false;
  boolean enableToolbarTabs = false;
  // Main Content
  View contentLayout = null;
  Integer contentLayoutResource = null;
  // Start Drawer
  View drawerCustomLayout = null;
  Integer drawerCustomLayoutResource = null;
  View drawerNavigationViewHeader = null;
  Integer drawerNavigationViewHeaderResource = null;
  Integer drawerNavigationViewMenuResource = null;
  Integer drawerNavigationViewBackgroundColor = null;
  NavigationView.OnNavigationItemSelectedListener drawerOnNavigationItemSelectedListener;
  // End Drawer
  View endDrawerCustomLayout = null;
  Integer endDrawerCustomLayoutResource = null;
  View endDrawerNavigationViewHeader = null;
  Integer endDrawerNavigationViewHeaderResource = null;
  Integer endDrawerNavigationViewMenuResource = null;
  Integer endDrawerNavigationViewBackgroundColor = null;
  NavigationView.OnNavigationItemSelectedListener endDrawerOnNavigationItemSelectedListener;
  // Toolbar
  Toolbar toolbar = null;
  Integer toolbarResource = null;
  Integer toolbarPopupTheme = null;
  Integer toolbarBackgroundColor = null;
  Integer toolbarTitleColor = null;
  Integer toolbarDrawerIcon = null;
  Integer toolbarBackIcon = null;
  boolean toolbarBack = false;
  // AppBarLayout
  int toolbarScrollFlags = 0;
  AppBarLayout appBarLayout = null;
  Integer appBarLayoutResource = null;
  Integer toolbarTabLayoutBackgroundColor = null;
  int tabLayoutScrollFlags = 0;
  TabLayout tabLayoutView = null;
  Integer tabLayoutResource = null;

  /**
   * Enable/Disable fullscreen mode.
   *
   * @param enableFullScreenMode It indicates whether the activity shows in fullscreen.
   * @return builder.
   */
  public ActivityBuilder enableFullScreenMode(boolean enableFullScreenMode) {
    this.enableFullScreenMode = enableFullScreenMode;
    return this;
  }

  /**
   * Enable/Disable Toolbar.
   *
   * @param enableToolbar It indicates whether the activity has toolbar.
   * @return builder.
   */
  public ActivityBuilder enableToolbar(boolean enableToolbar) {
    this.enableToolbar = enableToolbar;
    return this;
  }

  /**
   * Enable/Disable Drawer.
   *
   * @param enableDrawer It indicates whether the activity has navigation drawer.
   * @return builder.
   */
  public ActivityBuilder enableDrawer(boolean enableDrawer) {
    this.enableDrawer = enableDrawer;
    return this;
  }

  /**
   * Enable/Disable Toolbar Tabs.
   *
   * @param enableToolbarTabs It indicates whether create TabLayout in an AppBarLayout.
   * @return builder.
   */
  public ActivityBuilder enableToolbarTabs(boolean enableToolbarTabs) {
    this.enableToolbarTabs = enableToolbarTabs;
    return this;
  }

  /**
   * Set custom layout view for activity. This method overrides any custom layout resource.
   *
   * @param contentLayout Activity's layout view.
   * @return builder.
   */
  public ActivityBuilder setContentLayout(View contentLayout) {
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
  public ActivityBuilder setContentLayout(@LayoutRes Integer contentLayoutResource) {
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
  public ActivityBuilder setDrawerCustomLayout(View drawerCustomLayout) {
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
  public ActivityBuilder setDrawerCustomLayout(@LayoutRes Integer drawerCustomLayoutResource) {
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
  public ActivityBuilder setDrawerNavigationViewHeader(View drawerNavigationViewHeader) {
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
  public ActivityBuilder setDrawerNavigationViewHeader(
      @LayoutRes Integer drawerNavigationViewHeaderResource) {
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
  public ActivityBuilder setDrawerNavigationViewMenuResource(
      @MenuRes Integer drawerNavigationViewMenuResource) {
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
  public ActivityBuilder setDrawerNavigationViewBackgroundColor(
      @ColorInt Integer drawerNavigationViewBackgroundColor) {
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
  public ActivityBuilder setDrawerOnNavigationItemSelectedListener(
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
  public ActivityBuilder setEndDrawerCustomLayout(View endDrawerCustomLayout) {
    this.endDrawerCustomLayout = endDrawerCustomLayout;
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
  public ActivityBuilder setEndDrawerCustomLayout(
      @LayoutRes Integer endDrawerCustomLayoutResource) {
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
  public ActivityBuilder setEndDrawerNavigationViewHeader(View endDrawerNavigationViewHeader) {
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
  public ActivityBuilder setEndDrawerNavigationViewHeader(
      @DrawableRes Integer endDrawerNavigationViewHeaderResource) {
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
  public ActivityBuilder setEndDrawerNavigationViewMenuResource(
      @MenuRes Integer endDrawerNavigationViewMenuResource) {
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
  public ActivityBuilder setEndDrawerNavigationViewBackgroundColor(
      @ColorInt Integer endDrawerNavigationViewBackgroundColor) {
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
  public ActivityBuilder setEndDrawerOnNavigationItemSelectedListener(
      NavigationView.OnNavigationItemSelectedListener endDrawerOnNavigationItemSelectedListener) {
    this.endDrawerOnNavigationItemSelectedListener = endDrawerOnNavigationItemSelectedListener;
    endDrawerCustomLayout = null;
    endDrawerCustomLayoutResource = null;
    enableDrawer = true;
    return this;
  }

  /**
   * Set custom Toolbar view. This method overrides any custom toolbar layout resource,
   * any AppBarLayout and enable toolbar.
   *
   * @param toolbar Toolbar's view.
   * @return builder.
   */
  public ActivityBuilder setToolbar(Toolbar toolbar) {
    this.toolbar = toolbar;
    this.appBarLayoutResource = null;
    this.appBarLayout = null;
    this.toolbarResource = null;
    this.enableToolbar = true;
    return this;
  }

  /**
   * Set custom Toolbar layout resource. This method overrides any custom toolbar view, any
   * AppBarLayout and enable toolbar.
   *
   * @param toolbarResource Toolbar's layout resource.
   * @return builder.
   */
  public ActivityBuilder setToolbar(@LayoutRes Integer toolbarResource) {
    this.toolbarResource = toolbarResource;
    this.toolbar = null;
    this.appBarLayoutResource = null;
    this.appBarLayout = null;
    this.enableToolbar = true;
    return this;
  }

  /**
   * Set Toolbar's popup menu style. This method enable toolbar.
   *
   * @param toolbarPopupTheme Style resource for toolbar popup menu.
   * @return builder.
   */
  public ActivityBuilder setToolbarPopupTheme(@StyleRes Integer toolbarPopupTheme) {
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
  public ActivityBuilder setToolbarBackgroundColor(@ColorInt Integer toolbarBackgroundColor) {
    this.toolbarBackgroundColor = toolbarBackgroundColor;
    enableToolbar = true;
    return this;
  }

  /**
   * Set Toolbar's title color. This method enable toolbar.
   *
   * @param toolbarTitleColor Color for toolbar background.
   * @return builder.
   */
  public ActivityBuilder setToolbarTitleColor(@ColorInt Integer toolbarTitleColor) {
    this.toolbarTitleColor = toolbarTitleColor;
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
  public ActivityBuilder setToolbarDrawerIcon(@DrawableRes Integer toolbarDrawerIcon) {
    this.toolbarDrawerIcon = toolbarDrawerIcon;
    enableDrawer = true;
    enableToolbar = true;
    return this;
  }

  /**
   * Enable/disable Toolbar's back icon and back functionality. This method enable toolbar.
   *
   * @param toolbarBack enable/disable.
   * @return builder.
   */
  public ActivityBuilder setToolbarBack(boolean toolbarBack) {
    this.toolbarBack = toolbarBack;
    enableToolbar = true;
    return this;
  }

  /**
   * Set Toolbar's back icon and enable back functionality. This method enable toolbar.
   *
   * @param toolbarBackIcon Toolbar's back icon resource.
   * @return builder.
   */
  public ActivityBuilder setToolbarBackIcon(@DrawableRes Integer toolbarBackIcon) {
    this.toolbarBack = true;
    enableToolbar = true;
    this.toolbarBackIcon = toolbarBackIcon;
    return this;
  }

  /**
   * Set AppBarLayout scroll flags. This method enable toolbar.
   * @param toolbarScrollFlags Flags for AppBarLayout's scroll.
   * @return builder
   */
  public ActivityBuilder setToolbarScrollFlags(@ScrollFlags int toolbarScrollFlags) {
    this.toolbarScrollFlags = toolbarScrollFlags;
    this.enableToolbar = true;
    return this;
  }

  /**
   * Set custom AppBarLayout view. This method overrides any custom toolbar and
   * enable toolbar.
   *
   * @param appBarLayout AppBarLayout's view.
   * @return builder.
   */
  public ActivityBuilder setAppBarLayout(AppBarLayout appBarLayout) {
    this.appBarLayout = appBarLayout;
    toolbar = null;
    toolbarResource = null;
    appBarLayoutResource = null;
    enableToolbar = true;
    return this;
  }

  /**
   * Set custom AppBarLayout layout resource. This method overrides any custom toolbar and
   * enable toolbar.
   *
   * @param appBarLayoutResource Toolbar's layout resource.
   * @return builder.
   */
  public ActivityBuilder setAppBarLayout(@LayoutRes Integer appBarLayoutResource) {
    this.appBarLayoutResource = appBarLayoutResource;
    toolbar = null;
    toolbarResource = null;
    appBarLayout = null;
    enableToolbar = true;
    return this;
  }

  /**
   * Set Toolbar Tab's background color. This method enable toolbar.
   *
   * @param toolbarTabLayoutBackgroundColor Color for toolbar background.
   * @return builder.
   */
  public ActivityBuilder setToolbarTabLayoutBackgroundColor(
      @ColorInt Integer toolbarTabLayoutBackgroundColor) {
    this.toolbarTabLayoutBackgroundColor = toolbarTabLayoutBackgroundColor;
    enableToolbar = true;
    enableToolbarTabs = true;
    return this;
  }

  /**
   * Set TabLayout scroll flags. This method enable toolbar and tabs.
   * @param tabLayoutScrollFlags Flags for TabLayout's scroll.
   * @return builder
   */
  public ActivityBuilder setTabLayoutScrollFlags(@ScrollFlags int tabLayoutScrollFlags) {
    this.tabLayoutScrollFlags = tabLayoutScrollFlags;
    this.enableToolbar = true;
    this.enableToolbarTabs = true;
    return this;
  }

  /**
   * Set custom TabLayout view. This method overrides any custom TabLayoutResource or
   * AppBarLayout and enable toolbar.
   *
   * @param tabLayoutView TabLayout's view.
   * @return builder.
   */
  public ActivityBuilder setTabLayout(TabLayout tabLayoutView) {
    this.tabLayoutView = tabLayoutView;
    this.tabLayoutResource = null;
    this.appBarLayoutResource = null;
    this.appBarLayout = null;
    this.enableToolbar = true;
    this.enableToolbarTabs = true;
    return this;
  }

  /**
   * Set custom TabLayout layout resource. This method overrides any custom TabLayout view or
   * AppBarLayout and enable toolbar.
   *
   * @param tabLayoutResource TabLayout's layout resource.
   * @return builder.
   */
  public ActivityBuilder setTabLayout(@LayoutRes Integer tabLayoutResource) {
    this.tabLayoutResource = tabLayoutResource;
    this.appBarLayout = null;
    this.appBarLayoutResource = null;
    this.enableToolbar = true;
    this.enableToolbarTabs = true;
    return this;
  }

  @IntDef(flag = true, value = {
      SCROLL_FLAG_SCROLL,
      SCROLL_FLAG_EXIT_UNTIL_COLLAPSED,
      SCROLL_FLAG_ENTER_ALWAYS,
      SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED,
      SCROLL_FLAG_SNAP
  })
  @Retention(RetentionPolicy.SOURCE)
  public @interface ScrollFlags {
  }

  /**
   * The view will be scroll in direct relation to scroll events. This flag needs to be
   * set for any of the other flags to take effect. If any sibling views
   * before this one do not have this flag, then this value has no effect.
   */
  public static final int SCROLL_FLAG_SCROLL = 1;

  /**
   * When exiting (scrolling off screen) the view will be scrolled until it is
   * 'collapsed'. The collapsed height is defined by the view's minimum height.
   */
  public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 1 << 1;

  /**
   * When entering (scrolling on screen) the view will scroll on any downwards
   * scroll event, regardless of whether the scrolling view is also scrolling. This
   * is commonly referred to as the 'quick return' pattern.
   */
  public static final int SCROLL_FLAG_ENTER_ALWAYS = 1 << 2;

  /**
   * An additional flag for 'enterAlways' which modifies the returning view to
   * only initially scroll back to it's collapsed height. Once the scrolling view has
   * reached the end of it's scroll range, the remainder of this view will be scrolled
   * into view. The collapsed height is defined by the view's minimum height.
   */
  public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 1 << 3;

  /**
   * Upon a scroll ending, if the view is only partially visible then it will be snapped
   * and scrolled to it's closest edge. For example, if the view only has it's bottom 25%
   * displayed, it will be scrolled off screen completely. Conversely, if it's bottom 75%
   * is visible then it will be scrolled fully into view.
   */
  public static final int SCROLL_FLAG_SNAP = 1 << 4;
}