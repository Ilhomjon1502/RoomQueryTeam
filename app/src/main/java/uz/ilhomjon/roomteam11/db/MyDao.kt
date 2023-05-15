package uz.ilhomjon.roomteam11.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.ilhomjon.roomteam11.models.MyContact

@Dao
interface MyDao {
    @Insert
    fun addContact(myContact: MyContact)

    @Query("select * from MyContact")
    fun getAllContacts():List<MyContact>

    @Query("SELECT * FROM MyContact WHERE name LIKE '%' || :searchQuery || '%' OR number LIKE '%' || :searchQuery || '%'")
    fun searchUsers(searchQuery: String): List<MyContact>
}