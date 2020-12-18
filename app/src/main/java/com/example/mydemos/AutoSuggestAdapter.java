package com.example.mydemos;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AutoSuggestAdapter extends ArrayAdapter<String> implements Filterable {
   private final List<String> mlistData;
    public AutoSuggestAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mlistData=new ArrayList<>();
    }

    public void setData(List<String> lists){
        mlistData.clear();
        mlistData.addAll(lists);
    }

    @Override
    public int getCount() {
        return mlistData.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return mlistData.get(position);
    }
    public String getObject(int position){
        return mlistData.get(position);
    }

    public Filter getFilter(){
        Filter filter =new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
               FilterResults filterResults=new FilterResults();
               if (constraint!=null){
                   filterResults.values=mlistData;
                   filterResults.count=mlistData.size();
               }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
if (results!=null && (results.count>0)){
notifyDataSetChanged();
    mlistData.clear();

}else {
    notifyDataSetInvalidated();
}
            }
        };
        return filter;
    }

}
