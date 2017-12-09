/*
 * Created by Jose Fuentes on 9/12/17 20:40
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

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.underlegendz.underactivity_sample.R;
import com.underlegendz.underactivity_sample.data.Sample;
import com.underlegendz.underactivity_sample.data.SamplesRepository;
import java.util.List;

public class SampleDataAdapter extends RecyclerView.Adapter<SampleDataAdapter.SampleDataViewHolder> {

  List<Sample> mSampleList;

  public SampleDataAdapter() {
    mSampleList = SamplesRepository.getSamples();
  }

  @Override
  public SampleDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new SampleDataViewHolder(LayoutInflater.from(
        parent.getContext()).inflate(R.layout.row_sample, parent, false));
  }

  @Override
  public void onBindViewHolder(SampleDataViewHolder holder, int position) {
    Sample sample = mSampleList.get(position);
    holder.image.setImageResource(sample.getImageResource());
    holder.description.setText(sample.getDescription());
    holder.title.setText(sample.getTitle());
  }

  @Override
  public int getItemCount() {
    return mSampleList.size();
  }

  public class SampleDataViewHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener {

    ImageView image;
    TextView title;
    TextView description;

    public SampleDataViewHolder(View itemView) {
      super(itemView);
      itemView.setOnClickListener(this);
      image = itemView.findViewById(R.id.sample_row_image);
      title = itemView.findViewById(R.id.sample_row_title);
      description = itemView.findViewById(R.id.sample_row_description);
    }

    @Override
    public void onClick(View view) {
      Sample sample = mSampleList.get(getAdapterPosition());
      Intent goTo = new Intent(view.getContext(), sample.getDestinationActivity());
      view.getContext().startActivity(goTo);
    }
  }
}
