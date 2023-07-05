package hu.bme.aut.android.initiativetracker

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import hu.bme.aut.android.initiativetracker.adapter.CharacterAdapter
import hu.bme.aut.android.initiativetracker.data.CharacterDatabase
import hu.bme.aut.android.initiativetracker.data.PlayerCharacter

import hu.bme.aut.android.initiativetracker.databinding.FragmentListBinding
import hu.bme.aut.android.initiativetracker.fragments.NewCharacterDialogFragment
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}