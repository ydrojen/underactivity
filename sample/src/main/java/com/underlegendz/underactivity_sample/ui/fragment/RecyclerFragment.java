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

package com.underlegendz.underactivity_sample.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.underlegendz.underactivity_sample.R;

public class RecyclerFragment extends Fragment {

  public static RecyclerFragment newInstance() {
    Bundle args = new Bundle();
    RecyclerFragment fragment = new RecyclerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  private static final int SIZE = 50;

  @Nullable
  @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.recycler, container, false);
    RecyclerView recyclerView = v.findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setAdapter(new RecyclerView.Adapter() {
      @NonNull
      @Override
      public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sample, parent, false));
      }

      @SuppressLint("SetTextI18n")
      @Override
      public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
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
    ViewHolder(View itemView) {
      super(itemView);
      text = itemView.findViewById(R.id.text);
    }
  }
}
