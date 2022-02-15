
package com.example.itunesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppAdapter extends ArrayAdapter<DataServices.App> {
    public AppAdapter(@NonNull Context context, int resource, @NonNull List<DataServices.App> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.top_paid_app_list, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.appName = convertView.findViewById(R.id.textView_AppName_AppList);
            viewHolder.artistName = convertView.findViewById(R.id.textView_ArtistName_AppList);
            viewHolder.releaseDate = convertView.findViewById(R.id.textView_ReleaseDate_AppList);
            convertView.setTag(viewHolder);
        }

        DataServices.App appDetails = getItem(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.appName.setText(appDetails.name);
        viewHolder.artistName.setText(appDetails.artistName);
        viewHolder.releaseDate.setText(appDetails.releaseDate);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iListenerAppAdapter mListener;
                Context context = getContext();
                if (context instanceof iListenerAppAdapter) {
                    mListener = (iListenerAppAdapter) context;
                    mListener.showAppDetailsFragment(appDetails);
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView appName, artistName, releaseDate;
    }

    public interface iListenerAppAdapter {
        void showAppDetailsFragment(DataServices.App appDetails);
    }
}
