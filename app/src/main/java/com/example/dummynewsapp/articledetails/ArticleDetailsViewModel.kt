package com.example.dummynewsapp.articledetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dummynewsapp.articledetails.data.ArticleDetails

class ArticleDetailsViewModel : ViewModel() {

    private var articleList = ArrayList<ArticleDetails>()

    //live data for article details
    private val _articleDetails = MutableLiveData<ArticleDetails>()
    val articleDetails: LiveData<ArticleDetails>
        get() = _articleDetails

    // Dummy articles details
    init {
        articleList.addAll(listOf(ArticleDetails(
            "a2b448sq",
            "Author 1",
            "Science",
            arrayListOf("Technology", "Science", "Gen AI", "Copilot"),
            "GitHub Copilot - About, Features and Use Cases",
            "quantifying GitHub Copilot’s impact on developer productivity and happiness",
            "https://www.freecodecamp.org/news/content/images/size/w2000/2023/06/Screenshot-2023-06-14-at-12.42.04-PM.png",
            "<p>    Have you ever wondered how coding could become even more efficient? With GitHub Copilot, you can utilise the power of AI to generate code suggestions according to your specific context</p>\n    <h2 id='1'>Features of GitHub Copilot</h2> \n  <ul> \n   <li>GitHub Copilot uses an AI model trained on a large corpus of code from publicly available sources, including code on GitHub itself. This allows it to understand and generate programming patterns, functions, and entire classes.</li> \n   <li>It assists in writing new code and contributing to existing code. The tool can suggest complete methods, boilerplate code, tests, and even complex algorithms.</li>\n  </ul>"
        ), ArticleDetails(
            "b2n2ss92",
            "Author 2",
            "Technology",
            arrayListOf("technology", "Gen AI", "Copilot"),
            "Has Codeium Cracked the Code for AI Assistants?",
            "quantifying GitHub Copilot’s impact on developer productivity and happiness",
            "https://www.bigdatawire.com/wp-content/uploads/2024/05/AI-copilot_shutterstock_AI-generated.jpg",
            "<p>    Have you ever wondered how coding could become even more efficient? With GitHub Copilot, you can utilise the power of AI to generate code suggestions according to your specific context</p>\n    <h2 id='1'>Features of GitHub Copilot</h2> \n  <ul> \n   <li>GitHub Copilot uses an AI model trained on a large corpus of code from publicly available sources, including code on GitHub itself. This allows it to understand and generate programming patterns, functions, and entire classes.</li> \n   <li>It assists in writing new code and contributing to existing code. The tool can suggest complete methods, boilerplate code, tests, and even complex algorithms.</li>\n  </ul>"
        )))
    }

    fun fetchArticleDetails(articleId: String) {
        _articleDetails.value = articleList.find { it.articleId == articleId }
    }
}