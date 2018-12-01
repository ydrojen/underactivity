/*
 * Created by Jose Fuentes on 9/12/17 19:37
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

package com.underlegendz.underactivity_sample.data;

import com.underlegendz.underactivity_sample.R;
import com.underlegendz.underactivity_sample.ui.activity.CustomDrawerActivity;
import com.underlegendz.underactivity_sample.ui.activity.CustomToolbarActivity;
import com.underlegendz.underactivity_sample.ui.activity.NavigationViewActivity;
import com.underlegendz.underactivity_sample.ui.activity.NoExtendsActivity;
import java.util.LinkedList;
import java.util.List;

public class SamplesRepository {

  public static List<Sample> getSamples() {
    List<Sample> sampleList = new LinkedList<>();

    // No extends activity
    sampleList.add(new Sample()
        .setTitle("No extends Activity")
        .setDescription("Apply ActivityBuilder in an Activity without UnderActivity extension")
        .setDestinationActivity(NoExtendsActivity.class)
        .setImageResource(R.drawable.ic_settings_black_24dp)
    );

    // Navigation Drawer
    sampleList.add(new Sample()
        .setTitle("NavigationView")
        .setDescription("DrawerLayout initialized with NavigationView component")
        .setImageResource(R.drawable.navigation_drawer)
        .setDestinationActivity(NavigationViewActivity.class)
    );

    sampleList.add(new Sample()
        .setTitle("Custom Drawer layout")
        .setDescription("DrawerLayout initialized with custom 'drawer' layout")
        .setImageResource(R.drawable.navigation_drawer)
        .setDestinationActivity(CustomDrawerActivity.class)
    );

    // Toolbar
    sampleList.add(new Sample()
        .setTitle("Custom Toolbar layout")
        .setDescription("Toolbar initialized with custom layout")
        .setImageResource(R.drawable.toolbars)
        .setDestinationActivity(CustomToolbarActivity.class)
    );

    return sampleList;
  }
}
