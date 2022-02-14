
package com.example.group18_hw02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UsersListAdapter extends ArrayAdapter<DataServices.User> {

    public UsersListAdapter(@NonNull Context context, int resource, @NonNull List<DataServices.User> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_listview, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.textView_Name_user_listview);
            viewHolder.state = convertView.findViewById(R.id.textView_State_user_listview);
            viewHolder.age = convertView.findViewById(R.id.textView_Age_user_listview);
            viewHolder.group = convertView.findViewById(R.id.textView_Group_user_listview);
            viewHolder.profileImage = convertView.findViewById(R.id.imageView_Profile_user_listview);
            convertView.setTag(viewHolder);
        }

        DataServices.User user = getItem(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();


        if (user.gender == "Male") {
            viewHolder.profileImage.setBackgroundResource(R.drawable.avatar_male);
        } else  {
            viewHolder.profileImage.setBackgroundResource(R.drawable.avatar_female);
        }

        viewHolder.name.setText(user.name);
        viewHolder.state.setText(user.state);
        viewHolder.age.setText(user.age + " Years Old");
        viewHolder.group.setText(user.group);

        return convertView;
    }

    private static class ViewHolder {
        TextView name, state, age, group;
        ImageView profileImage;
    }
}
