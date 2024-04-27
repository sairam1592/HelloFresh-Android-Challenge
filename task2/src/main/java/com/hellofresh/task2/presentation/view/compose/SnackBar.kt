package com.hellofresh.task2.presentation.view.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.hellofresh.task2.R
import com.hellofresh.task2.common.AppConstants

/**
 * ShowErrorSnackBarWithoutAction is used to show the error Snack bar without any action
 */
@Composable
fun ShowErrorSnackBarWithoutAction() {
    val snackState = remember { SnackbarHostState() }
    androidx.compose.material.SnackbarHost(hostState = snackState, Modifier, snackbar = {
        SnackBar(it)
    })
    LaunchedEffect(snackState) {
        snackState.showSnackbar(AppConstants.ERROR_API_FAILED)
    }
}

@Composable
fun SnackBar(
    data: SnackbarData,
    backgroundColor: Color = colorResource(id = R.color.dark_red),
    contentColor: Color = colorResource(id = R.color.white)
) {
    Snackbar(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.padding_8)),
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_8))
    ) {
        Text(
            modifier = Modifier,
            text = data.message,
            style = MaterialTheme.typography.body2,
            color = contentColor,
        )
    }
}