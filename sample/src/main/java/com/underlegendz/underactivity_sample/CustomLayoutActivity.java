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
import android.view.View;
import android.widget.TextView;
import com.underlegendz.underactivity.UnderActivity;

public class CustomLayoutActivity extends UnderActivity {
  @Override protected Builder configureActivityBuilder(Builder builder) {
    return builder.setContentLayoutResource(R.layout.custom_layout);
  }

  @Override public void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    TextView text = (TextView) findViewById(R.id.text);
    TextView code = (TextView) findViewById(R.id.code);

    text.setText(R.string.maincontent_customlayout_text);
    code.setText(R.string.main_content_customlayout_code);

    findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent i = new Intent(CustomLayoutActivity.this, FragmentActivity.class);
        startActivity(i);
      }
    });
  }
}
