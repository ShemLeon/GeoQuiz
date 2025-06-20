package com.leoevg.geoquiz.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.firebase.auth.FirebaseAuth
import com.leoevg.geoquiz.data.model.TypeGame
import com.leoevg.geoquiz.screens.admin.AdminScreen
import com.leoevg.geoquiz.screens.choose.ChooseScreen
import com.leoevg.geoquiz.screens.finish.FinishScreen
import com.leoevg.geoquiz.screens.login.LoginScreen
import com.leoevg.geoquiz.screens.quiz.QuizScreen
import com.leoevg.geoquiz.screens.rate.RateScreen
import com.leoevg.geoquiz.screens.register.RegisterScreen
import kotlin.reflect.typeOf

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
            RegisterScreen(
                navigate = {
                    navController.navigate(it)
                },
                popBackStack = {
                    navController.popBackStack()
                }
            )
        }

        composable<NavigationPaths.Admin> {
            AdminScreen { navController.navigate(it) }
        }

        composable<NavigationPaths.Choose> {
            ChooseScreen(
                navigate = {
                    navController.navigate(it)
                },
                onQuizSelected = {
                    navController.navigate(it)
                },
                popBackStack = {
                    navController.popBackStack()
                }
            )
        }

        composable<NavigationPaths.Quiz>(
            typeMap = mapOf(
                typeOf<TypeGame>() to CustomNavType.TypeGameNavType
            )
        ) {
            val quizInfo = it.toRoute<NavigationPaths.Quiz>()
            // Передача выбранного типа квиза из ChooseScreen
            QuizScreen(
                typeGame = quizInfo.typeGame,
                navigate = { navController.navigate(it) },
                popBackStack = { navController.popBackStack() }
            )
        }

        composable<NavigationPaths.Finish> {
            val finishScreenInfo = it.toRoute<NavigationPaths.Finish>()
            FinishScreen(
                finalScore = finishScreenInfo.finalScore,
                navigate = {
                    navController.navigate(it) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<NavigationPaths.Rate> {
            RateScreen(
                navigate = {
                    navController.navigate(it)
                },
                popBackStack = {
                   navController.popBackStack()
                }
            )
        }

    }
}












