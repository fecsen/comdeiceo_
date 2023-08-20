package com.dei.ceo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    Context context;
    ArrayList<HashMap<String,String>> scheduleList; //공지사항 정보 담겨있음

    public ScheduleAdapter(Context context, ArrayList<HashMap<String, String>> scheduleList) {
        this.context = context;
        this.scheduleList = scheduleList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recycler view에 반복될 아이템 레이아웃 연결
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule_cardview,null);
        return new ViewHolder(v);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    /** 정보 및 이벤트 처리는 이 메소드에서 구현 **/
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        HashMap<String,String> scheduleItem = scheduleList.get(position);

        holder.tv_s_title.setText(scheduleItem.get("title")); //제목
        holder.tv_year.setText(scheduleItem.get("year")); //내용 일부
        holder.tv_month.setText(scheduleItem.get("month")); //작성일
        holder.tv_day.setText(scheduleItem.get("day")); //제목
        holder.tv_sub.setText(scheduleItem.get("sub")); //내용 일부
    }


    @Override
    public int getItemCount() {
        return this.scheduleList.size();
    }
    /** item layout 불러오기 **/
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_s_title;
        TextView tv_month;
        TextView tv_day;
        TextView tv_year;
        TextView tv_sub;
        CardView cv;
        public ViewHolder(View v) {
            super(v);
            tv_s_title = (TextView) v.findViewById(R.id.tv_s_title);
            tv_year = (TextView) v.findViewById(R.id.tv_year);
            tv_month = (TextView) v.findViewById(R.id.tv_month);
            tv_day = (TextView) v.findViewById(R.id.tv_day);
            tv_sub = (TextView) v.findViewById(R.id.tv_sub);
            //    tv_writer = (TextView) v.findViewById(R.id.tv_writer);
            cv = (CardView) v.findViewById(R.id.s_cv);

        }
    }
}
