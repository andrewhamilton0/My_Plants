package com.example.myplants.core.presentation.util

import android.content.Context

class LocaleProviderImpl(private val context: Context) : LocaleProvider {
    override fun getCurrentLocaleLanguageCode(): String {
        return context.resources.configuration.locales.get(0).toLanguageTag()
    }
}
