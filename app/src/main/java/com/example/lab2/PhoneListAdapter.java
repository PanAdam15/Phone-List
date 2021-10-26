package com.example.lab2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.sax.Element;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {

    private List<PhoneEntity> mPhoneList;
    private LayoutInflater mInflate;
    private ElementViewModel phoneViewModel;
    private ItemTouchHelper itemTouchHelper;

    interface OnItemClickListener {
        void onItemClick(int position);
    }
    
    private OnItemClickListener mOnItemClickListener;

    public void setPhoneViewModel(ElementViewModel phoneViewModel) {
        this.phoneViewModel = phoneViewModel;
    }

    public PhoneListAdapter(Context context,OnItemClickListener mOnItemClickListener) {
        mInflate = LayoutInflater.from(context);
        this.mPhoneList = null;
       this.mOnItemClickListener = mOnItemClickListener;
    }
    
    @Override
    public int getItemCount() {
        if (mPhoneList != null)
            return mPhoneList.size();
        return 0;
    }

    public void setElementList(List<PhoneEntity> mPhoneList) {
        this.mPhoneList = mPhoneList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View wiersz = mInflate.inflate(R.layout.activity_row, null);
        return new PhoneViewHolder(wiersz);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder phoneViewHolder, int rowNumber) {
        PhoneEntity phones = mPhoneList.get(rowNumber);
        phoneViewHolder.SetRow(rowNumber);
        phoneViewHolder.producentView.setText(phones.getProducent());
        phoneViewHolder.modelView.setText(phones.getModel());

    }
    public void onItemSwiped(int position) {
        phoneViewModel.deleteSelected(mPhoneList.get(position));
        mPhoneList.remove(position);
        notifyItemRemoved(position);
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder  {
        TextView producentView, modelView;
        int rowNumber;

            public PhoneViewHolder(@NonNull View mainRowElemet) {
            super(mainRowElemet);
            producentView = mainRowElemet.findViewById(R.id.producentView_list);
            modelView = mainRowElemet.findViewById(R.id.modelView_list);
           mainRowElemet.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   mOnItemClickListener.onItemClick(getAdapterPosition());
            
               }
           });
        }

        public void SetRow(int rowNumber) {
            this.rowNumber = rowNumber;

        }
    }
}