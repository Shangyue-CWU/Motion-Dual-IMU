package com.example.motionwatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.motionwatch.R.id.btnBack
import com.example.motionwatch.firebase.FirebaseUserFetcher

class AccountInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_account_info, container, false)

        //back button
        view.findViewById<View>(btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        val tvName = view.findViewById<TextView>(R.id.tvName)


        tvUsername.text = "Username: --"
        tvEmail.text = "Email: --"
        tvName.text = "Name: --"

        FirebaseUserFetcher.fetchCurrentUser { user ->
//            if (user != null) {
//                tvUsername.text = "Username: ${user.username}"
//                tvEmail.text = "Email: ${user.email}"
//                tvName.text = "Name: ${user.name}"
//            }
            if (user != null) {
                tvName.setText(user.name);
                tvEmail.setText(user.email);
                tvUsername.setText(user.username);
            }


        }

        return view
    }
}


