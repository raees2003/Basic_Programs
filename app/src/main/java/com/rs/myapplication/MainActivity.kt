package com.rs.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rs.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MyApp()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyApp() {

    var status by remember {
        mutableIntStateOf(1)
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                status = appHeader {
                    status = it
                }
            }
            item {
                when (status) {
                    1 -> Armstrong()
                    2 -> Palindrome()
                    3 -> Prime()
                    4 -> Factorial()
                }
            }
        }

    }
}


@Composable
fun appHeader(onStatusChange: (Int) -> Unit): Int {
    var status by remember { mutableIntStateOf(1) } // Mutable variable initialized with 0

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 15.dp, 12.dp, 0.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Cyan)
                .padding(vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Basic Program",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.SansSerif
                )
            )
            Spacer(modifier = Modifier.height(12.dp))

            var program = ""
            when (status) {
                1 -> program = "Armstrong"
                2 -> program = "Palindrome"
                3 -> program = "Prime Number"
                4 -> program = "Factorial"
            }

            Text(
                text = program,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            ArrowButtons(
                onLeftClick = {
                    if (status > 1) {
                        status -= 1 // Decrement the status value
                        onStatusChange(status)
                    }
                },
                onRightClick = {
                    if (status < 4) {
                        status += 1 // Increment the status value
                        onStatusChange(status)
                    }
                }
            )
        }
    }
    return status
}


@Composable
fun ArrowButtons(
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    shapeIcon: Shape = MaterialTheme.shapes.extraLarge,
    iconSize: Int = 100,
    iconColor: Color = MaterialTheme.colorScheme.primary
) {
    Row {
        ArrowButton(
            onClick = onLeftClick,
            shapeIcon = shapeIcon,
            iconSize = iconSize,
            iconColor = iconColor,
            iconResId = R.drawable.ic_arrow_left
        )
        Spacer(modifier = Modifier.weight(1f))
        ArrowButton(
            onClick = onRightClick,
            shapeIcon = shapeIcon,
            iconSize = iconSize,
            iconColor = iconColor,
            iconResId = R.drawable.ic_arrow_right
        )
    }
}

@Composable
fun ArrowButton(
    onClick: () -> Unit,
    shapeIcon: Shape = MaterialTheme.shapes.large,
    iconSize: Int = 100,
    iconColor: Color = MaterialTheme.colorScheme.primary,
    iconResId: Int
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
            .clip(shapeIcon)
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = "Arrow Icon",
            tint = iconColor,
            modifier = Modifier.width(iconSize.dp),
        )
    }
}




@Composable
fun Armstrong() {
    var input by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = input,
                onValueChange = {
                    input = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .clip(RoundedCornerShape(12.dp)),
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.DarkGray
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Calculation",
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Monospace
                )
            )

            if (input != "") {
                Text(
                    text = "Input : $input",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                var rem = ""
                var sum = 0
                for (i in input.indices) {
                    Text(
                        text = "Step ${i + 1} : ",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.fillMaxWidth()
                    )
                    val digit: Int = input[i].toString().toInt() // Convert char to int
                    val r: Int = digit * digit * digit
                    sum += r
                    rem += if (i != input.length - 1) {
                        "$r + "
                    } else {
                        "$r"
                    }
                    Text(
                        text = "   ->   $r = $digit * $digit * $digit",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Text(
                    text = "Step ${input.length + 1} : ",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(text = "   ->   $sum = $rem", modifier = Modifier.fillMaxWidth())
                Text(
                    text = "Step ${input.length + 2} : ",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "          Input: $input == $sum ",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.fillMaxWidth()
                )


                if (sum == input.toIntOrNull()) { // Convert input to Int for comparison
                    Text(
                        text = "Armstrong",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),

                    )
                } else {
                    Text(
                        text = "Not Armstrong",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp,color = Color.Red),

                    )
                }
            }
        }
    }
}


@Composable
fun Palindrome() {
    var input by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = input,
                onValueChange = {
                    input = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .clip(RoundedCornerShape(12.dp)),
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.DarkGray
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Logic",
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Monospace
                )
            )

            if (input != "") {
                Text(
                    text = "Input : $input",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Step 1 : ",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "   Reversed ->   ${input.reversed()}",
                        modifier = Modifier.fillMaxWidth()
                    )

                Text(
                    text = "Step 2 : ",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(text = "   Compared ->   $input == ${input.reversed()}", modifier = Modifier.fillMaxWidth())

                if (input == input.reversed()) { // Convert input to Int for comparison
                    Text(
                        text = "Palindrome",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
                    )
                } else {
                    Text(
                        text = "Not Palindrome",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp,color = Color.Red),
                    )
                }
            }
        }
    }
}

@Composable
fun Prime() {
    var input by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = input,
                onValueChange = {
                    input = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .clip(RoundedCornerShape(12.dp)),
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.DarkGray
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Calculation",
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Monospace
                )
            )

            if (input != "") {
                Text(
                    text = "Input : $input",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                val num = input.toIntOrNull() ?: 0
                if (num <= 1) {
                    Text(
                        text = "Step 1: $num is not a prime number as it is less than or equal to 1.",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    var isPrime = true
                    for (i in 2 until num) {
                        if (num % i == 0) {
                            isPrime = false
                            break
                        }
                    }
                    if (isPrime) {
                        Text(
                            text = "Step 1: $num is greater than 1. Start checking divisibility.",
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            modifier = Modifier.fillMaxWidth()
                        )
                        for (i in 2 until num) {
                            Text(
                                text = "Step ${i - 1}: $num % $i = ${num % i}",
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Text(
                            text = "Step ${num - 1}: $num is divisible only by 1 and itself.",
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "Step $num: Conclusion - $num is a Prime Number!",
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        Text(
                            text = "Step 1: $num is greater than 1. Start checking divisibility.",
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            modifier = Modifier.fillMaxWidth()
                        )
                        for (i in 2 until num) {
                            Text(
                                text = "Step ${i - 1}: $num % $i = ${num % i}",
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                modifier = Modifier.fillMaxWidth()
                            )
                            if (num % i == 0) {
                                Text(
                                    text = "Step $i: $num is divisible by $i, so it is not a prime number.",
                                    style = TextStyle(fontWeight = FontWeight.Bold),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                break
                            }
                        }
                        Text(
                            text = "Step $num: Conclusion - $num is not a Prime Number.",
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Factorial() {
    var input by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = input,
                onValueChange = {
                    input = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .clip(RoundedCornerShape(12.dp)),
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.DarkGray
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Calculation",
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Monospace
                )
            )

            if (input != "") {
                Text(
                    text = "Input : $input",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))


                var f = 1
                for (i in input.toInt() downTo 1) {
                    f *= i
                    Text(
                        text = "   ->   $i x $input = $f",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Text(
                    text = "Factorial is -> $f",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp),
                )
            }
        }
    }
}

