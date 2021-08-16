package com.example.myapplication.List

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.RecyclerList
import com.example.myapplication.viewmodel.ListActivityViewModel


/**
 * Fragment
 */
class RecyclerListFragment : Fragment() {


    private lateinit var recyclerAdapter: RecyclerViewAdapter

    /**
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler_list, container, false) //将xml加载成view
        initViewModel(view)
        initViewModel()
        return view

    }

    /**
     *
     */
    private fun initViewModel(view: View) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        //添加分割线
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerAdapter


    }

    //获取api数据
    private fun initViewModel() {

        val viewModel = ViewModelProvider(this).get(ListActivityViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(this, Observer<RecyclerList> {
            if (it != null) {
                recyclerAdapter.setUpdatedData(it.items)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    /**
     * 在类中只能存在一个，伴生对象
     */
    companion object {
        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }
}