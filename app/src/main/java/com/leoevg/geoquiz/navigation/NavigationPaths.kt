package com.leoevg.geoquiz.navigation
import kotlinx.serialization.Serializable

sealed interface NavigationPaths{
      @Serializable data object Login: NavigationPaths
      @Serializable data object Register: NavigationPaths
      @Serializable data object Admin: NavigationPaths

      @Serializable data class Quiz(val typeGameId: Int): NavigationPaths
      @Serializable data class Choose(val typeGameId: Int): NavigationPaths  // потом поменять
      @Serializable data object Finish: NavigationPaths



}