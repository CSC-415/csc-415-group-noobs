package com.example.bottomnav.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bottomnav.MainActivity
import com.example.bottomnav.R
import com.example.bottomnav.databinding.FragmentHomeBinding
import com.example.bottomnav.databinding.FragmentLoginBinding
import com.example.bottomnav.home.ui.Home
import com.example.bottomnav.login.viewmodel.LoginViewModel
import com.example.bottomnav.signup.ui.SignUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : Fragment(){

    //binding data members
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel : LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener{
            val username = binding.loginUsername.toString()
            val password = binding.loginPassword.toString()

            if(loginViewModel.isLoginValid(username, password)){
                replaceFragment(Home())
                (requireActivity() as MainActivity).setItemSelectedOnBottomNav()
            }
            else{
                Toast.makeText(requireContext(), "Invalid Username or Password!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signUpButton.setOnClickListener{
            replaceFragment(SignUp())
        }
    }

    private fun replaceFragment(destinationFragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, destinationFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}