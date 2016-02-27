package com.test.oddle.weatherapp.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.test.oddle.weatherapp.APICalls.CityList;
import com.test.oddle.weatherapp.Model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Achyut on 2/24/2016.
 */
public class SuggestionAdapter extends ArrayAdapter<String> {

    private List<String> suggestions;

    public List<City> getCity_suggestion() {
        return city_suggestion;
    }

    List<City> city_suggestion = null;
    public SuggestionAdapter(Context context, String cityFilter) {
        super(context, android.R.layout.simple_dropdown_item_1line);
        suggestions = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return suggestions.size();
    }

    @Override
    public String getItem(int position) {
        return suggestions.get(position);
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint != null){

                    try {
                        city_suggestion = new CityList(constraint.toString()).execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    suggestions.clear();
                    for (int i=0;i<city_suggestion.size();i++){
                        suggestions.add(city_suggestion.get(i).getCity());
                    }
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count >0){
                    notifyDataSetChanged();
                }else{
                    notifyDataSetInvalidated();
                }

            }
        };
        return filter;
    }
}
