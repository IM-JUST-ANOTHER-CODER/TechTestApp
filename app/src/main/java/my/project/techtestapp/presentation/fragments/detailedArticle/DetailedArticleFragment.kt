package my.project.techtestapp.presentation.fragments.detailedArticle

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import my.project.techtestapp.R
import my.project.techtestapp.databinding.FragmentDetailedArticleBinding
import my.project.techtestapp.utils.Constants.BASE_URL
import my.project.techtestapp.utils.formatDate


class DetailedArticleFragment : Fragment(R.layout.fragment_detailed_article) {

    private val binding by viewBinding(FragmentDetailedArticleBinding::bind)
    private val args: DetailedArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataToFragment()
    }

    private fun setDataToFragment() {
        val article = args.article
        binding.apply {
            detailedArticleTitle.text = article.title
            detailedArticleDate.text = article.formatDate(article.date)
            detailedArticleText.text = article.text
            Glide.with(this.root).load(BASE_URL + article.image).into(detailedItemImage)
        }
    }
}