package com.example.make_best_music.data

object
Constants {
    const val BASE_URL = "https://api.example.com/"
    const val TIMEOUT_SECONDS = 30L

    // Auth Endpoints
    const val LOGIN = "api/v1/auth/login"
    const val REGISTER = "api/v1/auth/register"
    const val FORGOT_PASSWORD = "api/v1/auth/forgot-password"
    const val LOGOUT = "api/v1/auth/logout"

    // Profile Endpoints
    const val GET_PROFILE = "api/v1/user/profile"
    const val UPDATE_PROFILE = "api/v1/user/profile/update"
    const val DEACTIVATE_ACCOUNT = "api/v1/user/deactivate"
    const val DELETE_ACCOUNT = "api/v1/user/delete"

    // Cards Endpoints
    const val GET_CARDS = "api/v1/cards"
    const val ADD_CARD = "api/v1/cards/add"
    const val DELETE_CARD = "api/v1/cards/delete/{id}"

    // Transaction Endpoints
    const val GET_TRANSACTIONS = "api/v1/transactions"
    const val TRANSFER_MONEY = "api/v1/transfer"
}