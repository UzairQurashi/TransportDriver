package com.driver.travel.driverapp.recyclerviews;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.driver.travel.driverapp.R;
import com.driver.travel.driverapp.databinding.ContactListBinding;

/**
 * Created by Uzair Qureshi on 6/29/2018.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactVH> {
    private Context mContext;

    public ContactListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ContactVH onCreateViewHolder(ViewGroup parent, int viewType) {

        ContactListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.contact_list, parent, false);
        return new ContactVH(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ContactVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
