package com.nailton.managerpassword.screens.authentication

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import com.nailton.managerpassword.presentation.configmodel.MyViewModel
import com.nailton.managerpassword.presentation.configmodel.ViewModelFactory
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.Injector
import com.nailton.managerpassword.routes.graph.RootNavGraph
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterScreen {

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var mpViewModel: MyViewModel
    private lateinit var valueRegister: String

    @Composable
    fun ResgisterConfig(navController: NavController) {
        val context = LocalContext.current
        val viewModelStore: ViewModelStoreOwner? = LocalViewModelStoreOwner.current
        (context.applicationContext as Injector).createMPSubComponent().injectCreateUser(this)
        mpViewModel = ViewModelProvider(viewModelStore!!, factory)[MyViewModel::class.java]
        valueRegister = ""
        Register(navController)
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun Register(navController: NavController) {

        val context = LocalContext.current
        val owner = LocalLifecycleOwner.current
        val coroutineScope = rememberCoroutineScope()
        var txtFieldValue by rememberSaveable { mutableStateOf("") }
        var txtFieldEmail by rememberSaveable { mutableStateOf("") }
        var txtPassValue by rememberSaveable { mutableStateOf("") }

        fun registerUser(name: String, email: String, pass: String, owner: LifecycleOwner): CompletableDeferred<String> {
            val response = mpViewModel.insertUser(name, email, pass)
            val deferred = CompletableDeferred<String>()
            response.observe(owner) {
                deferred.complete(it.toString())
            }
            return deferred
        }

        val onChangeEmail = {it: String ->
            txtFieldEmail = it
        }

        val onChangeValue = { it: String ->
            txtFieldValue = it
        }

        val onChangePassValue = {it: String ->
            txtPassValue = it
        }

        fun changeValueButton(): Boolean {
            return txtFieldEmail.length >= 11 && txtPassValue.length >= 8 && txtFieldValue.length >= 3
        }

        val onTouchLogin = {
            coroutineScope.launch {
                valueRegister = withContext(Dispatchers.Main) {
                    registerUser(txtFieldValue, txtFieldEmail, txtPassValue, owner).await()
                }
                if (valueRegister.contains("User Created")) {
                    Toast.makeText(
                        context,
                        valueRegister,
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(RootNavGraph.Graph.AUTHENTICATION) {
                        launchSingleTop = true
                    }
                }
                Toast.makeText(
                    context,
                    valueRegister,
                    Toast.LENGTH_LONG
                ).show()
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

            BtnField({ onTouchLogin() }, "Register", Color(0.475f, 0.094f, 0.82f), changeValueButton())
        }
    }

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
        isTrue: Boolean
    ) {
        Button(
            onClick = { onTouch() },
            colors = ButtonDefaults.buttonColors(color),
            enabled = isTrue
        ) {
            Text(text = text.uppercase(), color = Color.White)
        }
    }
}