package com.android.androidexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.androidexercise.adapter.ListViewAdapter
import com.android.androidexercise.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var mAdapter: ListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = ""
        swipeRefresh.isRefreshing = true

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)

        callListData()

        //pulling latest data set from api
        swipeRefresh.setOnRefreshListener {
            callListData()
        }

        viewModel.progressBar.observe(this, Observer {
            if (it) {
                swipeRefresh.isRefreshing = false
            }
        })

        viewModel.listData.observe(this, Observer {
            supportActionBar?.title = it.title
            mAdapter.setDataList(it.rows)
            swipeRefresh.isRefreshing = false
        })

        //set adapter
        mAdapter = ListViewAdapter(this)
        recyclerView.adapter = mAdapter

    }


    fun callListData() {
        viewModel.fetchListData()
    }


}
