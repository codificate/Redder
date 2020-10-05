package com.deviget.redder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deviget.redder.R
import com.deviget.redder.ui.viewmodel.PostViewModel
import com.deviget.redder.ui.viewmodel.PostViewModelFactory
import com.deviget.redder.utils.EndlessScrollListener
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var viewModel: PostViewModel
    private val adapter = PostAdapter(DefaultPostBehaviorReaction)

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

        activity?.let {
            viewModel = PostViewModelFactory.create(it)
        }

        initView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.resetPosts()
        viewModel.listPosts.observe(viewLifecycleOwner, Observer {
            swipePostLayout.isRefreshing = false
            progressLayout.visibility = GONE
            redderPostsRecycler.clearOnScrollListeners()
            adapter.setPosts(it, viewModel.postListWasRefreshed)
            dismissButton.visibility = VISIBLE
            noPostsAvailable.visibility = GONE
            redderPostsRecycler.visibility = VISIBLE
        })
    }

    private fun initView() {
        swipePostLayout.setOnRefreshListener {
            swipePostLayout.isRefreshing = true
            viewModel.postListWasRefreshed = true
            redderPostsRecycler.addOnScrollListener(createScrollListener())
            viewModel.resetPosts()
        }

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        redderPostsRecycler.setHasFixedSize(true)
        redderPostsRecycler.layoutManager = layoutManager
        redderPostsRecycler.adapter = adapter
        redderPostsRecycler.addOnScrollListener(createScrollListener())

        dismissButton.setOnClickListener {
            adapter.dismissAll()
            it.visibility = GONE
            redderPostsRecycler.visibility = GONE
            noPostsAvailable.visibility = VISIBLE
        }
    }

    private fun createScrollListener(): EndlessScrollListener {
        return object :
            EndlessScrollListener(redderPostsRecycler.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.loadMorePosts()
                viewModel.postListWasRefreshed = false
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DashboardFragment()
    }
}