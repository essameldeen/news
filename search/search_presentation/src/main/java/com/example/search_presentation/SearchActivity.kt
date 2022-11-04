package com.example.search_presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.common_utils.Constant
import com.example.common_utils.Navigator
import com.example.search_presentation.databinding.ActivitySearchBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private lateinit var _binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val newsAdapter = NewsAdapter()

    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, SearchActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        initViews()
        setObservables()
    }


    private fun initViews() {


        _binding.rvSearch.adapter = newsAdapter
        _binding.searchTitle.doAfterTextChanged {
            val map = mutableMapOf<String, String>()
            map[Constants.API_KEY] = Constant.API_KEY
            map[Constants.QUERY] = it.toString()
            searchViewModel.searchForArticles(map)
        }

        _binding.ivRange.setOnClickListener {
            showDataPicker()
        }
    }

    private fun setObservables() {

        lifecycleScope.launchWhenStarted {
            searchViewModel.searchResult.collectLatest { state ->
                if (state.isLoading) {
                    showProgress()
                }
                if (state.error.isNotBlank()) {
                    hideProgress()
                    showErrorMessage(state.error)
                }
                if (state.data?.isNotEmpty() == true) {
                    hideProgress()
                    newsAdapter.setData(state.data)
                }

            }
        }

    }

    private fun showDataPicker() {
        val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
        datePicker.show(this.supportFragmentManager, "range picker")

        datePicker.addOnPositiveButtonClickListener {
            val start = changeDateFormat(it.first)
            val end = changeDateFormat(it.second)

            val map = mutableMapOf<String, String>()
            map[Constants.API_KEY] = Constant.API_KEY
            map[Constants.QUERY] = _binding.searchTitle.text.toString()
            map[Constants.START_DATE] = start
            map[Constants.END_DATE] = end

            searchViewModel.getSearchArticles(map)
        }
    }

    private fun changeDateFormat(long: Long?): String {
        return try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            simpleDateFormat.format(long)
        } catch (e: Exception) {
            ""
        }
    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(this@SearchActivity, error, Toast.LENGTH_LONG).show()
    }

    private fun showProgress() {
        _binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        _binding.progressBar.visibility = View.GONE
    }
}


object GoToSearchActivity : Navigator {
    override fun navigate(activity: Activity) {
        SearchActivity.launchActivity(activity)
    }

}