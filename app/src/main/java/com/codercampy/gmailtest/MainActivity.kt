package com.codercampy.gmailtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.codercampy.gmailtest.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mails = generateRandomMails()
        val adapter = MailAdapter(mails) { mail, isStarred ->
            val intent = Intent(this, MailDetailActivity::class.java)
            intent.putExtra("mail", mail)
            intent.putExtra("isStarred", isStarred)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.btnFab.extend()
                } else {
                    binding.btnFab.shrink()
                }
            }

        })

        binding.editText.doAfterTextChanged {
            val text = it?.toString()
            adapter.filter.filter(text)
        }
    }

    private fun generateRandomMails(): List<Mail> {
        val list = mutableListOf<Mail>()
        for (i in 0 until 100) {
            list.add(
                Mail(
                    System.currentTimeMillis() - (Random.nextInt(100, 1000) * 1000 * Random.nextInt(
                        100,
                        1000
                    )),
                    "gaurav_${i}@gmail.com",
                    "Random subject created on $i",
                    "<h1><img src=\"https://www.onlinewebtoolkit.com/images/pages/lorem-ipsum-generator.png\" />Random Header</h1><p>Random body</p>"
                )
            )
        }
        list.sortByDescending { it.timestamp }
        return list
    }

}