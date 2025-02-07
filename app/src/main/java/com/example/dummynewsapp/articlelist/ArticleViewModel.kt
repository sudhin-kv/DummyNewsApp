package com.example.dummynewsapp.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummynewsapp.articlelist.data.Article
import com.example.dummynewsapp.articlelist.data.Categories
import kotlinx.coroutines.launch

/**
 * viewmodel for articles. These can be filtered specific to a category, article type, author and tag name
 */

class ArticleViewModel  : ViewModel() {
    private val _articles = MutableLiveData<List<Article>?>()
    val articles: LiveData<List<Article>?> get() = _articles

    private val _authors = MutableLiveData<List<Categories>>()
    val authors: LiveData<List<Categories>> get() = _authors

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> get() = _categories

    private val _articleTypes = MutableLiveData<List<Categories>>()
    val articleTypes: LiveData<List<Categories>> get() = _articleTypes

    private val _tags = MutableLiveData<List<Categories>>()
    val tags: LiveData<List<Categories>> get() = _tags

    // Initialize the articles list
    init {
        // Fetch articles from a data source
        fetchArticles()

        //Fetches authors from the data source and updates the LiveData.
        fetchAuthors()

        //Fetches categories from the data source and updates the LiveData.
        fetchCategories()

        //Fetches article types from the data source and updates the LiveData.
        fetchArticleTypes()

        //Fetches tags from the data source and updates the LiveData.
        fetchTags()
    }

    private fun fetchAuthors() {
        viewModelScope.launch {
            // Fetch authors from API
            val authorsFromApi = listOf(Categories("0","Select Author"), Categories("1","Author 1"), Categories("2","Author 2"), Categories("3","Author 3")) // Replace with actual API call
            _authors.value = authorsFromApi
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            // Fetch categories from API
            val categoriesFromApi = listOf(Categories("0","Select Category"), Categories("1","Technology"), Categories("2","Science")) // Replace with actual API call
            _categories.value = categoriesFromApi
        }
    }

    private fun fetchArticleTypes() {
        viewModelScope.launch {
            // Fetch article types from API
            val articleTypesFromApi = listOf(Categories("0","Select Type"), Categories("1","Type 1"), Categories("2","Type 2"), Categories("3","Type 3")) // Replace with actual API call
            _articleTypes.value = articleTypesFromApi
        }
    }

    private fun fetchTags() {
        viewModelScope.launch {
            // Fetch tags from API
            val tagsFromApi = listOf(Categories("0","Select Tag"), Categories("1","Science"), Categories("2","Technology")) // Replace with actual API call
            _tags.value = tagsFromApi
        }
    }

    /**
     * Fetches articles from a data source and updates the LiveData.
     * */
    private fun fetchArticles() {
        // Load articles from a data source
        _articles.value = listOf(
            // Example articles
            Article(
                "a2b448sq",
                "GitHub Copilot - About, Features and Use Cases",
                "Subtitle 1",
                "https://www.freecodecamp.org/news/content/images/size/w2000/2023/06/Screenshot-2023-06-14-at-12.42.04-PM.png",
                "Author 1",
                "Category 1",
                "Article Type 1",
                "Tag 1"
            ),
            Article(
                "b2n2ss92",
                "Has Codeium Cracked the Code for AI Assistants?",
                "Subtitle 2",
                "https://www.bigdatawire.com/wp-content/uploads/2024/05/AI-copilot_shutterstock_AI-generated.jpg",
                "Author 2",
                "Category 2",
                "Article Type 2",
                "Tag 2"
            )
        )
    }

    // Filter articles based on category, type, author and tag
    fun filterArticles(
        category: String? = null,
        type: String? = null,
        author: String? = null,
        tag: String? = null
    ) {
        val filteredArticles = _articles.value?.filter { article ->
            (category == null || category == "Select Category" || article.category == category) &&
                    (type == null || type == "Select Type" || article.articleType == type) &&
                    (author == null || author == "Select Author" ||article.author == author) &&
                    (tag == null || tag == "Select Tag" || article.tag.contains(tag))
        }
        _articles.value = filteredArticles
    }
}
