package com.example.checkbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var step by remember { mutableIntStateOf(1) }
            var stepResult by remember { mutableDoubleStateOf(0.0) }
            var result by remember { mutableIntStateOf(0) }
            var checkedOne by remember { mutableStateOf(false) }
            var checkedTwo by remember { mutableStateOf(false) }
            var checkedThree by remember { mutableStateOf(false) }
            var checkedFour by remember { mutableStateOf(false) }
            val flags = listOf(
                Flags(R.drawable.flag_rossii_1_),
                Flags(R.drawable.flag_ri),
                Flags(R.drawable.flag_sssr)
            )
            var selectedFlag by remember { mutableStateOf(flags[0]) }

            Column(Modifier.padding(top = 45.dp)) {
                Text(
                    text = when (step) {
                        1 -> "Добро пожаловать на историческую викторину"
                        2 -> "Какие страны входили в Антанту во время Второй мировой войны"
                        3 -> "Какие земли завоевал Иван IV Грозный"
                        4 -> "Третий вопрос"
                        else -> "Ваш результат $result из 3"
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp
                )
                if (step == 2 || step == 3) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedOne,
                            onCheckedChange = {
                                checkedOne = it
                                when (step) {
                                    2 -> if (checkedOne) stepResult += 0.5 else stepResult -= 0.5
                                    3 -> if (checkedOne) stepResult -= 0.5 else stepResult += 0.5
                                }
                            }
                        )
                        Text(
                            text = when (step) {
                                2 -> "Франция"
                                3 -> "Крым"
                                else -> ""
                            }, fontSize = 18.sp
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedTwo,
                            onCheckedChange = {
                                checkedTwo = it
                                when (step) {
                                    2 -> if (checkedTwo) stepResult -= 0.5 else stepResult += 0.5
                                    3 -> if (checkedTwo) stepResult += 0.5 else stepResult -= 0.5
                                }
                            }
                        )
                        Text(
                            text = when (step) {
                                2 -> "Германия"
                                3 -> "Казань"
                                else -> ""
                            }, fontSize = 18.sp
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedThree,
                            onCheckedChange = {
                                checkedThree = it
                                when (step) {
                                    2 -> if (checkedThree) stepResult -= 0.5 else stepResult += 0.5
                                    3 -> if (checkedThree) stepResult -= 0.5 else stepResult += 0.5
                                }
                            }
                        )
                        Text(
                            text = when (step) {
                                2 -> "Китай"
                                3 -> "Донецк"
                                else -> ""
                            }, fontSize = 18.sp
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedFour,
                            onCheckedChange = {
                                checkedFour = it
                                when (step) {
                                    2 -> if (checkedFour) stepResult += 0.5 else stepResult -= 0.5
                                    3 -> if (checkedFour) stepResult += 0.5 else stepResult -= 0.5
                                }
                            }
                        )
                        Text(
                            text = when (step) {
                                2 -> "Англия"
                                3 -> "Астрахань"
                                else -> ""
                            }, fontSize = 18.sp
                        )
                    }
                }
                if (step == 4){
                    flags.forEach { flag ->
                        val selected = selectedFlag == flag
                        Box(
                            Modifier
                                .border(
                                    width = 3.dp,
                                    color = if (selected) Color.Red else Color.Black
                                )
                                .selectable(
                                    selected = selected,
                                    onClick = {
                                        selectedFlag = flag
                                        if (flag.image == R.drawable.flag_ri) stepResult++
                                    }
                                )
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(flag.image),
                                    contentDescription = "option",
                                    modifier = Modifier.size(width = 150.dp, height = 100.dp)
                                )
                            }
                        }
                    }
                }
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (step < 5) {
                        Button(
                            onClick = {
                                if (step == 1) {
                                    step++
                                } else if (step in 2..4) {
                                    checkedOne = false
                                    checkedTwo = false
                                    checkedThree = false
                                    checkedFour = false
                                    step++
                                    if (stepResult == 1.0) result++
                                    stepResult = 0.0
                                }
                            },
                        ) { Text(text = "Продолжить") }
                    }
                }
            }
        }
    }
}

data class Flags(val image: Int)