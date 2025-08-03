package com.ifechukwu.moveapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Flip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifechukwu.moveapp.R
import com.ifechukwu.moveapp.ui.theme.NunitoSans

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    onSearchClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    value: String,
    readOnly: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onSearchClick != null) {
                    Modifier.pointerInput(onSearchClick) {
                        awaitEachGesture {
                            // Modifier.clickable doesn't work for text fields, so we use Modifier.pointerInput
                            // in the Initial pass to observe events before the text field consumes them
                            // in the Main pass.
                            awaitFirstDown(pass = PointerEventPass.Initial)
                            val upEvent =
                                waitForUpOrCancellation(pass = PointerEventPass.Initial)
                            if (upEvent != null) {
                                onSearchClick()
                            }
                        }
                    }
                } else {
                    Modifier
                }
            ),
        placeholder = {
            Text(
                text = stringResource(R.string.enter_the_receipt_number),
                color = Color.Gray,
                fontSize = 11.sp,
                fontFamily = NunitoSans
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        trailingIcon = {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = MaterialTheme.colorScheme.tertiary, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Flip,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        readOnly = readOnly,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        singleLine = true,
    )
}