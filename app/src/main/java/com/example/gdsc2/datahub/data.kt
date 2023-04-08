package com.example.gdsc2.datahub

import android.net.Uri

data class UserData(val imageId: Uri,
                    val item_name: String? = null,
                    val disease:String? = null) {
}
