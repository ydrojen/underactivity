/*
 * Created by Jose Fuentes on 9/12/17 20:45
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

package com.underlegendz.underactivity_sample.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.underlegendz.underactivity_sample.R;
import com.underlegendz.underactivity_sample.ui.adapter.SampleDataAdapter;

public class SampleDataListFragment extends android.support.v4.app.Fragment {

  public static SampleDataListFragment newInstance() {
    Bundle args = new Bundle();
    SampleDataListFragment fragment = new SampleDataListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.recycler, container, false);
    RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
    recyclerView.setBackgroundColor(0xFFF0F0F0);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setAdapter(new SampleDataAdapter());
    return v;
  }
}
