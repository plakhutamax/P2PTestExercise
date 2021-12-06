package com.p2ptestexercise.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.widget.doAfterTextChanged
import com.p2ptestexercise.ui.MainRouter
import com.p2ptestexercise.databinding.LoginScreenBinding
import com.p2ptestexercise.ui.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.Scope


class LoginScreen :
    BaseFragment<LoginContract.View, LoginContract.Router, LoginContract.Presenter>(),
    LoginContract.View,
    LoginContract.Router,
    AndroidScopeComponent {

    companion object {
        fun newInstance(): LoginScreen = LoginScreen()
    }

    override val scope: Scope by fragmentScope()
    override val presenter: LoginContract.Presenter by inject()

    private var _binding: LoginScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.passEditText.doAfterTextChanged { presenter.passphraseUpdated(it.toString()) }
        binding.signInButton.setOnClickListener {
            presenter.signIn(binding.passEditText.text.toString())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun showHelper(@StringRes textRes: Int) = with(binding.passInputLayout) {
        helperText = getString(textRes)
        isHelperTextEnabled = true
    }

    override fun hideHelper() {
        binding.passInputLayout.isHelperTextEnabled = false
    }

    override fun showError(@StringRes textRes: Int) {
        showError(getString(textRes))
    }

    override fun showError(text: String) = with(binding.passInputLayout) {
        error = text
        isErrorEnabled = true
    }

    override fun hideError() {
        binding.passInputLayout.isErrorEnabled = false
    }

    override fun navigateToWallets() {
        mainRouter.navigateTo(MainRouter.Destination.Wallets)
    }
}