package com.nailton.managerpassword.screens.authentication

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nailton.managerpassword.routes.NavigationRoutes

class ComposeLogin {

    @Composable
    fun TextField(
        text: String,
        onChange: (String) -> Unit,
        label: String,
        placeholder: String,
        keyboardType: KeyboardType,
        icons: ImageVector,
        visualTransformation: VisualTransformation
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onChange,
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            maxLines = 1,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            leadingIcon = {
                Icon(icons, contentDescription = "Icon")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Cyan,
                unfocusedBorderColor = Color.Black,
                focusedLeadingIconColor = Color.Cyan,
                unfocusedLeadingIconColor = Color.Black,
                focusedLabelColor = Color.Cyan,
                unfocusedLabelColor = Color.Black,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
    }

    @Composable
    fun BtnField(
        onTouch: () -> Unit,
        text: String,
        color: Color,
        isEnable: Boolean
    ) {
        Button(
            onClick = { onTouch() },
            colors = ButtonDefaults.buttonColors(color),
            enabled = isEnable
        ) {
            Text(text = text.uppercase(), color = Color.White)
        }

    }

    @Composable
    fun Login(navController: NavController) {

        val context = LocalContext.current
        var txtFieldValue by rememberSaveable { mutableStateOf("") }
        var txtPassValue by rememberSaveable { mutableStateOf("") }
        //val intent = Intent(context, LoginScreen::class.java)
        //ContextCompat.startActivity(context, intent, null)

        val onChangeValue = { it: String ->
            txtFieldValue = it
        }

        val onChangePassValue = { it: String ->
            txtPassValue = it
        }

        fun changeValueButton(): Boolean {
            return txtFieldValue.length >= 11 && txtPassValue.length >= 8
        }

        val onTouchLogin = {
            val value: String = "Value" //LoginScreen().login(txtFieldValue, txtFieldValue)
            Toast.makeText(
                context,
                value,
                Toast.LENGTH_LONG
            ).show()
        }

        val onTouchRegister = {
            navController.navigate(NavigationRoutes.Register.routes) {
                launchSingleTop = true
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                text = txtFieldValue,
                onChange = onChangeValue,
                label = "E-mail",
                placeholder = "Digite seu Email",
                icons = Icons.Outlined.Email,
                keyboardType = KeyboardType.Email,
                visualTransformation = VisualTransformation.None,
            )

            TextField(
                text = txtPassValue,
                onChange = onChangePassValue,
                label = "Password",
                placeholder = "Digite sua Senha",
                icons = Icons.Outlined.Lock,
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
            )

            BtnField({ onTouchLogin() }, "Login", Color(0.475f, 0.094f, 0.82f), changeValueButton())

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Ainda não tem login ?".uppercase(),
                modifier = Modifier
                    .clickable { onTouchRegister() },
                fontSize = 14.sp,
                color = Color.White
            )
        }

    }
}