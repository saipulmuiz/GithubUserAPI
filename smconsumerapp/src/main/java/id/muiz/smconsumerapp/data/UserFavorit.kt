package id.muiz.smconsumerapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserFavorit(
    val id: Int?,
    val username: String?,
    val foto: String?
) : Parcelable