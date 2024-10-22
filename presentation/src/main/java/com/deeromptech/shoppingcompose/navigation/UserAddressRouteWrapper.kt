package com.deeromptech.shoppingcompose.navigation

import android.os.Parcelable
import com.deeromptech.shoppingcompose.model.UserAddress
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class UserAddressRouteWrapper(
    val userAddress: UserAddress?
) : Parcelable