package com.example.puneetchugh.reportcard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by puneetchugh on 6/28/16.
 */
public class ReportCardClass implements Parcelable{



    public static int DEFAULT_MARKS = 0;
    private String name;
    private int mathsMarks = DEFAULT_MARKS;
    private int englishMarks = DEFAULT_MARKS;
    private int scienceMarks = DEFAULT_MARKS;

    public static final Parcelable.Creator<ReportCardClass> CREATOR = new Parcelable.Creator<ReportCardClass>() {
        public ReportCardClass createFromParcel(Parcel p) {
            return new ReportCardClass(p);
        }

        public ReportCardClass[] newArray(int size) {
            return new ReportCardClass[size];
        }
    };

    public ReportCardClass(Parcel p) {

        this.name = p.readString();
        this.mathsMarks = p.readInt();
        this.englishMarks = p.readInt();
        this.scienceMarks = p.readInt();
    }

    public ReportCardClass(String name, int mathsMarks, int englishMarks, int scienceMarks){
        this(name);
        this.mathsMarks = mathsMarks;
        this.englishMarks = englishMarks;
        this.scienceMarks = scienceMarks;
    }

    public ReportCardClass(String name){
        this.name = name;
    }

    @Override
    public String toString(){

      String returnString = String.format("%s's ReportCard  \n\nMaths  -  %d\n\nEnglish  -  %d\n\nScience  -  %d", name, mathsMarks,englishMarks,scienceMarks);
        return returnString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeInt(mathsMarks);
        dest.writeInt(englishMarks);
        dest.writeInt(scienceMarks);
    }
}
