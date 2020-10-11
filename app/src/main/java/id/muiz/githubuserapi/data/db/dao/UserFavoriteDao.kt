package id.muiz.githubuserapi.data.db.dao

import android.database.Cursor
import androidx.room.*
import id.muiz.githubuserapi.data.db.entity.UserFavorite

@Dao
interface UserFavoriteDao {

    @Query("SELECT * FROM favorit_table")
    suspend fun fetchAllUsers() : List<UserFavorite>

    @Query("SELECT * FROM favorit_table WHERE username = :userName")
    suspend fun getFavByUsername(userName: String) : List<UserFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserToFavDB(user: UserFavorite)

    @Delete
    suspend fun deleteUserDb(user: UserFavorite)

    /**
     * Cursor for content provider
     */
    @Query("SELECT * FROM favorit_table")
    fun cursorGetAllUserFavorite() : Cursor

}