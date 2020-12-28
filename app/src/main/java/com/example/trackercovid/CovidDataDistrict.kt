package com.example.trackercovid

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class CovidDataDistrict() :Serializable, Parcelable {
    var id:String? = null
    var name:String? = null
    var confirmed:String? = null
    var recovered:String? = null
    var deaths:String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
        confirmed = parcel.readString()
        recovered = parcel.readString()
        deaths = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(confirmed)
        parcel.writeString(recovered)
        parcel.writeString(deaths)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CovidDataDistrict> {
        override fun createFromParcel(parcel: Parcel): CovidDataDistrict {
            return CovidDataDistrict(parcel)
        }

        override fun newArray(size: Int): Array<CovidDataDistrict?> {
            return arrayOfNulls(size)
        }
    }

}
