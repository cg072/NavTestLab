package com.kch.navtestlab.ui.screen.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProductScreen(
    navigateWithSingleTop: () -> Unit = {},
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "ProductScreen",
            modifier = Modifier.padding(10.dp)
        )

        Button(
            onClick = navigateWithSingleTop,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(text = "navigateWithSingleTop")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    ProductScreen()
}