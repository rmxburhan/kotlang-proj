package com.rmxburhan.create_basic_app

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rmxburhan.create_basic_app.ui.theme.Create_basic_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Create_basic_appTheme(dynamicColor = false) {
                // A surface container using the 'background' color from the theme
               MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}



@Composable
private fun Greetings(modifier : Modifier = Modifier, names : List<String> = List(1000) {"$it"}) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = names) {name ->
                Greeting(name = name, body = "Lorem ipsum todor aset alskdjaskljdaksljdlkasjdlkasjdlkjasldjasldjaskljdsaljdaslkjdaskljdaskljdaskldjsakldjalkj")
            }
        }
    }
}

@Composable
fun MyApp(modifier : Modifier =Modifier) {
    var shouldShowOnboardingScreen by rememberSaveable { mutableStateOf(true)}
    Surface(modifier) {
        if (shouldShowOnboardingScreen) {
            OnboardingScreen { shouldShowOnboardingScreen = false }
        } else {
                Greetings()
        }
    }
}

@Composable
fun Greeting(name: String, body : String) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState (
        if (isExpanded)  48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val color by animateColorAsState(targetValue =
        if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    )

    val borderColor by animateColorAsState(
        targetValue = if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    )


        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                )
                {
                    Text(text = "Hello")
                    Text(
                        text = "$name!", style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    if (isExpanded) {
                        Text(text = body)
                    }
                }
                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = stringResource(id = if (isExpanded) R.string.show_less else R.string.show_more)
                    )
                }
            }
        }
}

@Preview
@Composable
fun MyAppPreview() {
    Create_basic_appTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true, widthDp = 320)
@Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES, name="Dark"
    )
@Composable
fun GreetingsPreview() {
    Create_basic_appTheme {
        Greetings()
    }
}

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onContinueClicked: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the basic codelab!")
        Button(onClick = onContinueClicked,
            modifier = Modifier.padding(vertical = 24.dp)) {
            Text(text = "Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun PreviewOnboardingScreen() {
    Create_basic_appTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

