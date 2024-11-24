package com.kch.navtestlab.ui.screen.main

import android.media.Image
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.kch.navtestlab.ui.navigation.Home
import com.kch.navtestlab.ui.navigation.Order
import com.kch.navtestlab.ui.navigation.Product
import com.kch.navtestlab.ui.screen.home.HomeScreen
import com.kch.navtestlab.ui.screen.order.OrderScreen
import com.kch.navtestlab.ui.screen.product.ProductScreen
import com.kch.navtestlab.ui.theme.NavTestLabTheme

@Composable
fun NavTestLabApp() {
    NavTestLabTheme {
        val navController = rememberNavController()

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            NavTestLabHost(navController, innerPadding)
        }
    }
}

@Composable
fun NavTestLabHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {

        composable(route = Home.route) {
            HomeScreen(
                navigateOrder = {
                    navController.navigate(route = Order.route)
                }
            )
        }

        composable(
            route = Order.route,
            enterTransition = { moveBottomUp() },
            exitTransition = { moveBottomDown() }
        ) {
            OrderScreen(
                navigateProductScreen = {
                    // 컴포저블로 이동
                    navController.navigate(route = Product.route)
                },
                navigateWithPopUpTo = {
                    navController.navigate(Product.route) {
                        // Home에서 Product 사이 백스택 지우기
                        popUpTo(Home.route)
                    }
                },
                navigateWithInclusive = {
                    navController.navigate(Product.route) {
                        // Home 포함한 모든 백 스택 지우기
                        popUpTo(Home.route) {
                            inclusive = true
                        }
                    }
                },
                onClickItem = { name ->
                    navController.navigate(
                        Product.route,
                        navOptions = navOptions {  },
                        ItemInformation(name, "")
                    )
                }
            )
        }

        composable(
            route = Product.route,
            enterTransition = { moveBottomUp() },
            exitTransition = { moveBottomDown() }
        ) { navBackStackEntry ->

            val item = navBackStackEntry.arguments?.get("item")

            ProductScreen(
                navigateWithSingleTop = {
                    navController.navigate(route = Order.route) {
                        popUpTo(Order.route) {
                            inclusive = true
                        }

                        launchSingleTop = true // 최상위 화면을 다시 생성하지 않고 재활용
                    }
                }
            )
        }
    }
}

data class ItemInformation(val name: String, val content: String): Navigator.Extras

/**
 * XML 기반 애니메이션 리소스는 Compose에서 기본적으로 지원되지 않기
 * 때문에 Compose 스타일로 애니메이션을 정의
 */
private fun moveBottomDown() = slideOutVertically(
    targetOffsetY = { it },
    animationSpec = tween(300)
)

private fun moveBottomUp() = slideInVertically(
    initialOffsetY = { it }, // 시작 위치
    animationSpec = tween(300) // 지속 시간
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavTestLabApp()
}