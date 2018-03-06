/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package viewdemo.tumour.com.a51ehealth.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.chrisbanes.photoview.PhotoView;

import viewdemo.tumour.com.a51ehealth.view.Adapter.ImagePagerAdapter;
import viewdemo.tumour.com.a51ehealth.view.R;
import viewdemo.tumour.com.a51ehealth.view.bean.ImageUrl;
import viewdemo.tumour.com.a51ehealth.view.utils.glide.GlideApp;


public class ImageFragment extends Fragment {

    private ImageUrl imageUrl;

    public static ImageFragment newInstance(ImageUrl data) {
        ImageFragment fragment = new ImageFragment();
        Bundle argument = new Bundle();
        argument.putSerializable("ImageUrl", data);
        fragment.setArguments(argument);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        imageUrl = (ImageUrl) arguments.getSerializable("ImageUrl");

        View view = inflater.inflate(R.layout.home_weiboitem_imagedetails_item, null);
        ViewHolder holder = new ViewHolder(view);
        GlideApp
                .with(this)
                .load(imageUrl.getBigImage())
                .imageProgressListener(imageUrl.getBigImage(), holder.photo_view)
                .thumbnail(GlideApp.with(this).load(imageUrl.getSmallImage()))
                .into(holder.photo_view);
        holder.photo_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getActivity().finishAfterTransition();
                } else {
                    getActivity().finish();
                }


            }
        });

        holder.ImageViewItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getActivity().finishAfterTransition();
                } else {
                    getActivity().finish();
                }
            }
        });


        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public PhotoView photo_view;
        public RelativeLayout ImageViewItemLayout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.photo_view = (PhotoView) rootView.findViewById(R.id.photo_view);
            this.ImageViewItemLayout = (RelativeLayout) rootView.findViewById(R.id.ImageViewItemLayout);
        }

    }
}
