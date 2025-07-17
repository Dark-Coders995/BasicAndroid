package com.develop.basicandroid.jettip2


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develop.basicandroid.jettip2.components.InputField
import com.develop.basicandroid.jettip2.util.calculateTotalPerPerson
import com.develop.basicandroid.jettip2.util.calculateTotalTip

@Composable
fun MyApp2(
    content: @Composable () -> Unit
){

}




@Composable
fun TopHeader(totalPerPerson : Double = 130.0){
    val total = "%.2f".format(totalPerPerson)
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(15.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(15.dp))),
        color = Color(0xFFE9D7F7)
    ){
        Column(
            modifier = Modifier
                .padding(13.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$ $total",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

//@Preview
@Composable
fun MainContent(){
    val tipAmount = remember {
        mutableDoubleStateOf(0.0)
    }

    val totalPerPerson = remember {
        mutableDoubleStateOf(0.0)
    }
    val splitByState = remember {
        mutableIntStateOf(1)
    }
    BillForm(
        tipAmount = tipAmount,
        totalPerPerson = totalPerPerson,
        splitByState = splitByState
    ){}
}


@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    range : IntRange = 1..100,
    splitByState : MutableState<Int>,
    tipAmount : MutableState<Double>,
    totalPerPerson : MutableState<Double>,
    onValueChange :(String) -> Unit = {}
){
    val sliderState = remember {
        mutableFloatStateOf(0f)
    }
    val totalBillState = remember {
        mutableStateOf("")
    }
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }

    val tipPercentage = (sliderState.floatValue * 100).toInt()

    val keyboardController = LocalSoftwareKeyboardController.current

    TopHeader(totalPerPerson = totalPerPerson.value)
    Surface (
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ){
        Column(
            modifier = Modifier
                .padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                modifier = Modifier
                    .fillMaxWidth(),
                valueState = totalBillState,
                label = "Enter Bill",
                enabled =  true,
                isSingleLine = true ,
                onAction = KeyboardActions {
                    if(!validState) return@KeyboardActions
                    onValueChange(totalBillState.value.trim())
                    keyboardController?.hide()
                }
            )
            if(validState){
                Row(
                    modifier = Modifier
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier
                            .align(
                                alignment = Alignment.CenterVertically
                            ),
                        text = "Split",
                    )
                    Spacer(
                        modifier = Modifier
                            .width(120.dp)
                    )
                    Row(
                        modifier = Modifier
                            .padding(
                                horizontal = 3.dp
                            ),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            modifier = Modifier
                                .clip(shape = CircleShape),
                            colors = IconButtonDefaults.iconButtonColors(Color.LightGray),
                            onClick = {
                                splitByState.value += 1
                                totalPerPerson.value = calculateTotalPerPerson(
                                    totalBill = totalBillState.value.toDouble(),
                                    splitBy = splitByState.value,
                                    tipPercentage = tipPercentage
                                )
                                Log.d("Icon" , "Added")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add"
                            )
                        }
                        Text(
                            modifier = Modifier
                                .align(
                                    alignment = Alignment.CenterVertically
                                )
                                .padding(
                                    start = 5.dp,
                                    end = 5.dp
                                ),
                            text = "${splitByState.value}"
                        )
                        IconButton(
                            modifier = Modifier
                                .clip(shape = CircleShape),
                            colors = IconButtonDefaults.iconButtonColors(Color.LightGray),
                            onClick = {
                                if(splitByState.value > 1){
                                    splitByState.value -= 1

                                }
                                else{
                                    splitByState.value = 1
                                }
                                totalPerPerson.value = calculateTotalPerPerson(
                                    totalBill = totalBillState.value.toDouble(),
                                    splitBy = splitByState.value,
                                    tipPercentage = tipPercentage
                                )

                                Log.d("Icon" , "Removed")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Remove"
                            )
                        }
                    }
                }

                // Tip Row
                Row(
                    modifier = modifier
                        .padding(
                            horizontal = 3.dp,
                            vertical = 12.dp
                        ),
                    horizontalArrangement = Arrangement.End
                ){
                    Text(
                        text = "Tips",
                        modifier = Modifier
                            .align(
                                alignment = Alignment.CenterVertically
                            )
                    )
                    Spacer(
                        modifier = Modifier
                            .width(160.dp)
                    )
                    Text(
                        text = "$ ${tipAmount.value}"
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$tipPercentage %"
                    )
                    Spacer(
                        modifier = Modifier
                            .height(14.dp)
                    )
                    Slider(
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                end = 16.dp
                            ),
                        steps = 5,
                        value = sliderState.floatValue,
                        onValueChange = { newValue ->
                            sliderState.floatValue = newValue
                            tipAmount.value = calculateTotalTip(totalBillState.value.toDouble() , tipPercentage)
                            totalPerPerson.value = calculateTotalPerPerson(
                                totalBill = totalBillState.value.toDouble(),
                                splitBy = splitByState.value,
                                tipPercentage = tipPercentage
                            )
                        },

                    )
                }
            }else{
                Box{}
            }
        }
    }
}




@Preview
@Composable
fun App2(){
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            MainContent()
        }
    }
}