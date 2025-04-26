package com.leoevg.geoquiz.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Popup
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
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
    NavHost(
        navController = navController,
        startDestination =
            if (FirebaseAuth.getInstance().currentUser == null) NavigationPaths.Login
            else NavigationPaths.Choose,
        modifier = modifier
    ) {
        composable<NavigationPaths.Login> {
            LoginScreen(
                navigate = {
                    navController.navigate(it)
                },
                popBackStack = {
                    navController.popBackStack()
                }
            )
       }
        composable<NavigationPaths.Register> {
            RegisterScreen { navController.navigate(it) }
        }
        composable<NavigationPaths.Admin> {
            AdminScreen { navController.navigate(it) }
        }
        composable<NavigationPaths.Choose> {
            ChooseScreen(
                navigate = {
                    navController.navigate(it)
                },
                popBackStack = {
                    navController.popBackStack()
                }
            )
        }
        composable<NavigationPaths.Quiz> {
            QuizScreen { navController.navigate(it) }
        }
        composable<NavigationPaths.Finish> {
            FinishScreen { navController.navigate(it) }
        }
    }
}












