package com.deepakverma.todolist.Template

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deepakverma.todolist.R

//@Preview(showSystemUi = true)
@Composable
fun CardTemplate() {

    val primary_color = colorResource(id = R.color.primary_color)
    val card_color = colorResource(id = R.color.card_color)

    var title = "This is Title Welcome to my app"
    var description = "Description Your column for inputs goes here (as in your code)"
    var priority = "Priority"
    var date = "07/08/25"

    OutlinedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
//            elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = card_color)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(IntrinsicSize.Min) // ✅ This makes both columns match the tallest one
            /* horizontalArrangement = Arrangement.SpaceAround,
             verticalAlignment = Alignment.CenterVertically*/

        ) {
            Column(
                Modifier
                    .weight(0.8f)
                    .fillMaxHeight()/*  .border(1.dp, Color.Black)*/
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider(thickness = 2.dp, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(10.dp))

                Text(text = description, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(10.dp))
                Text(
                    text = date,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )

            }

            Column(
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxHeight(),
//                        .border(2.dp, primary_color),
                verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = priority,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    Icons.Default.DeleteOutline, contentDescription = "delete_task",
//                        modifier = Modifier.size(30.dp),
                    tint = primary_color
                )

            }
        }
    }
}
