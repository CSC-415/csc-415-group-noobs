package com.example.bottomnav.signup.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bottomnav.data.entity.UserStat
import com.example.bottomnav.databinding.FragmentSignupBinding
import com.example.bottomnav.signup.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUp: Fragment() {

    //binding data members
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel : SignUpViewModel by activityViewModels()

    companion object{
        var isUsernameAllowed = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpUsername.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called to notify you that, within `s`, the `count` characters
                // beginning at `start` are about to be replaced by new text with length `after`.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called to notify you that, within `s`, the `count` characters
                // beginning at `start` have just replaced old text that had length `before`.
            }

            override fun afterTextChanged(s: Editable?) {
                val userName = s.toString()

                if(!signUpViewModel.isUsernameTaken(userName)){
                    isUsernameAllowed = false
                    Toast.makeText(requireContext(), "Username is already taken!", Toast.LENGTH_SHORT).show()
                }
                else{
                    isUsernameAllowed = true
                }
            }
        })

        binding.signUpButton.setOnClickListener(View.OnClickListener {

        })
    }
}