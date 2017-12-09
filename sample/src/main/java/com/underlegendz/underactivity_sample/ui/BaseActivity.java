/*
 * Created by Jose Fuentes on 9/12/17 21:08
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

import android.graphics.Color;
import android.os.Bundle;
import com.underlegendz.underactivity.ActivityBuilder;
import com.underlegendz.underactivity.UnderActivity;

public class BaseActivity extends UnderActivity {

  @Override
  protected ActivityBuilder configureActivityBuilder(ActivityBuilder builder) {
    return builder.enableToolbar(true)
        .setToolbarTitleColor(Color.WHITE);
  }
}
