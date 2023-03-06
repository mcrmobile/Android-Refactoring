package com.jaguarlandrover.interview.xml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.jaguarlandrover.interview.databinding.ActivityMainBinding
import com.jaguarlandrover.interview.databinding.ItemVehicleBinding
import kotlinx.coroutines.launch

class XmlMainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    val adapter = VehiclesRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.recyclerView.adapter = adapter
        lifecycleScope.launch {
            try {
                adapter.vehicles = DependencyContainer.vehiclesService.getVehicles()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    inner class VehiclesRecyclerViewAdapter :
        RecyclerView.Adapter<VehiclesRecyclerViewAdapter.ViewHolder>() {

        var vehicles: List<Vehicle> = emptyList()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(ItemVehicleBinding.inflate(LayoutInflater.from(parent.context))).apply {
                itemView.setOnClickListener {
                    Toast
                        .makeText(
                            this@XmlMainActivity,
                            vehicles[adapterPosition].model,
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            }
        }

        override fun getItemCount(): Int = vehicles.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.name.text = vehicles[position].model + " " + vehicles[position].model
        }

        inner class ViewHolder(val binding: ItemVehicleBinding) : RecyclerView.ViewHolder(binding.root)
    }
}

