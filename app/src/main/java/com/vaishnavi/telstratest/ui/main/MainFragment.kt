package com.vaishnavi.telstratest.ui.main

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.vaishnavi.telstratest.NetworkConnection
import com.vaishnavi.telstratest.R
import com.vaishnavi.telstratest.databinding.MainFragmentBinding
import com.vaishnavi.telstratest.model.Result

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //set up view model
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //check if network is available
        when (NetworkConnection().checkNetworkAvailability(context)) {
            true -> fetchData() //get data from server
            false -> getCachedData() // else get cached data
        }

        //set up recycler view
        val recyclerView : RecyclerView = binding.recyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //set up refresh listener
        binding.swipe.setOnRefreshListener {
            val handler = Handler()
            handler.postDelayed({
                if (binding.swipe.isRefreshing) {
                    binding.swipe.isRefreshing = false
                    fetchData()
                }
            }, 1000)
        }
    }

    private fun fetchData(){
        viewModel.getDataFromServer(context!!).observe(viewLifecycleOwner,
            Observer { res ->
                if (res.rows.isNotEmpty()) {
                    // stop refreshing
                    binding.swipe.isRefreshing = false

                    //set adapter to recycler view
                    binding.recyclerView.adapter =
                        MainAdapter(activity!!.applicationContext, res.rows)

                    //set the title
                    Result(res.title, res.rows)
                }else{
                    showSnackBar() //show error message
                }
            })
    }

    private fun showSnackBar(){
        //to show error message in snackbar
        Snackbar.make(
            binding.recyclerView,
            getString(R.string.no_connection),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun getCachedData(){
        //stop refreshing
        binding.swipe.isRefreshing = false

        //get cached data from room
        viewModel.getDataFromDb(context!!).observe(viewLifecycleOwner,
            Observer { res ->
                if (res.isNotEmpty()) {
                    binding.swipe.isRefreshing = false

                    //set adapter to recycler view
                    binding.recyclerView.adapter =
                        MainAdapter(activity!!.applicationContext, res)
                }
            })
        showSnackBar()
    }
}
