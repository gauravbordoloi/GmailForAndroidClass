package com.codercampy.gmailtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codercampy.gmailtest.databinding.ActivityMailDetailBinding

class MailDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMailDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMailDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.settings.javaScriptEnabled = true

        val mail = intent.getParcelableExtra<Mail>("mail")
        val isStarred = intent.getBooleanExtra("isStarred", false)
        if (mail != null) {

            binding.tvSubject.text = mail.subject
            binding.webView.loadData(mail.body, "text/html", "utf-8")

            if (isStarred) {
                binding.btnStar.setImageResource(R.drawable.star)
            } else {
                binding.btnStar.setImageResource(R.drawable.star_outline)
            }
        }


    }

}