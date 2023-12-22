package com.nailton.managerpassword.screens.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nailton.managerpassword.routes.graph.RootNavGraph

class RegisterScreen {

    @Composable
    fun Register(navController: NavController) {
        var txtFieldValue by rememberSaveable { mutableStateOf("") }
        var txtFieldEmail by rememberSaveable { mutableStateOf("") }
        var txtPassValue by rememberSaveable { mutableStateOf("") }

        val onChangeEmail = {it: String ->
            txtFieldEmail = it
        }

        val onChangeValue = { it: String ->
            txtFieldValue = it
        }

        val onChangePassValue = {it: String ->
            txtPassValue = it
        }

        val onTouchLogin = {
            navController.navigate(RootNavGraph.Graph.AUTHENTICATED) {
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
                label = "Nome",
                placeholder = "Digite seu Nome",
                icons = Icons.Outlined.Person,
                keyboardType = KeyboardType.Text,
                visualTransformation = VisualTransformation.None,
            )

            TextField(
                text = txtFieldEmail,
                onChange = onChangeEmail,
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

            BtnField({ onTouchLogin() }, "Register", Color(0.475f, 0.094f, 0.82f))
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
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
            label = { Text(text = label)},
            placeholder = { Text(text = placeholder)},
            maxLines = 1,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            leadingIcon = {
                Icon(icons, contentDescription = "Icon")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Cyan,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Cyan,
                focusedLeadingIconColor = Color.Cyan,
                unfocusedLabelColor = Color.Black,
                unfocusedLeadingIconColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
    }

    @Composable
    fun BtnField(
        onTouch: () -> Unit,
        text: String,
        color: Color
    ) {
        Button(
            onClick = { onTouch() },
            colors = ButtonDefaults.buttonColors(color)
        ) {
            Text(text = text.uppercase(), color = Color.White)
        }
    }
}