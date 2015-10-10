package com.example.saurabh.smartattendanceapplication;


import android.os.Parcel;
import android.os.Parcelable;

public class StudentModel implements Parcelable {
    public String roll_no;
    public boolean present;
    public String name;

    public StudentModel(String roll_no,String name) {
        this.roll_no = roll_no;
        this.name=name;
        present = false;
    }

    protected StudentModel(Parcel in) {
        roll_no = in.readString();
        present = in.readByte() != 0;
        name = in.readString();
    }

    public static final Creator<StudentModel> CREATOR = new Creator<StudentModel>() {
        @Override
        public StudentModel createFromParcel(Parcel in) {
            return new StudentModel(in);
        }

        @Override
        public StudentModel[] newArray(int size) {
            return new StudentModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(roll_no);
        dest.writeByte((byte) (present ? 1 : 0));
        dest.writeString(name);
    }
}
