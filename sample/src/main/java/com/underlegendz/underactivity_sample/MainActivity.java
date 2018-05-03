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
import android.support.v7.widget.RecyclerView;
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

  @Override
  protected Builder configureActivityBuilder(Builder builder) {
    return builder
        .enableToolbar(true)
        .setToolbarTopViewResource(R.layout.top_view)
        .setToolbarScrollFlags(Builder.SCROLL_FLAG_SCROLL|Builder.SCROLL_FLAG_ENTER_ALWAYS)
        .setToolbarBackgroundColor(getResources().getColor(R.color.accentColor));
  }

  @Override
  public void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    setFragment(new RecyclerFragment());
  }
}
