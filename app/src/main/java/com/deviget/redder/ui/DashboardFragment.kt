package com.deviget.redder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.deviget.redder.R
import com.deviget.redder.ui.viewmodel.PostViewModel
import com.deviget.redder.ui.viewmodel.PostViewModelFactory
import com.deviget.redder.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var viewModel : PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = PostViewModelFactory.create(requireActivity())

        viewModel.listPosts.observe(viewLifecycleOwner, Observer {
            swipePostLayout.isRefreshing = false
            progressLayout.visibility = GONE
        })

        initView()
    }

    private fun initView() {
        swipePostLayout.setOnRefreshListener {
            swipePostLayout.isRefreshing = true
            viewModel.postListWasRefreshed = true
            viewModel.resetPosts()
        }

        val layoutManager = LinearLayoutManager(requireContext())
        redderPostsRecycler.layoutManager = layoutManager
        redderPostsRecycler.addOnScrollListener(object : PaginationScrollListener(layoutManager){
            override fun loadMoreItems() {
                viewModel.loadMorePosts()
            }

            override val isLastPage: Boolean = viewModel.isLastPage()
            override val isLoading: Boolean = viewModel.postListWasRefreshed

        })

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DashboardFragment()
    }
}