package be.heb.g48982.myfirstapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import be.heb.g48982.myfirstapp.EmailValidator
import be.heb.g48982.myfirstapp.R
import be.heb.g48982.myfirstapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * This [Fragment] will show the  sign up screen.
 */

class SignUpFragment : Fragment() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentSignUpBinding>(
            inflater,
            R.layout.fragment_sign_up,
            container,
            false
        )

        binding.buttonSignupUser.setOnClickListener {
            val userEmail = binding.editTextEmailSignup.text.toString().trim()
            val userPassword = binding.editTextPasswordSignup.text.toString()
            if (userEmail.isNotEmpty() && EmailValidator.isEmailValid(userEmail) && userPassword.isNotEmpty()) {
                signupWithFirebase(userEmail, userPassword)
            } else {
                Toast.makeText(requireContext(), "INVALID CREDENTIALS", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    private fun signupWithFirebase(userEmail: String, userPassword: String) {

        auth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Your account has been created",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Sorry Email or Password do not meet standards",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}