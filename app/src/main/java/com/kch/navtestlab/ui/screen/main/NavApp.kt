package com.kch.navtestlab.ui.screen.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            HomeScreen()
        }

        composable(route = Order.route) {
            OrderScreen()
        }

        composable(route = Product.route) {
            ProductScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavTestLabApp()
}