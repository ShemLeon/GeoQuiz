package com.leoevg.geoquiz.navigation
import com.leoevg.geoquiz.data.model.TypeGame
import kotlinx.serialization.Serializable

sealed interface NavigationPaths{
      @Serializable data object Login: NavigationPaths
      @Serializable data object Register: NavigationPaths
      @Serializable data object Admin: NavigationPaths
      @Serializable data object Choose: NavigationPaths

      @Serializable data class Quiz(val typeGame: TypeGame): NavigationPaths

      @Serializable data class Finish(val finalScore: Double): NavigationPaths
      @Serializable data object Rate: NavigationPaths
      @Serializable data object Userpic: NavigationPaths
}
