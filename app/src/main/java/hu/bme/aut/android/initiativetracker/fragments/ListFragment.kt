package hu.bme.aut.android.initiativetracker.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.initiativetracker.adapter.CharacterAdapter
import hu.bme.aut.android.initiativetracker.data.CharacterDatabase
import hu.bme.aut.android.initiativetracker.data.PlayerCharacter
import hu.bme.aut.android.initiativetracker.databinding.FragmentListBinding
import kotlin.concurrent.thread


class ListFragment : Fragment(), CharacterAdapter.CharacterClickListener, NewCharacterDialogFragment.NewCharacterListener {
    private lateinit var binding: FragmentListBinding
    private lateinit var database: CharacterDatabase
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        database = CharacterDatabase.getDatabase(requireContext(). applicationContext)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        binding.fab.setOnClickListener{
            NewCharacterDialogFragment().show(
                childFragmentManager/*(supportFragmentManager) (childFragmentManager) */,
                NewCharacterDialogFragment.TAG
            )
        }


        initRecyclerView()
        return binding.root
    }
    private fun initRecyclerView() {
        adapter = CharacterAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(context)
        binding.rvMain.adapter = adapter
        loadItemsInBackground()
    }

    override fun onItemChanged(item: PlayerCharacter) {
        thread {
            database.characterDao().update(item)
            Log.d("ListFragment", "Character update was successful")
        }
    }

    override fun onItemDeleted(item: PlayerCharacter) {
        thread {
            database.characterDao().deleteItem(item)
            Log.d("ListFragment", "Character delete was successful")
        }
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.characterDao().getAll()
            activity?.runOnUiThread {
                adapter.update(items)
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