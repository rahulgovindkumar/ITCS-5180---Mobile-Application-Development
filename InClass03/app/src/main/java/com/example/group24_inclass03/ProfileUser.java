/*Class 03
        Grouping1-Group27 Grouping2-24
        Name: Rahul Govindkumar



        */

package com.example.group24_inclass03;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfileUser implements Parcelable {

    String name;
    String email;
    String id;
    String department;

    public ProfileUser(String name, String email, String id, String department) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.department = department;
    }

    public ProfileUser() {
    }

    protected ProfileUser(Parcel in) {
        name = in.readString();
        email = in.readString();
        id = in.readString();
        department = in.readString();
    }

    public static final Creator<ProfileUser> CREATOR = new Creator<ProfileUser>() {
        @Override
        public ProfileUser createFromParcel(Parcel in) {
            return new ProfileUser(in);
        }

        @Override
        public ProfileUser[] newArray(int size) {
            return new ProfileUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(this.name);
        parcel.writeString(this.email);
        parcel.writeString(this.id);
        parcel.writeString(this.department);

    }
}
