package me.xujichang.train.paging.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import me.xujichang.train.paging.databinding.PagingUserItemBinding;
import me.xujichang.train.paging.entity.User;

/**
 * Info:for JetpackTraining  in me.xujichang.train.paging.adapter.UserViewHolder
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/4/14 7:41 PM
 * @since 1.0.0
 */
class UserViewHolder extends RecyclerView.ViewHolder {
    private PagingUserItemBinding mBinding;

    public UserViewHolder(@NonNull PagingUserItemBinding itemView) {
        super(itemView.getRoot());
        mBinding = itemView;
    }

    public void bindData(User pItem) {
        if (null == pItem) {
            clear();
        } else {
            patch(pItem);
        }
    }

    private void patch(User pItem) {
        mBinding.tvUser.setText(pItem.getName());
    }

    private void clear() {
        mBinding.tvUser.setText("clear....");
    }
}
