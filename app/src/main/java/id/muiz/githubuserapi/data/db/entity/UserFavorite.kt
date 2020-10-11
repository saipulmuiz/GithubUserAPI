package id.muiz.githubuserapi.data.db.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorit_table")
data class UserFavorite(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "foto") val foto: String?,
    @ColumnInfo(name = "following_url") val followingUrl: String?,
    @ColumnInfo(name = "company") val company: String?,
    @ColumnInfo(name = "repository") val repository: Int?,
    @ColumnInfo(name = "followers_url") val followersUrl: String?,
    @ColumnInfo(name = "followers") val followers: Int?,
    @ColumnInfo(name = "following") val following: Int?,
    @ColumnInfo(name = "location") val location: String?
) : Parcelable
