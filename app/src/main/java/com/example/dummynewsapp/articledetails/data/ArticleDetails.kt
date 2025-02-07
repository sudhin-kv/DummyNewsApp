package com.example.dummynewsapp.articledetails.data

/**
 * Data class that represents the details of an article.
 *
 * @property articleId The unique identifier of the article.
 * @property authorName The name of the author of the article.
 * @property categoryName The category of the article.
 * @property tags The tags associated with the article.
 * @property title The title of the article.
 * @property subtitle The subtitle of the article.
 * @property image The URL of the image associated with the article.
 * @property descriptor The description of the article.
 */

data class ArticleDetails(
    val articleId : String,
    val authorName : String,
    val categoryName : String,
    val tags : ArrayList<String>,
    val title : String,
    val subtitle : String,
    val image: String,
    val description: String
)
