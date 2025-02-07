package com.example.dummynewsapp.articledetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dummynewsapp.articledetails.data.ArticleDetails

class ArticleDetailsViewModel : ViewModel() {
    //live data for article details
    private val _articleDetails = MutableLiveData<ArticleDetails>()
    val articleDetails: LiveData<ArticleDetails>
        get() = _articleDetails

    // Dummy articles details
    init {
        _articleDetails.value = ArticleDetails(
            "a2b448sq",
            "Anonymous",
            "Anonymous",
            arrayListOf("technology", "Gen AI", "Copilot"),
            "GitHub Copilot - About, Features and Use Cases",
            "quantifying GitHub Copilot’s impact on developer productivity and happiness",
            "https://www.freecodecamp.org/news/content/images/size/w2000/2023/06/Screenshot-2023-06-14-at-12.42.04-PM.png",
            "<p>    Have you ever wondered how coding could become even more efficient? With GitHub Copilot, you can utilise the power of AI to generate code suggestions according to your specific context</p>\n    <h2 id='1'>Features of GitHub Copilot</h2> \n  <ul> \n   <li>GitHub Copilot uses an AI model trained on a large corpus of code from publicly available sources, including code on GitHub itself. This allows it to understand and generate programming patterns, functions, and entire classes.</li> \n   <li>It assists in writing new code and contributing to existing code. The tool can suggest complete methods, boilerplate code, tests, and even complex algorithms.</li>\n  </ul>"
        )
    }
}