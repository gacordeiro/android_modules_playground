package com.tutuland.modularsandbox.libraries.utils

interface BindingPresenter {
    fun bind()
    fun unbind()
}

interface LoadingView {
    fun showLoading()
    fun hideLoading()
}

interface ErrorStateView {
    fun showErrorState()
    fun hideErrorState()
}