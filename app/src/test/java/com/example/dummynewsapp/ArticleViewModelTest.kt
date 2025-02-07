package com.example.dummynewsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.dummynewsapp.articlelist.ArticleViewModel
import com.example.dummynewsapp.articlelist.data.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Test class for ArticleViewModel.
 * Contains unit tests to verify the functionality of the ArticleViewModel.
 */
@ExperimentalCoroutinesApi
class ArticleViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ArticleViewModel

    @Mock
    private lateinit var articlesObserver: Observer<List<Article>?>


    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = ArticleViewModel()
        viewModel.articles.observeForever(articlesObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun filterArticles_withValidCategory_filtersCorrectly() = runTest {
        viewModel.filterArticles(category = "Science")
        val expected = listOf(
            Article(
                "a2b448sq",
                "GitHub Copilot - About, Features and Use Cases",
                "Subtitle 1",
                "https://www.freecodecamp.org/news/content/images/size/w2000/2023/06/Screenshot-2023-06-14-at-12.42.04-PM.png",
                "Author 1",
                "Science",
                "Type 1",
                arrayListOf("Technology", "Science")
            )
        )
        assert(viewModel.articles.value == expected)
    }

    @Test
    fun filterArticles_withInvalidCategory_returnsEmptyList() = runTest {
        viewModel.filterArticles(category = "Invalid Category")
        assert(viewModel.articles.value?.isEmpty() == true)
    }

    @Test
    fun filterArticles_withValidType_filtersCorrectly() = runTest {
        viewModel.filterArticles(type = "Type 2")
        val expected = listOf(
            Article(
                "b2n2ss92",
                "Has Codeium Cracked the Code for AI Assistants?",
                "Subtitle 2",
                "https://www.bigdatawire.com/wp-content/uploads/2024/05/AI-copilot_shutterstock_AI-generated.jpg",
                "Author 2",
                "Technology",
                "Type 2",
                arrayListOf("Science")
            )
        )
        assert(viewModel.articles.value == expected)
    }

    @Test
    fun filterArticles_withValidAuthor_filtersCorrectly() = runTest {
        viewModel.filterArticles(author = "Author 1")
        val expected = listOf(
            Article(
                "a2b448sq",
                "GitHub Copilot - About, Features and Use Cases",
                "Subtitle 1",
                "https://www.freecodecamp.org/news/content/images/size/w2000/2023/06/Screenshot-2023-06-14-at-12.42.04-PM.png",
                "Author 1",
                "Science",
                "Type 1",
                arrayListOf("Technology", "Science")
            )
        )
        assert(viewModel.articles.value == expected)
    }

    @Test
    fun filterArticles_withValidTag_filtersCorrectly() = runTest {
        viewModel.filterArticles(tag = "Science")
        val expected = listOf(
            Article(
                "a2b448sq",
                "GitHub Copilot - About, Features and Use Cases",
                "Subtitle 1",
                "https://www.freecodecamp.org/news/content/images/size/w2000/2023/06/Screenshot-2023-06-14-at-12.42.04-PM.png",
                "Author 1",
                "Science",
                "Type 1",
                arrayListOf("Technology", "Science")
            ),
            Article(
                "b2n2ss92",
                "Has Codeium Cracked the Code for AI Assistants?",
                "Subtitle 2",
                "https://www.bigdatawire.com/wp-content/uploads/2024/05/AI-copilot_shutterstock_AI-generated.jpg",
                "Author 2",
                "Technology",
                "Type 2",
                arrayListOf("Science")
            )
        )
        assert(viewModel.articles.value == expected)
    }

    @Test
    fun filterArticles_withMultipleFilters_filtersCorrectly() = runTest {
        viewModel.filterArticles(category = "Science", type = "Type 1", author = "Author 1", tag = "Technology")
        val expected = listOf(
            Article(
                "a2b448sq",
                "GitHub Copilot - About, Features and Use Cases",
                "Subtitle 1",
                "https://www.freecodecamp.org/news/content/images/size/w2000/2023/06/Screenshot-2023-06-14-at-12.42.04-PM.png",
                "Author 1",
                "Science",
                "Type 1",
                arrayListOf("Technology", "Science")
            )
        )
        assert(viewModel.articles.value == expected)
    }
}