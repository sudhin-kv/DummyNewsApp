package com.example.dummynewsapp.articledetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.dummynewsapp.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // Create a ViewModel instance
    private val viewModel: ArticleDetailsViewModel by lazy {
        ViewModelProvider(this)[ArticleDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the article ID from the fragment arguments
        val articleId = arguments?.getString("articleId")

        // Check if the article ID is not null and fetch article details
        if (articleId != null) {
            viewModel.fetchArticleDetails(articleId)
        }
        observe()
    }

    // Observe the LiveData
    private fun observe() {
        viewModel.articleDetails.observe(viewLifecycleOwner) { articles ->
            if (articles != null) {
                binding.articleDetails = articles
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}