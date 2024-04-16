package com.rajat.assignment.views.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rajat.assignment.R
import com.rajat.assignment.databinding.FragmentDetailPostBinding
import com.rajat.assignment.models.PostsItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPostFragment : Fragment(R.layout.fragment_detail_post) {

    private lateinit var binding : FragmentDetailPostBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailPostBinding.bind(view)

        val bundle = arguments
        if (bundle != null) {
            val details = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("post_data",PostsItem::class.java)
            } else {
                bundle.getParcelable("post_data")
            }
            details.let {
                "ID : ${it?.id.toString()}".also { binding.tvId.text = it }
                it?.body.toString().also { binding.tvBody.text = it }
                "TITLE : ${it?.title.toString()}".also { binding.tvTitle.text = it }
                "USER ID : ${it?.userId.toString()}".also { binding.tvUserId.text = it }
            }

        }




    }
}