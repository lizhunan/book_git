package com.sdjy.book.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sdjy.book.R;
import com.sdjy.book.mvp.entity.BooksInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李竹楠 on 2018/5/10.
 * 交换书籍列表构造器
 */

public class BooksListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<BooksInfo.BooksArrayBean> booksArrayBeans = new ArrayList<>();
    public final static int TYPE_FOOTER = 3;//底部--往往是loading_more
    public final static int TYPE_NORMAL = 1; // 通知
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener itemClickListener;

    public BooksListAdapter(Context context, List<BooksInfo.BooksArrayBean> booksArrayBeans) {
        this.context = context;
        this.booksArrayBeans = booksArrayBeans;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param orgs
     */
    public void updateListView(List<BooksInfo.BooksArrayBean> orgs) {
        if (orgs == null) {
            this.booksArrayBeans = new ArrayList<BooksInfo.BooksArrayBean>();
        } else {
            this.booksArrayBeans = orgs;
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view;
        switch (viewType) {
            case TYPE_NORMAL:
                view = mLayoutInflater.inflate(R.layout.books_list, parent, false);
                holder = new ItemViewHolder(view);
                break;
            case TYPE_FOOTER:
                view = mLayoutInflater.inflate(R.layout.recyclerview_footer, parent, false);
                holder = new FooterViewHolder(view);
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
        BooksInfo.BooksArrayBean booksArrayBean = booksArrayBeans.get(position);
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder newHolder = (ItemViewHolder) holder;
            newHolder.bookNameTv.setText(booksArrayBean.getBookName());
            newHolder.bookAuthorTv.setText(booksArrayBean.getAthor());
            newHolder.bookPriceTv.setText(""+booksArrayBean.getPrice());
            newHolder.bookPublisherTv.setText(booksArrayBean.getPublishing());
            newHolder.bookCv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.OnItemClickListener(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (booksArrayBeans != null) {
            return booksArrayBeans.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        BooksInfo.BooksArrayBean booksArrayBean = booksArrayBeans.get(position);
        if (booksArrayBean == null) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView profileIv;
        TextView bookNameTv, bookAuthorTv, bookPriceTv, bookPublisherTv;
        CardView bookCv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            bookCv = itemView.findViewById(R.id.book_cv);
            bookNameTv = itemView.findViewById(R.id.book_name_tv);
            bookAuthorTv = itemView.findViewById(R.id.author_tv);
            bookPriceTv = itemView.findViewById(R.id.price_tv);
            bookPublisherTv = itemView.findViewById(R.id.publisher_tv);
            profileIv = itemView.findViewById(R.id.profile_iv);
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
