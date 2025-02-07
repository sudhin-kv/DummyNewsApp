package com.example.dummynewsapp.articlelist

/*Create Article adapter clas for recyclerview to show title, subtitle and image
 * Adapter class for displaying articles in a RecyclerView.
 * It shows the title, subtitle, and image of each article.
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummynewsapp.articlelist.data.Article
import com.example.dummynewsapp.databinding.ArticleItemBinding

class ArticleAdapter(private var articles: List<Article>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {


    class ArticleViewHolder(val binding: ArticleItemBinding, listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                binding.article?.let { article ->
                    listener.onItemClick(article)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding, listener)
    }

    /**
     * Binds the data to the view holder.
     *
     * @param holder The view holder to bind the data to.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.article = article
    }

    override fun getItemCount(): Int = articles.size

    /**
     * Updates the list of articles and notifies the adapter to refresh the views.
     *
     * @param newArticles The new list of articles to be displayed.
     */
    fun updateArticles(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(article: Article)
    }
}
