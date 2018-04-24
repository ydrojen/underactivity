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
import android.support.annotation.ColorInt;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import com.underlegendz.underactivity.UnderActivity;
import me.priyesh.chroma.ChromaDialog;
import me.priyesh.chroma.ColorMode;
import me.priyesh.chroma.ColorSelectListener;

public class MainActivity extends UnderActivity {
  final int TOOLBAR_BG = 0, TABS_BG = 1, START_DRAWER_BG = 2, END_DRAWER_BG = 3;
  Integer toolbarColor = 0xFF455A64, tabsColor = 0xFF455A64, startDrawerColor = 0xFF455A64,
      endDrawerColor = 0xFF455A64;
  CheckBox toolbarEnable, backAction, tabsEnable, drawerEnable, startNavigationViewHeader,
      endNavigationViewHeader;
  View bgToolbar, bgTabs, bgStartDrawer, bgEndDrawer, toolbarBgContainer, tabsBgContainer, launch,
      endDrawerBgContainer, startDrawerBgContainer, toolbarConfig, drawerConfig, tabsSpinnerHint;
  Spinner toolbarScrollFlags, tabsScrollFlags, startDrawerType, endDrawerType;

  @Override
  protected Builder configureActivityBuilder(Builder builder) {
    return builder.setContentLayout(R.layout.empty)
        .enableToolbar(true)
        .setToolbarTopViewResource(R.layout.top_view)
        .setToolbarScrollFlags(Builder.SCROLL_FLAG_SCROLL|Builder.SCROLL_FLAG_ENTER_ALWAYS)
        .setToolbarBackgroundColor(getResources().getColor(R.color.accentColor));
  }

  @Override
  public void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    //bindActivity();
    //initializeActivity();
  }

  private void bindActivity() {
    toolbarEnable = (CheckBox) findViewById(R.id.toolbar_enable);
    tabsEnable = (CheckBox) findViewById(R.id.toolbar_tabs);
    backAction = (CheckBox) findViewById(R.id.toolbar_back);
    drawerEnable = (CheckBox) findViewById(R.id.drawer_enable);

    bgToolbar = findViewById(R.id.toolbar_color);
    toolbarBgContainer = findViewById(R.id.toolbar_color_container);
    bgEndDrawer = findViewById(R.id.drawer_end_color);
    endDrawerBgContainer = findViewById(R.id.drawer_end_color_container);
    bgStartDrawer = findViewById(R.id.drawer_start_color);
    startDrawerBgContainer = findViewById(R.id.drawer_start_color_container);
    bgTabs = findViewById(R.id.toolbar_tabs_color);
    tabsBgContainer = findViewById(R.id.toolbar_tabs_color_container);

    toolbarConfig = findViewById(R.id.toolbar_configuration_container);
    drawerConfig = findViewById(R.id.drawer_configuration_container);

    toolbarScrollFlags = (Spinner) findViewById(R.id.toolbar_scroll_flags);
    tabsScrollFlags = (Spinner) findViewById(R.id.toolbar_tabs_scroll_flags);
    startDrawerType = (Spinner) findViewById(R.id.drawer_start_type_selector);
    endDrawerType = (Spinner) findViewById(R.id.drawer_end_type_selector);

    endNavigationViewHeader = (CheckBox) findViewById(R.id.drawer_end_header);
    startNavigationViewHeader = (CheckBox) findViewById(R.id.drawer_start_header);
    tabsSpinnerHint = findViewById(R.id.toolbar_tabs_scroll_flags_hint);

    launch = findViewById(R.id.launch);
  }

  private void initializeActivity() {
    toolbarEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        toolbarConfig.setVisibility(b ? View.VISIBLE : View.GONE);
      }
    });

    tabsEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int visibility = b ? View.VISIBLE : View.GONE;
        tabsBgContainer.setVisibility(visibility);
        tabsScrollFlags.setVisibility(visibility);
        tabsSpinnerHint.setVisibility(visibility);
      }
    });

    drawerEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        drawerConfig.setVisibility(b ? View.VISIBLE : View.GONE);
      }
    });

    toolbarBgContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectColor(TOOLBAR_BG);
      }
    });

    tabsBgContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectColor(TABS_BG);
      }
    });

    startDrawerBgContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectColor(START_DRAWER_BG);
      }
    });

    endDrawerBgContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        selectColor(END_DRAWER_BG);
      }
    });

    toolbarScrollFlags.setAdapter(
        ArrayAdapter.createFromResource(this, R.array.scroll_flags, R.layout.list_item));
    toolbarScrollFlags.setSelection(2);

    tabsScrollFlags.setAdapter(
        ArrayAdapter.createFromResource(this, R.array.scroll_flags, R.layout.list_item));
    startDrawerType.setAdapter(
        ArrayAdapter.createFromResource(this, R.array.drawer_type, R.layout.list_item));
    endDrawerType.setAdapter(
        ArrayAdapter.createFromResource(this, R.array.drawer_type, R.layout.list_item));

    startDrawerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        startNavigationViewHeader.setVisibility(i == 1 ? View.VISIBLE : View.GONE);
        startDrawerBgContainer.setVisibility(i != 0 ? View.VISIBLE : View.GONE);
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    startDrawerType.setSelection(2);

    endDrawerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        endNavigationViewHeader.setVisibility(i == 1 ? View.VISIBLE : View.GONE);
        endDrawerBgContainer.setVisibility(i != 0 ? View.VISIBLE : View.GONE);
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    launch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startDemo();
      }
    });
  }

  private void startDemo() {
    Intent launch = new Intent(this, DemoActivity.class);
    launch.putExtra(DemoActivity.KEY_TOOLBAR_ENABLE, toolbarEnable.isChecked());
    launch.putExtra(DemoActivity.KEY_DRAWER_ENABLE, drawerEnable.isChecked());
  }

  private void selectColor(final int bg) {
    int initialColor = 0;

    switch (bg) {
      case TOOLBAR_BG:
        initialColor = toolbarColor;
        break;
      case TABS_BG:
        initialColor = tabsColor;
        break;
      case START_DRAWER_BG:
        initialColor = startDrawerColor;
        break;
      case END_DRAWER_BG:
        initialColor = endDrawerColor;
        break;
    }

    new ChromaDialog.Builder().initialColor(initialColor)
        .colorMode(ColorMode.ARGB) // There's also ARGB and HSV
        .onColorSelected(new ColorSelectListener() {
          @Override
          public void onColorSelected(@ColorInt int color) {
            setColor(bg, color);
          }
        })
        .create()
        .show(getSupportFragmentManager(), "ChromaDialog");
  }

  private void setColor(int bg, int color) {
    switch (bg) {
      case TOOLBAR_BG:
        toolbarColor = color;
        bgToolbar.setBackgroundColor(color);
        break;
      case TABS_BG:
        tabsColor = color;
        bgTabs.setBackgroundColor(color);
        break;
      case START_DRAWER_BG:
        startDrawerColor = color;
        bgStartDrawer.setBackgroundColor(color);
        break;
      case END_DRAWER_BG:
        endDrawerColor = color;
        bgEndDrawer.setBackgroundColor(color);
        break;
    }
  }
}
