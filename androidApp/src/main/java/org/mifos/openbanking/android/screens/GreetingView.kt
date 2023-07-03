package org.mifos.openbanking.android.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.mifos.openbanking.android.MyApplicationTheme

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}
@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
