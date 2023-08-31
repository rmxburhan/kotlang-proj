package com.example.fundamental_of_jetpack_compose.get_started

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fundamental_of_jetpack_compose.R
import com.example.fundamental_of_jetpack_compose.ui.theme.Fundamental_of_jetpack_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Fundamental_of_jetpack_composeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
//                    MessageCard(Message("Hiroshima", "Nagawasaki"))
                    Conversation(messages = SampleData.conversationSample)
                }
            }
        }
    }
}

data class Message(val author : String, val body : String)
@Composable
fun MessageCard(msg : Message) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.img_test),
            contentDescription = "My wife",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.size(8.dp))
        var isExpanded by remember {
            mutableStateOf(false)
        }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        )
        Column(modifier = Modifier.clickable {
            isExpanded = !isExpanded
        }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.size(2.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}

@Composable
fun Conversation(messages : List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(msg = message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation(){
    Fundamental_of_jetpack_composeTheme {
        Conversation(messages = SampleData.conversationSample)
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark mode"
)
@Composable
fun PreviewMessageCard() {
    Fundamental_of_jetpack_composeTheme {
        Surface {
            MessageCard(msg = Message("Rizal", "Burhanudin"))
        }
    }
}
