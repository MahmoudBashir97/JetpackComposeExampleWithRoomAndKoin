package com.mahmoud_bashir.composeapplying.ui.main

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.mahmoud_bashir.composeapplying.room.UserEntity
import com.mahmoud_bashir.composeapplying.ui.theme.ComposeApplyingTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplyingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    color = MaterialTheme.colors.background,
                ) {

                  HomePage(context = this,this)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeApplyingTheme {
      // SimpleTextField()
    }
}

@Composable
fun HomePage(context: Context,owner:LifecycleOwner){

    val mainViewModel = getViewModel<MainViewModel>()

    var nameVal by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var ageVal by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var isNameError by remember {
        mutableStateOf(false)
    }
    var isAgeError by remember {
        mutableStateOf(false)
    }

    Column {

        OutlinedTextField(
            value = nameVal,
//        leadingIcon = {
//                      Icon(
//                          imageVector = Icons.Default.Email, contentDescription = null)
//        },
            trailingIcon = {
                if (isNameError)
                    Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colors.error)
              //  Icon(imageVector = Icons.Default.Add, contentDescription = null)
                           },
            onValueChange = {
                    newText->
                nameVal= newText

                if(isNameError) isNameError=false
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    Toast.makeText(context,"done",Toast.LENGTH_SHORT).show()
                }
            ),
            isError = isNameError,
            singleLine = true,
            label ={ Text(text = "UserName")},
            placeholder = { Text(text = "userName")}
        )
        if (isNameError){
            Text(text = "Empty Filed",
                color=MaterialTheme.colors.error,
                style=MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 10.dp))
        }
        
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = ageVal,
//        leadingIcon = {
//                      Icon(
//                          imageVector = Icons.Default.Email, contentDescription = null)
//        },
            trailingIcon = {
                if (isAgeError)
                    Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colors.error) 
                           },
            onValueChange = {
                    newText->
                ageVal= newText
                if(isAgeError) isAgeError=false },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    Toast.makeText(context,"done",Toast.LENGTH_SHORT).show()
                }
            ),
            isError = isAgeError,
            singleLine = true,
            label ={ Text(text = "Age")},
            placeholder = { Text(text = "age")}
        )
        if (isAgeError){
            Text(text = "Empty Filed",
            color=MaterialTheme.colors.error,
            style=MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 10.dp))
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row {
            Button(
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp),
                onClick = {
                          mainViewModel.usersList.observe(owner){
                              mlist->
                              if (mlist.isNotEmpty()){
                                  Toast.makeText(context,"size: ${mlist.size}",Toast.LENGTH_SHORT).show()
                              }
                          }
                          },
                shape = RoundedCornerShape(13.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
            ){
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Get")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Get",
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp),
                onClick = {
                    if (nameVal.text.isEmpty()){
                        isNameError=true
                    }else if(ageVal.text.isEmpty()){
                        isAgeError=true
                    }else {
                        isAgeError=false
                        isNameError=false

                        val check = mainViewModel.insertUserData(UserEntity(0,nameVal.text,ageVal.text.toInt())).isActive
                        if (check){
                            nameVal = TextFieldValue("")
                            ageVal = TextFieldValue("")
                        }
                    }
                },
                shape = RoundedCornerShape(13.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
            ){
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Add",
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

    }
}


@Composable
fun SimpleTextField(context:Context){
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    OutlinedTextField(
        value = text,
//        leadingIcon = {
//                      Icon(
//                          imageVector = Icons.Default.Email, contentDescription = null)
//        },
        trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
        onValueChange = {
            newText->
            text= newText
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                Toast.makeText(context,"done",Toast.LENGTH_SHORT).show()
            }
        ),
        singleLine = true,
        label ={ Text(text = "UserName")},
        placeholder = { Text(text = "userName")}
    )
}

@Composable
fun SimpleButton(context: Context){

    Button(
        modifier = Modifier
            .width(150.dp)
            .height(50.dp),
        onClick = {
          Toast.makeText(context,"Add clicked",Toast.LENGTH_SHORT).show()           
    },
        shape = RoundedCornerShape(13.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
    ){
        Image(
            imageVector = Icons.Default.Add,
            contentDescription = "add")
        Text(text = "Add",
        style = TextStyle(
            color = Color.Red,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        )
    }
}