/*
 * Created by Jose Fuentes on 9/12/17 19:38
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

import android.support.annotation.DrawableRes;

public class Sample {
  String title;
  String description;
  @DrawableRes int imageResource;
  Class<?> destinationActivity;

  public String getTitle() {
    return title;
  }

  public Sample setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Sample setDescription(String description) {
    this.description = description;
    return this;
  }

  public int getImageResource() {
    return imageResource;
  }

  public Sample setImageResource(int imageResource) {
    this.imageResource = imageResource;
    return this;
  }

  public Class<?> getDestinationActivity() {
    return destinationActivity;
  }

  public Sample setDestinationActivity(Class<?> destinationActivity) {
    this.destinationActivity = destinationActivity;
    return this;
  }
}
