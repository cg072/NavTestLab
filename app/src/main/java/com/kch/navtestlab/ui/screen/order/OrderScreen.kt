package com.kch.navtestlab.ui.screen.order

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kch.navtestlab.R

@Composable
fun OrderScreen(
    navigateProductScreen: () -> Unit = {},
    navigateWithPopUpTo: () -> Unit = {},
    navigateWithInclusive: () -> Unit = {},
    onClickItem: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ContentLayout(
            modifier = Modifier.weight(1f),
            onClickItem
        )

        Button(
            onClick = navigateProductScreen,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(text = "navigateProductScreen")
        }
        Button(
            onClick = navigateWithPopUpTo,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(text = "navigateWithPopUpTo")
        }
        Button(
            onClick = navigateWithInclusive,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(text = "navigateWithInclusive")
        }
    }
}

@Composable
private fun ContentLayout(
    modifier: Modifier = Modifier,
    onClickItem: (String) -> Unit,
    names: List<String> = List(1000) { "$it" }
) {

    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            ProductItem(
                name = name,
                onClickItem
            )
        }
    }
}


@Composable
fun ProductItem(
    name: String,
    onClickItem: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier
        .padding(vertical = 4.dp, horizontal = 8.dp)
        .clip(RoundedCornerShape(8.dp))) {

        Row(modifier = Modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 24.dp,
                bottom = if (expanded) 0.dp else 24.dp
            )
        ) {
            Row (
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
                    .clickable { onClickItem(name) }
            ) {
                Image(
                    painter = painterResource(R.drawable.coffee),
                    contentDescription = "",
                    modifier = Modifier.requiredSize(60.dp)
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
                )

                if (expanded) Text(text = "Composem ipsum color sit lazy, padding theme elit, sed do bouncy. ".repeat(4))
            }

            IconButton(onClick = { expanded = !expanded }) {
                Image(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderScreenPreview() {
    OrderScreen()
}