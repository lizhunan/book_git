package com.sdjy.book.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sdjy.book.R;
import com.sdjy.book.mvp.entity.ConsumeInfo;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 李竹楠 on 2018/5/17.
 * 换购信息列表构造器
 */

public class ConsumeInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    public final static int TYPE_FOOTER = 3;//底部--往往是loading_more
    public final static int TYPE_NORMAL = 1; // 通知
    private LayoutInflater mLayoutInflater;
    private List<ConsumeInfo.ExchangeArrayBean> dynList = new ArrayList<>();
    private ConsumeInfoAdapter.OnItemClickListener itemClickListener;

    public ConsumeInfoAdapter(Context context, List<ConsumeInfo.ExchangeArrayBean> dynList) {
        this.context = context;
        this.dynList = dynList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(ConsumeInfoAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param ConsumeInfo
     */
    public void updateListView(List<ConsumeInfo.ExchangeArrayBean> ConsumeInfo) {
        if (ConsumeInfo == null) {
            this.dynList = new ArrayList<ConsumeInfo.ExchangeArrayBean>();
        } else {
            this.dynList = ConsumeInfo;
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view;
        switch (viewType) {
            case TYPE_NORMAL:
                view = mLayoutInflater.inflate(R.layout.user_dyn, parent, false);
                holder = new ConsumeInfoAdapter.ItemViewHolder(view);
                break;
            case TYPE_FOOTER:
                view = mLayoutInflater.inflate(R.layout.recyclerview_footer, parent, false);
                holder = new ConsumeInfoAdapter.FooterViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        //这时候 article是 null，先把 footer 处理了
        if (holder instanceof FooterViewHolder) {
            ((FooterViewHolder) holder).progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        ConsumeInfo.ExchangeArrayBean ConsumeInfo = dynList.get(position);
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder newHolder = (ItemViewHolder) holder;
            newHolder.idTv.setText(ConsumeInfo.getExchangeId());
            newHolder.pointTv.setText(""+ConsumeInfo.getPoints());
            newHolder.timeTv.setText(ConsumeInfo.getCreateTime());
            newHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.OnItemClickListener(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (dynList != null) {
            return dynList.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        ConsumeInfo.ExchangeArrayBean ConsumeInfo = dynList.get(position);
        if (ConsumeInfo == null) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView idTv;
        TextView timeTv;
        TextView pointTv;
        CardView cardView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            idTv = itemView.findViewById(R.id.id_tv);
            timeTv = itemView.findViewById(R.id.time_tv);
            pointTv = itemView.findViewById(R.id.point_tv);
            cardView = itemView.findViewById(R.id.cv);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public FooterViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.load_more_pb);
        }
    }

    public interface OnItemClickListener {
        void OnItemClickListener(View view, int position);
    }
}
