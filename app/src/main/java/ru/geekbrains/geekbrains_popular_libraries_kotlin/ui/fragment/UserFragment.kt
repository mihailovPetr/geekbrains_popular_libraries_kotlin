package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentUserBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.UserPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackButtonListener

class UserFragment() : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var vb: FragmentUserBinding? = null
    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable(BUNDLE_EXTRA) ?: GithubUser("No login")
        UserPresenter(user, App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backClick()

    companion object {
        private const val BUNDLE_EXTRA = "GithubUser"
        fun getInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_EXTRA, user)
            }
        }
    }

    override fun setUserTvText(text: String) {
        vb?.tvUserLogin?.text = text
    }
}