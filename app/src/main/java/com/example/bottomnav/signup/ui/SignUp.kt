package com.example.bottomnav.signup.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bottomnav.R
import com.example.bottomnav.data.entity.UserStat
import com.example.bottomnav.data.repository.DatabaseRepository
import com.example.bottomnav.databinding.FragmentHomeBinding
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        return binding.root
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

                if(signUpViewModel.isUsernameTaken(userName)){
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
                val newUserStat = UserStat(0, binding.signUpUsername.text.toString(), binding.signUpPassword.text.toString(),
                    22, 4321, 3456,
                    2435, 200, 0,
                    0, 0, 0, 0,
                    0, 0, 5)
                signUpViewModel.insertUser(newUserStat)
                Toast.makeText(requireContext(), "Profile created!", Toast.LENGTH_SHORT).show()
                pullLoginFragment()
            }
            else{
                Toast.makeText(requireContext(), "Username is already taken!", Toast.LENGTH_SHORT).show()
            }
        })

        binding.loginButton.setOnClickListener(View.OnClickListener {
            pullLoginFragment()
        })
    }

    private fun pullLoginFragment(){
        val destinationFragment = Login()

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, destinationFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}