package com.example.breakingbad

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbad.data.CharacterEntity

import com.example.breakingbad.databinding.FragmentMainBinding

class MainFragment : Fragment(),
    CharacterListAdapter.ListItemListener{

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: CharacterListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)


        binding = FragmentMainBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.character.observe(viewLifecycleOwner, Observer {

            adapter = CharacterListAdapter(requireContext(),it, this@MainFragment)
            binding.recyclerView.adapter = adapter
        })
        return binding.root

    }

    override fun onItemClick(character: CharacterEntity) {

        val action = MainFragmentDirections.actionMainFragmentToEditorFragment(character)
        findNavController().navigate(action)

    }

}