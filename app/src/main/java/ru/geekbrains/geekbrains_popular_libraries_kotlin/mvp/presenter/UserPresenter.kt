package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo.GithubUsersRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list.IUserListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UsersView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.UserItemView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.navigation.AndroidScreens

class UserPresenter(private val user: GithubUser, private val router: Router) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setUserTvText(user.login)
    }

    fun backClick(): Boolean {

        router.exit()
        return true
    }
}