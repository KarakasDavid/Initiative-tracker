package hu.bme.aut.android.initiativetracker.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context

import android.os.Bundle

import android.view.LayoutInflater

import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import hu.bme.aut.android.initiativetracker.R
import hu.bme.aut.android.initiativetracker.data.PlayerCharacter
import hu.bme.aut.android.initiativetracker.databinding.FragmentNewCharacterDialogBinding

class NewCharacterDialogFragment : DialogFragment() {
    interface NewCharacterListener {
        fun onCharacterCreated(newItem: PlayerCharacter)
    }
    private lateinit var listener: NewCharacterListener
    private lateinit var binding: FragmentNewCharacterDialogBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = parentFragment as? NewCharacterListener
            ?: throw RuntimeException("Parent must implement the NewCharacterDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = FragmentNewCharacterDialogBinding.inflate(LayoutInflater.from(context))
        binding.spCategory.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.class_items)
        )
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_character)
            .setView(binding.root)
            .setPositiveButton(R.string.button_ok) { dialogInterface, i ->
                if (isValid()) {
                    listener.onCharacterCreated(getCharacter())
                }
            }
            .setNegativeButton(R.string.button_cancel, null)
            .create()
    }

    private fun isValid() = binding.etName.text.isNotEmpty()

    private fun getCharacter() = PlayerCharacter(
        name = binding.etName.text.toString(),
        race = binding.etRace.text.toString(),
        hp = binding.etHP.text.toString().toIntOrNull() ?: 0,
        ac = binding.etAC.text.toString().toIntOrNull() ?: 0,
        init = binding.etINIT.text.toString().toIntOrNull() ?: 0,
        player_class = PlayerCharacter.Class.getByOrdinal(binding.spCategory.selectedItemPosition)
            ?: PlayerCharacter.Class.ARTIFICER,
        isPicked = binding.cbAlreadyPurchased.isChecked

    )
    companion object {
        const val TAG = "NewCharacterDialogFragment"
    }




}