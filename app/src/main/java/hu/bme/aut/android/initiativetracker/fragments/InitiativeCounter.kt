package hu.bme.aut.android.initiativetracker.fragments

import android.R.attr.data
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.initiativetracker.R
import hu.bme.aut.android.initiativetracker.adapter.InitiativeAdapter
import hu.bme.aut.android.initiativetracker.data.CharacterDatabase
import hu.bme.aut.android.initiativetracker.data.PlayerCharacter
import hu.bme.aut.android.initiativetracker.databinding.FragmentInitiativeCounterBinding
import kotlin.concurrent.thread


class InitiativeCounter : Fragment(), InitiativeAdapter.CharacterClickListener, NewCharacterDialogFragment.NewCharacterListener {
    private lateinit var binding: FragmentInitiativeCounterBinding
    private lateinit var database: CharacterDatabase
    private lateinit var adapter: InitiativeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInitiativeCounterBinding.inflate(inflater, container, false)

        database = CharacterDatabase.getDatabase(requireContext(). applicationContext)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        binding.fab.setOnClickListener{
            OnSorting()
        }


        initRecyclerView()
        return binding.root
    }
    private fun initRecyclerView() {
        adapter = InitiativeAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(context)
        binding.rvMain.adapter = adapter
        loadItemsInBackground()
    }

    override fun onItemChanged(item: PlayerCharacter, elet: Int) {
        thread {
            item.hp=item.hp + elet
            database.characterDao().update(item)
            Log.d("ListFragment", "Character update was successful")
        }
    }

    override fun onRolled(item: PlayerCharacter, szam: Int) {
        thread {
            item.ac=item.init+szam
            val items = database.characterDao().getPicked()
            database.characterDao().update(item)
            Log.d("ListFragment", "Character update was successful")


        }
    }

    override fun onItemMoved(item: PlayerCharacter) {
        thread {
            onCharacterCreated(item)
            database.characterDao().deleteItem(item)

            Log.d("ListFragment", "Character movement was successful")
        }
    }

    override fun onItemDeleted(item: PlayerCharacter) {
        thread {
            database.characterDao().deleteItem(item)
            Log.d("ListFragment", "Character delete was successful")
        }
    }
    override fun onItemPic(item: PlayerCharacter) {
        thread {
            item.isPicked=false
            database.characterDao().update(item)
            Log.d("ListFragment", "Character picked change was successful")
        }
    }



    private fun loadItemsInBackground() {
        thread {
            val items = database.characterDao().getPicked()
            activity?.runOnUiThread {
                adapter.update(items)
            }
        }
    }
    private fun OnSorting() {
        thread {
            val items = database.characterDao().getPicked()
            activity?.runOnUiThread {
                adapter.sorting(items)
            }
        }
    }

    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
        activity?.runOnUiThread(action)
    }

    override fun onCharacterCreated(newItem: PlayerCharacter) {
        thread {
            val insertId = database.characterDao().insert(newItem)
            newItem.id = insertId
            runOnUiThread {
                adapter.addItem(newItem)
            }
        }
    }
}