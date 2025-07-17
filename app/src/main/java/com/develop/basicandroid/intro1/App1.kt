package com.develop.basicandroid.intro1

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.develop.basicandroid.ui.theme.AppTheme

@Composable
fun CreateCircle(
    moneyCounter :Int = 0,
    updateMoneyCounter : (Int) -> Unit
){
    Surface (
        modifier = Modifier
            .padding(3.dp)
            .size(100.dp)
            .clickable {
                updateMoneyCounter(moneyCounter + 1)
                Log.d("Tap", "CreateCircle: Clicked $moneyCounter times")
            },
        shape = CircleShape,
        shadowElevation = 4.dp
    ){
        Box(
            contentAlignment = Alignment.Center
        ){
            Text(text = "Tap $moneyCounter")
        }
    }
}

@Composable
fun MyApp1(){

    var moneyCounter by remember {
        mutableIntStateOf(0)
    } // State
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "$ $moneyCounter",
                style = TextStyle(
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(
                modifier = Modifier
                    .height(130.dp)
            )
            CreateCircle(moneyCounter){ newValue ->
                moneyCounter = newValue
            }
        }
    }
}

@Preview
@Composable
fun App1(){
    AppTheme {
        MyApp1()
    }
}
