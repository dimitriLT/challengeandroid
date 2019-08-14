package com.letorriellec.dimitri.challenge_android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.letorriellec.dimitri.challenge_android.R
import com.letorriellec.dimitri.challenge_android.model.CategoryViewModel
import kotlinx.android.synthetic.main.sous_category.*

class SubCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sous_category)

        setSupportActionBar(detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val category = intent?.extras?.getSerializable("category") as? CategoryViewModel

        if (category != null) {
            categoryIdTextView.text = category.parent?.id.toString()
            categoryResourceTypeTextView.text = category.parent?.resourceType
            categoryResourcesUriTextView.text = category.parent?.resourceUri
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}