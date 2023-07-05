package hu.bme.aut.android.initiativetracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.initiativetracker.R
import hu.bme.aut.android.initiativetracker.data.PlayerCharacter
import hu.bme.aut.android.initiativetracker.databinding.CharacterListBinding

class CharacterAdapter(private val listener: CharacterClickListener) :
    RecyclerView.Adapter<CharacterAdapter.BrowsingViewHolder>() {

    private lateinit var adapter: InitiativeAdapter

    private val items = mutableListOf<PlayerCharacter>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BrowsingViewHolder(
        CharacterListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BrowsingViewHolder, position: Int) {
        val character = items[position]

        holder.binding.ivIcon.setImageResource(getImageResource(character.player_class))
        holder.binding.cbIsPicked.isChecked = character.isPicked
        holder.binding.tvName.text = character.name
        holder.binding.tvDescription.text = character.race
        holder.binding.tvCategory.text = character.player_class.name
        holder.binding.tvPrice.text = "${character.hp} HP"
        holder.binding.tvPrice.text = "${character.ac} AC"
        holder.binding.tvPrice.text = "${character.init} Init"

        holder.binding.cbIsPicked.setOnCheckedChangeListener { buttonView, isChecked ->
            character.isPicked = isChecked
            listener.onItemChanged(character)
        }
        holder.binding.ibRemove.setOnClickListener{

            items.remove(character)
            listener.onItemDeleted(character)
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
        fun onItemChanged(item: PlayerCharacter)
        fun onItemDeleted(item: PlayerCharacter)
    }

    inner class BrowsingViewHolder(val binding: CharacterListBinding) : RecyclerView.ViewHolder(binding.root)
}