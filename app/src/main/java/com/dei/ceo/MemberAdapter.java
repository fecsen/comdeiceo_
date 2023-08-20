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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;


    public class MemberAdapter extends RecyclerView.Adapter<com.dei.ceo.MemberAdapter.ViewHolder> {

        Context context;
        ArrayList<HashMap<String,String>> mArrayList; //회원의 모든정보 담겨있음

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

            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*
                    카드뷰 클릭하면 모든 데이터를 Intent로 MemberSearchDetailActivity로 보냄
                     */
                    int mPosition = holder.getAdapterPosition();

                    Context context = view.getContext();

                    String str_profile = noticeItem.get("profile");
                    String str_name = noticeItem.get("name");
                    String str_group_name = noticeItem.get("group_name");
                    String str_cellphone = noticeItem.get("cellphone");

                    String str_birth = noticeItem.get("birth");
                    String str_general_position = noticeItem.get("general_position");
                    String str_group_position = noticeItem.get("group_position");
                    String str_job = noticeItem.get("job");
                    String str_jobaddr = noticeItem.get("job_addr");
                    String str_jobtel = noticeItem.get("job_tel");
                    String str_jobfax = noticeItem.get("job_fax");
                    String str_hometel = noticeItem.get("home_tel");
                    String str_homeaddr = noticeItem.get("home_addr");

                    Intent intent = new Intent(context, MemberDetailActivity.class);

                    intent.putExtra("profile", str_profile);
                    intent.putExtra("name", str_name);
                    intent.putExtra("group_name", str_group_name);
                    intent.putExtra("cellphone", str_cellphone);
                    intent.putExtra("birth", str_birth);
                    intent.putExtra("general_position", str_general_position);
                    intent.putExtra("group_position", str_group_position);
                    intent.putExtra("job", str_job);
                    intent.putExtra("job_addr", str_jobaddr);
                    intent.putExtra("job_tel", str_jobtel);
                    intent.putExtra("job_fax", str_jobfax);
                    intent.putExtra("home_tel", str_hometel);
                    intent.putExtra("home_addr", str_homeaddr);
                    context.startActivity(intent);


                }

        }
        );

        }


        @Override
        public int getItemCount() {
            return mArrayList.size();
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
                cv = (CardView) v.findViewById(R.id.c2v);
                profile = (ImageView) v.findViewById(R.id.profile);
            }
            public void setItem(HashMap noticeItem) {
                tv_namee.setText(noticeItem.get("name").toString()); //이름
                tv_leaderr.setText(noticeItem.get("group_name") + "기"); //기수

                int int_group_name= Integer.parseInt(noticeItem.get("group_name").toString());
                if(int_group_name == 0) {
                    tv_leaderr.setText("원장단"); //원장단
                }
                else {
                    tv_leaderr.setText(noticeItem.get("group_name") + "기"); //기수
                }
                tv_phonee.setText(noticeItem.get("cellphone").toString()); //전화번호
                tv_position.setText(noticeItem.get("group_position").toString()); //직책
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
