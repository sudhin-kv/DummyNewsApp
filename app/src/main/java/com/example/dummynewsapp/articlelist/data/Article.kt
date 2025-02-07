package com.example.dummynewsapp.articlelist.data

/**
 * Data class that represents an article.
 *
 * @property id The unique identifier of the article.
 * @property title The title of the article.
 * @property subtitle The subtitle of the article.
 * @property image The URL of the image associated with the article.
 * @property author The author of the article.
 * @property category The category of the article.
 * @property articleType The type of the article.
 * @property tag The tag associated with the article.
 */
data class Article(
    val id: String,
    val title: String,
    val subtitle: String,
    val image: String,
    val author: String,
    val category: String,
    val articleType: String,
    val tags: ArrayList<String>,
)
