package com.example.myplants.core.presentation.util

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

class LocaleProviderImpl : LocaleProvider {
    override fun getCurrentLocaleLanguageCode(): String {
        return NSLocale.currentLocale.languageCode
    }
}
