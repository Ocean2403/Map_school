package com.example.test2;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<SearchData> {
    private List<SearchData> countryListFull;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public SearchAdapter(@NonNull Context context, @NonNull List<SearchData> countryList) {
        super(context,  0, countryList);
        countryListFull = new ArrayList<>(countryList);

    }

    @NonNull
    @Override
    public Filter getFilter() {
        return countryFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.search_row,parent,false
            );
        }
        TextView textViewName = convertView.findViewById(R.id.text_view_name);
        ImageView imageViewFlag = convertView.findViewById(R.id.image_view_flag);

        SearchData countryItem =  getItem(position);
        if(countryItem != null){
            textViewName.setText(countryItem.getSearch());

//                DocumentReference docRef = db.collection("DanhSach").document(countryItem.getToakodau()).collection(countryItem.getTang()).document(countryItem.getMaphong());
//                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                        if (task.isSuccessful()) {
//                            DocumentSnapshot document = task.getResult();
//                            if (document.exists()) {
//                                Log.d(TAG, "DocumentSnapshot data: " + document.get("HinhAnh"));
//                                String   link1= (String) document.get("HinhAnh");
//                                if(link1 != null) {
//                                    Picasso.with(getContext()).load(link1).into(imageViewFlag);
//                                } else {
//                imageViewFlag.setImageResource(countryItem.getHinh());
//            }
//
//                                Log.d(TAG, "helllo" + link1);
//                            } else {
//                                Log.d(TAG, "No such document");
//                            }
//                        } else {
//                            Log.d(TAG, "get failed with ", task.getException());
//                        }
//                    }
//                });
            imageViewFlag.setImageResource(countryItem.getHinh());
        }
        return convertView;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<SearchData> suggestions = new ArrayList<>();

            if(constraint == null || constraint.length() == 0)
            {
                suggestions.addAll(countryListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(SearchData item : countryListFull){
                    if(item.getSearch().toLowerCase().contains(filterPattern)){
                        suggestions.add(item);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence contraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return  ((SearchData) resultValue).getSearch();
        }
    };
}
