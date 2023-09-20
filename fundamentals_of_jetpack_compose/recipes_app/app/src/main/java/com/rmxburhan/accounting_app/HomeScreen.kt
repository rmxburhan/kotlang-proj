package com.rmxburhan.accounting_app

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rmxburhan.accounting_app.data.Recipe
import com.rmxburhan.accounting_app.navigation.BottomNavItem
import com.rmxburhan.accounting_app.ui.theme.RecipesAppTheme

@Composable
fun HomeScreen() {
    var navBarSelected by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            CustomAppBar()
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Recipes list", style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                ), modifier = Modifier.padding(bottom = 8.dp))
                RecipeCard(Recipe("Makanan", "seuasdasbdkajsbdkajsbdkjasbdk", "Indonesia", "my.jpg"))
                RecipeCard(Recipe("Makanan", "seuasdasbdkajsbdkajsbdkjasbdk", "Indonesia", "my.jpg"))
                RecipeCard(Recipe("Makanan", "seuasdasbdkajsbdkajsbdkjasbdk", "Indonesia", "my.jpg"))
                RecipeCard(Recipe("Makanan", "seuasdasbdkajsbdkajsbdkjasbdk", "Indonesia", "my.jpg"))
                RecipeCard(Recipe("Makanan", "seuasdasbdkajsbdkajsbdkjasbdk", "Indonesia", "my.jpg"))
                RecipeCard(Recipe("Makanan", "seuasdasbdkajsbdkajsbdkjasbdk", "Indonesia", "my.jpg"))
            }
        }
        HomeBottomNavigation(
            selectedNavigation = navBarSelected,
            onNavigationIndexChanged = {navBarSelected = it })
    }
}


@Composable
fun BoxScope.HomeBottomNavigation(
    selectedNavigation : Int,
    onNavigationIndexChanged : (index : Int) -> Unit
) {
    val bottomNav : List<BottomNavItem> = listOf(
        BottomNavItem(
            title = "Home",
            icon = Icons.Outlined.Home,
            iconSelected = Icons.Filled.Home
        ),
        BottomNavItem(
            title = "Marked",
            icon = Icons.Outlined.Bookmarks,
            iconSelected = Icons.Filled.Bookmarks
        )
    )
    NavigationBar(
        modifier = Modifier.align(Alignment.BottomCenter)
    ) {
        bottomNav.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigation == index,
                onClick = {
                    onNavigationIndexChanged(index)
                },
                icon = {
                    if (selectedNavigation == index ){
                        Icon(item.iconSelected, contentDescription = item.title)
                    } else {
                        Icon(item.icon, contentDescription = item.title)
                    }
                },
                label = {
                    Text(item.title)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchField(
    modifier : Modifier = Modifier,
    onTextChanged : (newText : String) -> Unit,
    textValue : String
){
    OutlinedTextField(
        modifier = modifier,
        value = textValue,
        onValueChange = {
            onTextChanged(it)
        },
        textStyle = MaterialTheme.typography.labelLarge ,
        placeholder = {
            Text("Search here...", style = MaterialTheme.typography.labelLarge)
        },
        singleLine = true,
        shape = MaterialTheme.shapes.extraLarge,
        leadingIcon = {
            Icon(Icons.Filled.Search, "search")
        },
        trailingIcon = {
            if (textValue.isNullOrEmpty())
                null
            else
            {
                IconButton(onClick = { onTextChanged("")}) {
                    Icon(Icons.Filled.Cancel, "cancel")
                }
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun SearchFieldPreview() {
//    Accounting_appTheme {
//        var text by remember {
//            mutableStateOf("")
//        }
//        CustomSearchField(onTextChanged = {text = it}, textValue = text)
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(modifier : Modifier = Modifier) {
    var text by remember { mutableStateOf("")}
    CenterAlignedTopAppBar(
        title = {
            Text("Recipes", style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            ))
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(Icons.Filled.Sort, contentDescription = "navigation_menu")
            }
        },
    )
}

//@Preview
//@Composable
//fun PreviewCustomAppBar() {
//    Accounting_appTheme {
//        CustomAppBar()
//    }
//}
//
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    RecipesAppTheme {
        HomeScreen()
    }
}

@Composable
fun RecipeCard(recipe : Recipe) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = White,

        ),
        modifier = Modifier.heightIn(max = 80.dp).padding(bottom = 8.dp),
        border = BorderStroke(width = .8.dp, color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(fill = true, weight = 1f)
                    .padding(end = 8.dp)
            ) {
                Text(recipe.title, style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ))
                Text(recipe.country, style = MaterialTheme.typography.bodyMedium)
            }
            Card(
                shape = MaterialTheme.shapes.medium
            ) {
                Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = recipe.title, modifier = Modifier.wrapContentWidth(),
                alignment = Alignment.Center)
            }
        }
    }
}

//@Preview(showBackground = true, widthDp = 380)
//@Composable
//fun RecipeCard() {
//    Accounting_appTheme {
//        RecipeCard(Recipe("Makanan", "seuasdasbdkajsbdkajsbdkjasbdk", "Indonesia", "my.jpg"))
//    }
//}


