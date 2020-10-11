package id.muiz.githubuserapi.core.state

sealed class LoaderState {
    object ShowLoading: LoaderState()
    object HideLoading: LoaderState()
}