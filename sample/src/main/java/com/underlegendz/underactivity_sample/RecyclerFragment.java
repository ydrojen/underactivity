package com.underlegendz.underactivity_sample;
/**
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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerFragment extends android.support.v4.app.Fragment {

  public static RecyclerFragment newInstance() {
    Bundle args = new Bundle();
    RecyclerFragment fragment = new RecyclerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  static final int SIZE = 50;

  @Nullable
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.recycler, container, false);
    RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setAdapter(new RecyclerView.Adapter() {
      @Override
      public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false));
      }

      @Override
      public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).text.setText("Data - "+position);
      }

      @Override
      public int getItemCount() {
        return SIZE;
      }
    });


    return v;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView text;
    public ViewHolder(View itemView) {
      super(itemView);
      text = (TextView) itemView.findViewById(R.id.text);
    }
  }
}
