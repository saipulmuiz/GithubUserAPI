package id.muiz.githubuserapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FollowingItems(
    var id: Int = 0,
    var username: String? = null,
    var name: String? = null,
    var foto: String? = null,
    var company: String? = null,
    var location: String? = null,
    var repository: Int = 0,
    var followers: Int = 0,
    var following: Int = 0
) : Parcelable