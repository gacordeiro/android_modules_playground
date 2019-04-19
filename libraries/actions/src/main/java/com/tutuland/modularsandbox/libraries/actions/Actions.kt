package com.tutuland.modularsandbox.libraries.actions

import android.app.Activity
import android.content.Context
import android.content.Intent

object Actions {
    const val DETAILS_EXTRA_IMAGE_ID = "details.extra.image.id"
    const val DETAILS_EXTRA_TITLE_ID = "details.extra.title.id"

    fun Activity.detailsScreenIntent() = internalIntent(this, "com.tutuland.modularsandbox.features.details.open")

    fun Activity.openListScreen() =
        startActivity(internalIntent(this, "com.tutuland.modularsandbox.features.list.open"))

    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)
}