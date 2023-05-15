package uz.ilhomjon.roomteam11.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ilhomjon.roomteam11.databinding.ItemRvBinding

class RvAdapter(val list:ArrayList<String>):RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){

        fun onBind(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind()
    }
}