package com.leoevg.geoquiz.navigation
import kotlinx.serialization.Serializable

sealed interface NavigationPaths{
      @Serializable data object Login: NavigationPaths
      @Serializable data object Register: NavigationPaths
      @Serializable data object Admin: NavigationPaths


      @Serializable data object Choose: NavigationPaths
      @Serializable data class Quiz(val typeGameId: Int): NavigationPaths
      @Serializable data class Finish(val score: Int): NavigationPaths

}