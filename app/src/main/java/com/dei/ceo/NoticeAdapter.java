package com.dei.ceo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Fecsen on 2018-02-13.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    Context context;
    ArrayList<HashMap<String,String>> noticeList; //공지사항 정보 담겨있음

    public NoticeAdapter(Context context, ArrayList<HashMap<String, String>> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recycler view에 반복될 아이템 레이아웃 연결
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice_cardview,null);
        return new ViewHolder(v);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    /** 정보 및 이벤트 처리는 이 메소드에서 구현 **/
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        HashMap<String,String> noticeItem = noticeList.get(position);
      //  holder.tv_writer.setText(noticeItem.get("writer")); //작성자
      //  Log.e("[writer]", noticeItem.get("writer"));
        holder.tv_title.setText(noticeItem.get("title")); //제목
        holder.tv_msg.setText(noticeItem.get("msg")); //내용 일부
        holder.tv_date.setText(noticeItem.get("date")); //작성일
        holder.shareicon.setOnClickListener(new View.OnClickListener()  {

            @Override
            public void onClick(View v) {
                String tt = holder.tv_title.getText().toString();
                String mm = holder.tv_msg.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, tt);
                intent.putExtra(Intent.EXTRA_TEXT, mm);
                context.startActivity(Intent.createChooser(intent, "공유하기"));
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.noticeList.size();
    }
    /** item layout 불러오기 **/
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_date;
        TextView tv_msg;
        TextView tv_writer;
        CardView cv;
        ImageView shareicon;
        public ViewHolder(View v) {
            super(v);
            tv_title = (TextView) v.findViewById(R.id.tv_title);
            tv_date = (TextView) v.findViewById(R.id.tv_date);
            tv_msg = (TextView) v.findViewById(R.id.tv_msg);
        //    tv_writer = (TextView) v.findViewById(R.id.tv_writer);
            cv = (CardView) v.findViewById(R.id.cv);
            shareicon = (ImageView) v.findViewById(R.id.shareicon);
        }
    }
}
