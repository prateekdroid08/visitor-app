package com.visitorapp.bloominfotech.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.interfaces.OnPurposeItemClick;
import com.visitorapp.bloominfotech.models.purpose.PurposeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateekarora on 22/11/16.
 */

public class PurposeFilterAdapter extends ArrayAdapter<PurposeList> implements Filterable {

    List<PurposeList> items;
    List<PurposeList> itemsAll;
    List<PurposeList> suggestions;
    Context mContext;
    OnPurposeItemClick onPurposeItemClick;


    public PurposeFilterAdapter(Context context, int resource, List<PurposeList> items, OnPurposeItemClick onPurposeItemClick) {
        super(context, resource, items);
        this.mContext = context;
        this.items = items;
        this.itemsAll = (ArrayList<PurposeList>) ((ArrayList<PurposeList>) items).clone();
        this.suggestions = new ArrayList<>();

        this.onPurposeItemClick = onPurposeItemClick;

    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_companies_item, parent, false);
        }

        PurposeList purposeList = itemsAll.get(position);
        if (purposeList != null) {
            TextView companyTV = (TextView) view.findViewById(R.id.companyTV);
            if (companyTV != null)
                companyTV.setText(purposeList.getPurposeName());
        }

        return view;
    }


    Filter myFilter = new Filter() {

        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((PurposeList) (resultValue)).getPurposeName();
            return str;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<PurposeList> filteredList = (ArrayList<PurposeList>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (PurposeList c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }

        @Override
        public FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (PurposeList customer : itemsAll) {
                    if (customer.getPurposeName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(customer);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
    };

}
