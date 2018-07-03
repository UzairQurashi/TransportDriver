package com.driver.travel.driverapp.recyclerviews;

import android.content.ComponentName;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.driver.travel.driverapp.R;
import com.driver.travel.driverapp.databinding.ContactItemListBinding;
import com.driver.travel.driverapp.databinding.ContactListBinding;
import com.driver.travel.driverapp.models.Consumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uzair Qureshi on 6/29/2018.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactVH> {
    private Context mContext;
    private ArrayList<Consumer> cosumerlist;

    public ContactListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ContactVH onCreateViewHolder(ViewGroup parent, int viewType) {

        ContactItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.contact_item_list, parent, false);
        return new ContactVH(binding);
    }

    @Override
    public void onBindViewHolder(ContactVH holder, int position) {
        if(cosumerlist!= null)
        holder.bindViews(cosumerlist.get(position));

    }

    @Override
    public int getItemCount() {

        if (cosumerlist != null)
            return cosumerlist.size();
        else return 0;
    }
    public void refreshData(ArrayList<Consumer> consumerList){

        this.cosumerlist=consumerList;
        notifyDataSetChanged();


    }
}
