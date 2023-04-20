package com.example.bottomnav.signup.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bottomnav.R
import com.example.bottomnav.data.entity.UserStat
import com.example.bottomnav.data.repository.DatabaseRepository
import com.example.bottomnav.databinding.FragmentSignupBinding
import com.example.bottomnav.login.ui.Login
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpUsername.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val userName = s.toString()

                if(!signUpViewModel.isUsernameTaken(userName)){
                    isUsernameAllowed = false
                    Toast.makeText(requireContext(), "Already taken!", Toast.LENGTH_SHORT).show()
                }
                else{
                    isUsernameAllowed = true
                }
            }
        })

        binding.signUpButton.setOnClickListener(View.OnClickListener {
            if(isUsernameAllowed){
                val newUserStat = UserStat(0, binding.signUpUsername.toString(), binding.signUpPassword.toString(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
                signUpViewModel.insertUser(newUserStat)
                replaceLoginFragment()
            }
            else{
                Toast.makeText(requireContext(), "Username is already taken!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun replaceLoginFragment(){
        val destinationFragment = Login()

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, destinationFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}