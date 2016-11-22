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
import com.visitorapp.bloominfotech.interfaces.OnCompanyItemClick;
import com.visitorapp.bloominfotech.models.companies.CompanyList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateekarora on 22/11/16.
 */

public class CompanyFilterAdapter extends ArrayAdapter<CompanyList> implements Filterable {

    List<CompanyList> items;
    List<CompanyList> itemsAll;
    List<CompanyList> suggestions;
    Context mContext;
    OnCompanyItemClick onCompanyItemClick;



    public CompanyFilterAdapter(Context context, int resource, List<CompanyList> items,
                          OnCompanyItemClick onCompanyItemClick) {
        super(context, resource, items);
        this.mContext = context;
        this.items = items;
        this.itemsAll = (ArrayList<CompanyList>) ((ArrayList<CompanyList>) items).clone();
        this.suggestions = new ArrayList<>();
        this.onCompanyItemClick = onCompanyItemClick;
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

        CompanyList companyList = itemsAll.get(position);
        if (companyList != null) {
            TextView companyTV = (TextView) view.findViewById(R.id.companyTV);
            if (companyTV != null)
                companyTV.setText(companyList.getCompanyName());
        }

        return view;
    }


    Filter myFilter = new Filter() {

        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((CompanyList) (resultValue)).getCompanyName();
            return str;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<CompanyList> filteredList = (ArrayList<CompanyList>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (CompanyList c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }

        @Override
        public FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (CompanyList customer : itemsAll) {
                    if (customer.getCompanyName().toLowerCase().contains(constraint.toString().toLowerCase())) {
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