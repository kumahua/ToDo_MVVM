package com.example.todo.fragment.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.data.models.TodoData
import com.example.todo.data.viewmodel.TodoViewModel
import com.example.todo.databinding.FragmentAddBinding
import com.example.todo.fragment.SharedViewModel

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val mToDoViewModel: TodoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.prioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.add_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.menu_add) {
                    insertDataToDb()
                } else if (menuItem.itemId == android.R.id.home) {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun insertDataToDb() {

        val mTitle = binding.titleEt.text.toString()
        val mPriority = binding.prioritiesSpinner.selectedItem.toString()
        val mDescription = binding.descriptionEt.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if (validation) {

            val newData = TodoData(
                id = 0,
                title = mTitle,
                priority = mSharedViewModel.parsePriority(mPriority),
                description = mDescription
            )
            mToDoViewModel.insertData(newData)
            Toast.makeText(context, "Successfully added!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {

            Toast.makeText(context, "Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}