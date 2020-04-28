package me.xujichang.train.paging.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

import me.xujichang.train.paging.R;
import me.xujichang.train.paging.databinding.PagingUserItemBinding;
import me.xujichang.train.paging.entity.User;

/**
 * Info:for JetpackTraining  in me.xujichang.train.paging.adapter.PagedListAdapter
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/4/14 7:40 PM
 * @since 1.0.0
 */
public class UserAdapter extends PagedListAdapter<User, UserViewHolder> {

    private static DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getUserId() == newItem.getUserId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.equals(newItem);
        }
    };

    public UserAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new UserViewHolder(PagingUserItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }
}
