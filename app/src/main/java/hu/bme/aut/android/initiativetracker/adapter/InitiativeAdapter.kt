package hu.bme.aut.android.initiativetracker.adapter

import android.R.attr.data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.initiativetracker.R
import hu.bme.aut.android.initiativetracker.data.PlayerCharacter
import hu.bme.aut.android.initiativetracker.databinding.InitiativeListBinding
import java.util.*


class InitiativeAdapter(private val listener: CharacterClickListener) :
    RecyclerView.Adapter<InitiativeAdapter.BrowsingViewHolder>() {

    private val items = mutableListOf<PlayerCharacter>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BrowsingViewHolder(
        InitiativeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: BrowsingViewHolder, position: Int) {

        //items.sortByDescending { it.init }
        val character = items[position]


            holder.binding.ibIcon.setImageResource(getImageResource(character.player_class))
            holder.binding.tvName.text = character.name
            holder.binding.tvDescription.text = character.race
            holder.binding.tvCategory.text = character.player_class.name
            holder.binding.tvHP.text = "${character.hp} HP"
            holder.binding.tvInitiative.text="Init value:"
            holder.binding.tvInitValue.text="${character.ac}"

            holder.binding.ibAddDeduct.setOnClickListener {
                var elet = holder.binding.etValue.text.toString().toIntOrNull() ?: 0!!
                holder.binding.tvHP.text ="${character.hp + elet} HP"
                listener.onItemChanged(character,elet)
            }
            holder.binding.ibRoll.setOnClickListener {

                var szam =(1..20).random()
                holder.binding.tvInitValue.text = "${character.init + szam }"
                    listener.onRolled(character,szam)
            }
            holder.binding.ibMove.setOnClickListener {

                items.remove(character)
                listener.onItemMoved(character)
                notifyDataSetChanged()
            }
            holder.binding.ibIcon.setOnClickListener {
                items.remove(character)
                listener.onItemPic(character)
                notifyDataSetChanged()
            }

    }

    fun addItem(item: PlayerCharacter) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(characters: List<PlayerCharacter>) {
        items.clear()
        items.addAll(characters)

        notifyDataSetChanged()
    }

    fun sorting(characters: List<PlayerCharacter>) {
        items.sortByDescending { it.ac }
        notifyDataSetChanged()
    }


    @DrawableRes()
    private fun getImageResource(player_class: PlayerCharacter.Class): Int {
        return when (player_class) {
            PlayerCharacter.Class.ARTIFICER -> R.drawable.artificer_removebg
            PlayerCharacter.Class.BARBARIAN -> R.drawable.barbarian_removebg
            PlayerCharacter.Class.BARD -> R.drawable.bard_removebg
            PlayerCharacter.Class.CLERIC -> R.drawable.cleric_removebg
            PlayerCharacter.Class.DRUID -> R.drawable.druid_removebg
            PlayerCharacter.Class.FIGHTER -> R.drawable.fighter_removebg
            PlayerCharacter.Class.MONK -> R.drawable.monk_removebg
            PlayerCharacter.Class.PALADIN -> R.drawable.paladin_removebg
            PlayerCharacter.Class.RANGER -> R.drawable.ranger_removebg
            PlayerCharacter.Class.ROGUE -> R.drawable.rogue_removebg
            PlayerCharacter.Class.SORCERER -> R.drawable.sorcerer_removebg
            PlayerCharacter.Class.WARLOCK -> R.drawable.warlock_removebg
            PlayerCharacter.Class.WIZARD -> R.drawable.wizard_removebg
        }
    }

    override fun getItemCount(): Int = items.size

    interface CharacterClickListener {
        fun onItemChanged(item: PlayerCharacter,elet: Int)
        fun onItemDeleted(item: PlayerCharacter)
        fun onRolled(item: PlayerCharacter, szam: Int)
        fun onItemMoved(item: PlayerCharacter)
        fun onItemPic(item: PlayerCharacter)
    }

    inner class BrowsingViewHolder(val binding: InitiativeListBinding) : RecyclerView.ViewHolder(binding.root)
}