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

        /**
         * Sets up the item selected listener for the spinners to filter articles
         * based on the selected author, category, type, and tag.
         */
        val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedAuthor = binding.spinnerAuthor.selectedItem as String
                val selectedCategory = binding.spinnerCategory.selectedItem as String
                val selectedType = binding.spinnerArticleType.selectedItem as String
                val selectedTag = binding.spinnerTag.selectedItem as String

                viewModel.filterArticles(
                    category = selectedCategory,
                    type = selectedType,
                    author = selectedAuthor,
                    tag = selectedTag
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        binding.spinnerAuthor.onItemSelectedListener = onItemSelectedListener
        binding.spinnerCategory.onItemSelectedListener = onItemSelectedListener
        binding.spinnerArticleType.onItemSelectedListener = onItemSelectedListener
        binding.spinnerTag.onItemSelectedListener = onItemSelectedListener
    }

    private fun observeDate() {
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            if (articles != null) {
                articleAdapter.updateArticles(articles)
                viewModel.checkIfArticlesEmpty()
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
        val action = ArticleFragmentDirections.actionArticleFragmentToDetailsFragment(articleId = article.id)
        findNavController().navigate(action)
    }
}