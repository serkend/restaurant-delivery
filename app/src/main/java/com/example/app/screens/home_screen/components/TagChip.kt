package com.example.app.screens.home_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.app.utils.Constants

@Composable
fun ChipGroup(
    tags: List<String>,
    isSticked: Boolean = false,
    alphaValue:Float = 0f,
    onSelectedChanged: (String) -> Unit
) {
    val color: Color by animateColorAsState(
        if (!isSticked) {
            MaterialTheme.colorScheme.background
        } else {
            MaterialTheme.colorScheme.secondary
        }
    )

    var selectedChip by remember {
        mutableStateOf(Constants.TAG_LIST[0])
    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .alpha(alphaValue)
            .padding(top = 6.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(tags) { tag ->
            val isSelected = (tag == selectedChip)
            TextChip(isSelected = isSelected, text = tag) {
                //  isSelected = !it
                selectedChip = tag
                onSelectedChanged(tag)
            }
        }
    }

}

@Composable
fun TextChip(
    isSelected: Boolean,
    text: String,
    onChecked: () -> Unit,
) {
    TextButton(
        modifier = Modifier.border(border = BorderStroke(width = 0.dp, color = Color.Transparent)),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        onClick = { onChecked() },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary,
            contentColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onTertiary
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(
            modifier = Modifier,
            text = text,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onTertiary,
            style = MaterialTheme.typography.bodySmall
        )
    }
}