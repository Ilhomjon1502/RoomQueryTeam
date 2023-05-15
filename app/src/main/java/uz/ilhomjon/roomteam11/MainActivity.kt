package uz.ilhomjon.roomteam11

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import uz.ilhomjon.roomteam11.adapters.RvAdapter
import uz.ilhomjon.roomteam11.databinding.ActivityMainBinding
import uz.ilhomjon.roomteam11.databinding.ItemDialogBinding
import uz.ilhomjon.roomteam11.db.MyDbHelper
import uz.ilhomjon.roomteam11.models.MyContact

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var myDbHelper: MyDbHelper
    lateinit var rvAdapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myDbHelper = MyDbHelper.newInstance(this)
        rvAdapter = RvAdapter(myDbHelper.contactDao().getAllContacts() as ArrayList<MyContact>)
        binding.apply {
            rv.adapter = rvAdapter
            btnAdd.setOnClickListener {
                val dialog = AlertDialog.Builder(this@MainActivity).create()
                val itemDialog = ItemDialogBinding.inflate(layoutInflater)
                dialog.setView(itemDialog.root)

                itemDialog.btnSave.setOnClickListener {
                    val myContact = MyContact(
                        itemDialog.name.text.toString(),
                        itemDialog.number.text.toString()
                    )
                    myDbHelper.contactDao().addContact(myContact)
                    rvAdapter.list.add(myContact)
                    rvAdapter.notifyItemChanged(rvAdapter.list.size-1)
                    Toast.makeText(this@MainActivity, "Saved", Toast.LENGTH_SHORT).show()
                    dialog.cancel()
                }

                dialog.show()
            }

            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // Qidiruv tugadi
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // Qidiruv so'rovi o'zgarganda amalga oshiriladigan kodni yozing
                    val searchQuery = newText ?: ""
                    // Ma'lumotlar bazasiga so'rovni yuborish yoki qidiruvni boshlash
                    // ...
                    val list = myDbHelper.contactDao().searchUsers(searchQuery)
                    rvAdapter.list.clear()
                    rvAdapter.list.addAll(list)
                    rvAdapter.notifyDataSetChanged()
                    return true
                }
            })

        }
    }
}