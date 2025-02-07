package com.example.dummynewsapp.articlelist

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummynewsapp.articlelist.data.Article
import com.example.dummynewsapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ArticleFragment : Fragment(), ArticleAdapter.OnItemClickListener {

    // Create a binding object
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var articleAdapter: ArticleAdapter

    // Create a ViewModel instance
    private val viewModel: ArticleViewModel by lazy {
        ViewModelProvider(this)[ArticleViewModel::class.java]
    }

    // Create a ViewModel instance
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    // Initialize RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize RecyclerView
        articleAdapter = ArticleAdapter(emptyList(), this)
        binding.recyclerView.adapter = articleAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        observeDate()

        binding.spinnerAuthor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedAuthor = parent.getItemAtPosition(position) as String

                // Filter articles based on category, type, author and tag
                viewModel.filterArticles(null,null, selectedAuthor, null)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        binding.spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCategory = parent.getItemAtPosition(position) as String

                // Filter articles based on category, type, author and tag
                viewModel.filterArticles(selectedCategory,null, null, null)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        binding.spinnerArticleType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedType = parent.getItemAtPosition(position) as String

                // Filter articles based on category, type, author and tag
                viewModel.filterArticles(null, selectedType, null, null)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        binding.spinnerTag.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedTag = parent.getItemAtPosition(position) as String

                // Filter articles based on category, type, author and tag
                viewModel.filterArticles(null,null, null, selectedTag)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun observeDate() {
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            if (articles != null) {
                articleAdapter.updateArticles(articles)
            }
        }
    }

    // Destroy the binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
 * Handles the click event on an article item.
 * Navigates to the second fragment with the selected article.
 *
 * @param article The article that was clicked.
 */
    override fun onItemClick(article: Article) {
        //navigate to the second fragment
        val action = ArticleFragmentDirections.actionArticleFragmentToDetailsFragment()
        findNavController().navigate(action)
    }
}