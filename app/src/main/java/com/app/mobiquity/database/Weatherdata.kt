package com.app.mobiquity.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Weatherdata(val weather: String):Parcelable{
    @PrimaryKey(autoGenerate = true)
    var id: Int=0

    constructor(parcel: Parcel) : this(parcel.readString()!!) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(weather)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Weatherdata> {
        override fun createFromParcel(parcel: Parcel): Weatherdata {
            return Weatherdata(parcel)
        }

        override fun newArray(size: Int): Array<Weatherdata?> {
            return arrayOfNulls(size)
        }
    }
}