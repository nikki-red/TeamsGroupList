package com.example.expandabervviewbindingtwo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expandabervviewbindingtwo.databinding.SingleItemBinding

/*
RvAdapter class extends RecycleView.Adapter<RvAdapter.ViewHolder>
--> Takes group list (list of group objects) as parameter
--> Responsible for binding the group list items to view holder and displaying them in recyclerview
 */
class RvAdapter(private var groupList: List<Group>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    /*
    ViewHolder extends RecyclerView.ViewHolder
    --> Holds the views defined in the single_item.xml layout
    --> Takes the view argument
        -> Binding parameter is an instance of SingleItemBinding generated based on single_item.cml layout
     */
    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    /*
    Fxn is called when a new ViewHolder needs to be created
    --> Inflates the SingleItemBinding by using the LayoutInflater to inflate the single_item.xml layout
        then creates a new ViewHolder by passing the inflated binding object to it
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    /*
    Fxn is called when a ViewHolder needs to be bound with data at a specific position
    --> Binds the items with each item of the list which will be shown in rv
    --> Retrieves the corresponding item from the group list based on the position
        then calls the bind fxn of view holder and passes the item to it
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(groupList[position]){
                // Set name of the group from the list
                binding.groupName.text = this.name
                // Set description to the text
                /*
                Since this is inside 'expandedView', its visibility will be GONE initially
                Click on the item will make the visibility of the 'expandedView' VISIBLE
                 */
                binding.groupDescription.text = this.description
                // Check if boolean property "extend" is true or false
                // If it is true make the "extendedView" VISIBLE else GONE
                binding.expandedView.visibility = if (this.expand) View.VISIBLE else View.GONE
                // On clicking, revert the boolean 'expand' value
                // Notify the associated view that the dataset has been changed and needs to be updated
                binding.cardLayout.setOnClickListener {
                    this.expand = !this.expand
                    notifyDataSetChanged()
                }
            }
        }
    }
    /*
    Fxn returns the total no of items in the  group list
    --> represents the no of parent items in the RecyclerView
     */
    override fun getItemCount(): Int {
        return groupList.size
    }
}

/*
Points to be noted:

1. What is an Adapter?
--> Class that acts as a bridge between the data source and view
--> Provides data to the view and defines how the data should be displayed

2. What is view holder?
--> Design pattern used with an adapter to improve efficiency and performance of rv
--> Is an object that holds references to the views within each item of rv
--> Acts as an container for individual views
--> By using view holder, rv can reuse the views as the user scrolls,
    rather than inflating new views for each new item

3. What is inflating?
--> Refers to the process of creating a view object from an XML layout file
--> XML layout files alone do not represent the actual views that users see on the screen
    In order to make them visible and intractable, they need to be inflated.

4. Why GONE and not INVISIBLE?
--> Invisible will still make the empty space visible (though no text), hence, unwanted space is wasted
 */