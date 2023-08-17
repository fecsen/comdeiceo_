package com.dei.ceo;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;


    public class MemberAdapter extends RecyclerView.Adapter<com.dei.ceo.MemberAdapter.ViewHolder> {

        Context context;
        ArrayList<HashMap<String,String>> mArrayList; //공지사항 정보 담겨있음

        public MemberAdapter(Context context, ArrayList<HashMap<String, String>> mArrayList) {
            this.context = context;
            this.mArrayList = mArrayList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //recycler view에 반복될 아이템 레이아웃 연결
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member_cardview,null);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            HashMap<String, String> noticeItem = mArrayList.get(position);
            holder.setItem(noticeItem);

        }


        @Override
        public int getItemCount() {
            return this.mArrayList.size();
        }
        /** item layout 불러오기 **/
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_namee;
            TextView tv_leaderr;
            TextView tv_phonee;
            TextView tv_position;
            CardView cv;
            ImageView profile;
            public ViewHolder(View v) {
                super(v);
                tv_namee = (TextView) v.findViewById(R.id.tv_namee);
                tv_leaderr = (TextView) v.findViewById(R.id.tv_leaderr);
                tv_phonee = (TextView) v.findViewById(R.id.tv_phone);
                tv_position = (TextView) v.findViewById(R.id.tv_position);
                cv = (CardView) v.findViewById(R.id.cv);
                profile = (ImageView) v.findViewById(R.id.profile);
            }
            public void setItem(HashMap noticeItem) {
                tv_namee.setText(noticeItem.get("name").toString()); //제목
                tv_leaderr.setText(noticeItem.get("group_name") + "기"); //내용 일부
                tv_phonee.setText(noticeItem.get("cellphone").toString()); //작성일
                tv_position.setText(noticeItem.get("group_position").toString()); //작성일
                String graphUri = noticeItem.get("profile").toString();
                String profile_url = "https://dei.hivecom.co.kr/dei/profile/" + graphUri;
                imgload(profile_url, profile);
            }
        }
        public void imgload(String profile_url, ImageView profile) {
            ProfileIMGLoadTask task = new ProfileIMGLoadTask(profile_url, profile);
            task.execute();
        }
    }
