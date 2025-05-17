package com.leoevg.geoquiz.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.leoevg.geoquiz.data.model.TypeGame
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val TypeGameNavType = object : NavType<TypeGame>(isNullableAllowed = false) {
        override fun get(
            bundle: Bundle,
            key: String
        ): TypeGame? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): TypeGame {
            return Json.decodeFromString(value)
        }

        override fun serializeAsValue(value: TypeGame): String {
            return Json.encodeToString(value)
        }

        override fun put(bundle: Bundle, key: String, value: TypeGame) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}