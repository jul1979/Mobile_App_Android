package be.heb.g48982.myfirstapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import be.heb.g48982.myfirstapp.EmailValidator
import be.heb.g48982.myfirstapp.R
import be.heb.g48982.myfirstapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * This [Fragment] will show the login screen.
 *
 */

class LoginFragment : Fragment() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login,
            container, false
        )
        binding.buttonSignUp.setOnClickListener { view ->
            view.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }
        binding.buttonSignin.setOnClickListener {
            val userEmail = binding.editTextEmailSignin.text.toString().trim()
            val userPassword = binding.editTextPasswordSignin.text.toString()
            if (userEmail.isNotEmpty() && EmailValidator.isEmailValid(userEmail) && userPassword.isNotEmpty()) {
                signInWithFirebase(userEmail, userPassword)
            } else {
                Toast.makeText(requireContext(), "INVALID CREDENTIALS", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSearchFragment())
        }
    }

    private fun signInWithFirebase(userEmail: String, userPassword: String) {

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("DEBUG", "Login is successful", task.exception)
                    Toast.makeText(
                        requireContext(),
                        "Login is successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSearchFragment())

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Email or password are incorrect,TRY AGAIN",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}