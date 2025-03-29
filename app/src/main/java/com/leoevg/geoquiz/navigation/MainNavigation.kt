package com.leoevg.geoquiz.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leoevg.geoquiz.screens.admin.AdminScreen
import com.leoevg.geoquiz.screens.choose.ChooseScreen
import com.leoevg.geoquiz.screens.finish.FinishScreen
import com.leoevg.geoquiz.screens.login.LoginScreen
import com.leoevg.geoquiz.screens.quiz.QuizScreen
import com.leoevg.geoquiz.screens.register.RegisterScreen

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(navController, NavigationPaths.Login, modifier = modifier) {
        composable<NavigationPaths.Login> {
            LoginScreen { navController.navigate(it) }
       }
        composable<NavigationPaths.Register> {
            RegisterScreen { navController.navigate(it) }
        }
        composable<NavigationPaths.Admin> {
            AdminScreen { navController.navigate(it) }
        }
        composable<NavigationPaths.Choose> {
            ChooseScreen { navController.navigate(it) }
        }
        composable<NavigationPaths.Quiz> {
            QuizScreen { navController.navigate(it) }
        }
        composable<NavigationPaths.Finish> {
            FinishScreen { navController.navigate(it) }
        }
    }
}