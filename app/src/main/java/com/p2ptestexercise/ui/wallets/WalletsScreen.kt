package com.p2ptestexercise.ui.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.p2ptestexercise.R
import com.p2ptestexercise.databinding.WalletsScreenBinding
import com.p2ptestexercise.network.model.Wallet
import com.p2ptestexercise.ui.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.Scope

class WalletsScreen :
    BaseFragment<WalletsContract.View, WalletsContract.Router, WalletsContract.Presenter>(),
    WalletsContract.View,
    WalletsContract.Router,
    AndroidScopeComponent {

    companion object {
        fun newInstance() = WalletsScreen()

        private const val CHILD_LOADING = 0
        private const val CHILD_ERROR = 1
        private const val CHILD_EMPTY = 2
        private const val CHILD_DATA = 3
    }

    override val scope: Scope by fragmentScope()
    override val presenter: WalletsContract.Presenter by inject()

    private var _binding: WalletsScreenBinding? = null
    private val binding get() = _binding!!

    private val walletsAdapter: WalletsAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WalletsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.errorState) {
            title.setText(R.string.wallets_loading_error)
            retryButton.setOnClickListener {
                presenter.refreshData()
            }
        }

        // For now it's terminate state for app session
        // Better allow user to create new wallets here (refresh also option but it isn't good for UX)
        with(binding.emptyState) {
            title.setText(R.string.wallets_empty)
        }

        with(binding.normalState) {
            walletList.addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
            walletList.adapter = walletsAdapter
        }

        presenter.loadData()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun showLoadingState() {
        binding.contentFlipper.displayedChild = CHILD_LOADING
    }

    override fun showEmptyState() {
        binding.contentFlipper.displayedChild = CHILD_EMPTY
    }

    // We have error information, but for now we have only generic "try again" recover path
    override fun showErrorState() {
        binding.contentFlipper.displayedChild = CHILD_ERROR
    }

    override fun updateData(data: List<Wallet>) {
        binding.contentFlipper.displayedChild = CHILD_DATA
        walletsAdapter.updateItems(data)
    }
}