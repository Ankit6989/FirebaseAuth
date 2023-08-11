package com.apcoding.firebaseauth.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.apcoding.firebaseauth.ui.theme.spacing
import androidx.compose.foundation.layout.wrapContentSize
import com.apcoding.firebaseauth.R



@Composable
fun AuthHeader() {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val spacing = MaterialTheme.spacing

        Image(
            modifier = Modifier
                .size(128.dp, 128.dp),
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = stringResource(id = R.string.app_name)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = spacing.medium),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

