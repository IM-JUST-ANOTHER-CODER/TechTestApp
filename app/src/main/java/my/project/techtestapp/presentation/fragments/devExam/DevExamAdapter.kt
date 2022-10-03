package my.project.techtestapp.presentation.fragments.devExam

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import my.project.techtestapp.data.models.remote.articles.ArticlesResponseItem
import my.project.techtestapp.databinding.ArticleItemBinding
import my.project.techtestapp.utils.Constants.DATE_FORMAT_PATTERN
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DevExamAdapter : RecyclerView.Adapter<DevExamAdapter.ArticlesViewHolder>() {

    private var listArticles = emptyList<ArticlesResponseItem>()

    class ArticlesViewHolder(private val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(article: ArticlesResponseItem) {
            binding.apply {
                articleTitle.text = article.title
                articleText.text = article.text
                articleDate.text = article.date?.let { formatDate(it) }
//                Picasso.get().load(article.image).into(articleItemImage)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun formatDate(date: String): String {
            val dateTime : ZonedDateTime = OffsetDateTime.parse(date).toZonedDateTime()
            val defaultZoneTime: ZonedDateTime = dateTime.withZoneSameInstant(ZoneId.systemDefault())
            val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)
            return defaultZoneTime.format(formatter)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ArticleItemBinding.inflate(layoutInflater, parent, false)
        return ArticlesViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(listArticles[position])
    }

    override fun getItemCount(): Int {
        return listArticles.size
    }

    fun setArticles(articles: List<ArticlesResponseItem>) {
        listArticles = articles
        notifyDataSetChanged()
    }
}