package id.muiz.smconsumerapp.utility

import android.database.Cursor
import id.muiz.smconsumerapp.data.UserFavorit

fun Cursor.toListUserFavorite() : ArrayList<UserFavorit> {
    val userFavoriteList = ArrayList<UserFavorit>()

    apply {
        while (moveToNext()) {
            userFavoriteList.add(
                toUserFavoriteEntity()
            )
        }
    }

    return userFavoriteList
}

fun Cursor.toUserFavoriteEntity() : UserFavorit =
    UserFavorit(
        getInt(getColumnIndexOrThrow("id")),
        getString(getColumnIndexOrThrow("username")),
        getString(getColumnIndexOrThrow("foto"))
    )