package uz.ilhomjon.roomteam11.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyContact {

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
    var name:String? = null
    var number:String? = null
}