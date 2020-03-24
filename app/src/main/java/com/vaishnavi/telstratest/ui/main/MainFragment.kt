package com.vaishnavi.telstratest.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vaishnavi.telstratest.R
import com.vaishnavi.telstratest.databinding.MainFragmentBinding
import com.vaishnavi.telstratest.model.Result
import kotlinx.android.synthetic.main.main_activity.*

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

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getDataFromRepository(activity!!.applicationContext).observe(viewLifecycleOwner,
            Observer<Result> { res ->
                binding.swipe.setRefreshing(false)//set adapter to recycler view
                binding.recyclerView.adapter = MainAdapter(activity!!.applicationContext, res.rows)
            })

        //set up recycler view
        val recyclerView : RecyclerView = binding.recyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}
