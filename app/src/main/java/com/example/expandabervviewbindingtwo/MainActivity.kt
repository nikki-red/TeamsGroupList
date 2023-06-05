package com.example.expandabervviewbindingtwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expandabervviewbindingtwo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

// class MainActivity that extends AppCompatActivity, which is a base class for activities
class MainActivity : AppCompatActivity() {

    // View binding for the activity
    /*
    --> Declare a private variable _binding of type ActivityMainBinding and initialize it as nullable
    --> The binding property is then declared as a non-null type that retrieves the value of _binding
    --> 'ActivityMainBinding' is an auto-generated class that represents the binding
        between the views defined in the XML layout file for MainActivity and the code
     */
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    // Get reference to the adapter class
    /*
    --> Declare a variable groupList as an ArrayList of Group objects and initialize it as an empty list
    --> It will be assigned an instance of the RvAdapter class later.
     */
    private var groupList = ArrayList<Group>()
    private lateinit var rvAdapter: RvAdapter

    // Reference to bottom navigation
    /*
    --> Declares a bottomNav variable of type BottomNavigationView
     */
    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Calls the the parent class implementation of onCreate()
        super.onCreate(savedInstanceState)
        // Inflates the XML layout file associated with MainActivity using the ActivityMainBinding class
        _binding = ActivityMainBinding.inflate(layoutInflater)
        // Sets the root view of the activity to the inflated layout
        setContentView(binding.root)
        // Loads the HomeFragment into the container view, replacing any existing fragment
        loadFragment(HomeFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.message -> {
                    loadFragment(ChatFragment())
                    true
                }
                R.id.settings -> {
                    loadFragment(SettingsFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }
        // Define layout manager for the Recycler view
        binding.groupList.layoutManager = LinearLayoutManager(this)

        // Attach adapter to the recycler view
        rvAdapter = RvAdapter(groupList)
        binding.groupList.adapter = rvAdapter

        // Create new objects and adding the respective row data
        val group1 = Group("Group 1", "Desc 1", false)
        val group2 = Group("Group 2", "Desc 2", false)
        val group3 = Group("Group 3", "Desc 3", false)
        val group4 = Group("Group 4", "Desc 4", false)
        val group5 = Group("Group 5", "Desc 5", false)
        val group6 = Group("Group 6", "Desc 6", false)
        val group7 = Group("Group 7", "Desc 7", false)

        // Add items to list
        groupList.add(group1)
        groupList.add(group2)
        groupList.add(group3)
        groupList.add(group4)
        groupList.add(group5)
        groupList.add(group6)
        groupList.add(group7)

        rvAdapter.notifyDataSetChanged()

    }

    // On destroy of view make the binding reference to null
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}

/*
Points to be noted:
1. What is Activity?
--> Represents a single screen with UI
--> Acts as a container for UI elements
--> Defined in AndroidManifest.xml file

2. What is lateinit modifier?
--> Informs that the variable has been declared but not yet initialised
--> Will be initialised later
*/
