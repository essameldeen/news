package com.example.news_presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.common_utils.Navigator
import com.example.news_presentation.databinding.ActivityNewsBinding
import com.vision.andorid.news_presentation.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityNewsBinding
    private val newsViewModel: NewsViewModel by viewModels()
    private val newsAdapter = NewsAdapter()

    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, NewsActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        initView()
        initObservables()
    }

    private fun initView() {
        _binding.rvArticles.adapter = newsAdapter
    }

    private fun initObservables() {
        lifecycleScope.launchWhenStarted {
            newsViewModel.newsArticles.collect { newState ->
                if (newState.isLoading) {

                    showProgress()
                }
                if (newState.error.isNotEmpty()) {
                    hideProgress()
                    showError(newState.error)
                }
                hideProgress()
                newState.data?.let {
                    newsAdapter.setData(it)
                }
            }

        }
    }

    private fun showError(error: String) {
        Toast.makeText(this@NewsActivity, error, Toast.LENGTH_LONG).show()
    }

    private fun showProgress() {
        _binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        _binding.progressBar.visibility = View.GONE
    }
}

object GoToNewsActivity : Navigator {
    override fun navigate(activity: Activity) {
        NewsActivity.launchActivity(activity)
    }

}